package com.az.edadi.file_storage.controller;

import com.az.edadi.file_storage.model.res.UploadedImageRes;
import com.az.edadi.file_storage.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file-storage")
public class FileUploadController {

    private final FileService fileService;

    @PostMapping("/user")
    public ResponseEntity<UploadedImageRes> uploadUserProfilePicture(
            @RequestParam("image") MultipartFile file) {
        log.info("User image upload request received");
        return ResponseEntity.ok(fileService.uploadUserImage(file));
    }
}
