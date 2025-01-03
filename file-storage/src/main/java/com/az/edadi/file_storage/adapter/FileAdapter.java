package com.az.edadi.file_storage.adapter;

import com.az.edadi.dal.entity.file_storage.EdaFile;
import com.az.edadi.file_storage.model.res.UploadedImageRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileAdapter {

    @Value("${aws.s3.url}")
    private String ROOT_URL;

    public void map(EdaFile entity, MultipartFile file) {
        entity.setFileType(file.getContentType());
        entity.setSize(file.getSize());
        entity.setFileName(file.getOriginalFilename());
    }

    public UploadedImageRes map(EdaFile entity) {
        return new UploadedImageRes(entity.getId().toString(),
                ROOT_URL + entity.getPath() + entity.getId(),
                entity.getFileType(),
                entity.getSize(),
                entity.getDateCreated().toString());
    }
}
