package com.example.musicbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class Mp3UploadService {

    @Autowired
    private CloudinaryUploadService cloudinaryUploadService;

    // Phương thức upload MP3
    public String uploadMp3(MultipartFile mp3File, String folderName) {
        try {
            if (mp3File.getContentType().equals("audio/mpeg")) {
                return cloudinaryUploadService.uploadFile(mp3File, folderName, "raw");
            } else {
                throw new IllegalArgumentException("Only MP3 files are allowed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading MP3 file: " + e.getMessage();
        }
    }
}
