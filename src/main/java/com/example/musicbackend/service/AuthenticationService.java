package com.example.musicbackend.service;

import com.example.musicbackend.model.User;

public interface AuthenticationService {
    User LoginAndReturnJWT(User signInRequest);

}
