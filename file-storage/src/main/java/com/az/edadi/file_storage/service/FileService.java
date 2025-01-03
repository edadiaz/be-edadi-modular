package com.az.edadi.file_storage.service;

import com.az.edadi.file_storage.model.res.UploadedImageRes;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    UploadedImageRes uploadUserImage(MultipartFile file);
    void deleteFile(String fileName);
}
