package com.az.edadi.file_storage.model.response;

public record UploadedImageRes(String id, String url, String fileType, long size, String uploadDate) {
}
