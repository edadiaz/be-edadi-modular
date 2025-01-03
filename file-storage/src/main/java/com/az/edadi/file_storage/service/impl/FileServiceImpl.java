package com.az.edadi.file_storage.service.impl;

import com.az.edadi.dal.entity.file_storage.EdaFile;
import com.az.edadi.dal.repository.FileRepository;
import com.az.edadi.file_storage.adapter.FileAdapter;
import com.az.edadi.file_storage.model.res.UploadedImageRes;
import com.az.edadi.file_storage.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final S3Client s3Client;
    private final FileRepository fileRepository;
    private final FileAdapter fileAdapter;
    private final String USER_FILE_PATH = "public/image/user/";

    @Value("${aws.s3.url}")
    private String ROOT_URL;

    @Override
    public UploadedImageRes uploadUserImage(MultipartFile file) {

        EdaFile entity = mapToFileEntity(file);
        fileRepository.save(entity);

        Path tempFile = null;
        try {
            tempFile = createTempFile(file);
            uploadToS3(tempFile, entity.getPath() + entity.getId());

            Path thumbnailS = generateThumbnailS(tempFile);
            uploadToS3(thumbnailS, entity.getPath() + entity.getId() + "_thumbS");

        } catch (IOException e) {
            log.error("File processing failed when uploading image with id {} to S3", entity.getId());
            throw new RuntimeException("File processing failed", e);
        } finally {
            deleteTempFile(tempFile);
        }

        return fileAdapter.map(entity);
    }

    private EdaFile mapToFileEntity(MultipartFile file) {
        EdaFile entity = new EdaFile();
        fileAdapter.map(entity, file);
        entity.setPath(USER_FILE_PATH);
        return entity;
    }

    private Path createTempFile(MultipartFile file) throws IOException {
        Path tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile.toFile());
        return tempFile;
    }

    private void uploadToS3(Path filePath, String s3Key) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket("edadi")
                .key(s3Key)
                .build();
        s3Client.putObject(putObjectRequest, filePath);
    }

    private Path generateThumbnailS(Path originalFile) throws IOException {
        Path thumbnailFile = Files.createTempFile("thumbS-", originalFile.getFileName().toString());
        Thumbnails.of(originalFile.toFile())
                .size(150, 150)
                .toFile(thumbnailFile.toFile());
        return thumbnailFile;
    }


    private void deleteTempFile(Path tempFile) {
        if (tempFile != null) {
            try {
                Files.deleteIfExists(tempFile);
            } catch (IOException e) {
                log.error("Failed to delete temporary file: {}", tempFile);
            }
        }
    }


    @Override
    public void deleteFile(String fileName) {

    }
}
