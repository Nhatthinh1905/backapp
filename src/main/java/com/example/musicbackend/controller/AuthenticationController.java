package com.example.musicbackend.controller;

import com.example.musicbackend.model.User;
import com.example.musicbackend.service.AuthenticationServiceImpl;
import com.example.musicbackend.service.ImageUploadService;
import com.example.musicbackend.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AuthenticationServiceImpl authenticationService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestParam( value = "imgFile" ,required = false) MultipartFile imgFile,
                                      @RequestParam("name") String name,
                                      @RequestParam("email") String email,
                                      @RequestParam("password") String password,
                                      @RequestParam("birthday") String birthday) {
        if (userService.findByEmail(email).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthDay(birthday);
        user.setName(name);
        if(imgFile == null){
            user.setImageUrl(null);
        }
        else {
            String imageUrl = imageUploadService.uploadImage(imgFile,"img_user");
            user.setImageUrl(imageUrl);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody User user) {
        return new ResponseEntity<>(authenticationService.LoginAndReturnJWT(user), HttpStatus.OK);
    }
}
