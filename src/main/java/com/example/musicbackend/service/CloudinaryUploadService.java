package com.example.musicbackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryUploadService {

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    /**
     * Upload file lên Cloudinary.
     * @param file MultipartFile cần upload
     * @param folderName Thư mục lưu trữ trên Cloudinary
     * @param resourceType Loại tài nguyên (image, raw, video,...)
     * @return URL của file sau khi upload
     */
    public String uploadFile(MultipartFile file, String folderName, String resourceType) throws IOException {
        try {
            // Khởi tạo đối tượng Cloudinary mỗi khi upload để đảm bảo rằng chúng ta sử dụng các giá trị mới từ @Value
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", cloudName,
                    "api_key", apiKey,
                    "api_secret", apiSecret
            ));

            // Upload file lên Cloudinary
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                    "folder", folderName,
                    "public_id", UUID.randomUUID().toString() + "-" + file.getOriginalFilename(), // Tạo public_id duy nhất
                    "resource_type", resourceType // Chỉ định kiểu tài nguyên (image, raw, video,...)
            ));

            // Trả về URL của file sau khi upload
            return (String) uploadResult.get("url");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error uploading file to Cloudinary: " + e.getMessage());
        }
    }
}
