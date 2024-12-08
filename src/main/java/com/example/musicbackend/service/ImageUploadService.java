package com.example.musicbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageUploadService {

    @Autowired
    private CloudinaryUploadService cloudinaryUploadService;

    // Phương thức upload ảnh
    public String uploadImage(MultipartFile file, String folderName) {
        try {
            if (file.getContentType().startsWith("image/")) {
                return cloudinaryUploadService.uploadFile(file, folderName, "image");
            } else {
                throw new IllegalArgumentException("Only image files are allowed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading image file: " + e.getMessage();
        }
    }
}
