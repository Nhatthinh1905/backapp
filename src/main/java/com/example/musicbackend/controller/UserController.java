package com.example.musicbackend.controller;

import com.example.musicbackend.model.User;
import com.example.musicbackend.security.UserPrincipal;
import com.example.musicbackend.service.ImageUploadService;
import com.example.musicbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    private ImageUploadService imageUploadService;

    @GetMapping("/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(Authentication authentication){
        String email = authentication.getName();
        Optional<User> user = userService.findByEmail(email);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PostMapping("/updateAvatar")
    public ResponseEntity<?> updateProfile(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                           @RequestParam( value = "imgFile") MultipartFile imgFile) {
        User updateUser = new User();
        String imageUrl = imageUploadService.uploadImage(imgFile,"img_user");
        updateUser.setImageUrl(imageUrl);
        return new ResponseEntity<>(userService.updateUser(userPrincipal.getId(),updateUser),HttpStatus.OK);
    }

}
