package com.pantrypalbackend.pantrypalbackend.controller;

import com.pantrypalbackend.pantrypalbackend.constants.PathConstants;
import com.pantrypalbackend.pantrypalbackend.dto.UserLoginRequest;
import com.pantrypalbackend.pantrypalbackend.dto.UserLoginResponse;
import com.pantrypalbackend.pantrypalbackend.dto.UserRegistrationRequest;
import com.pantrypalbackend.pantrypalbackend.dto.UserRegistrationResponse;
import com.pantrypalbackend.pantrypalbackend.service.Impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConstants.AUTH)
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> register(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        System.out.println("Request to register user received. \nRequest: " + userRegistrationRequest);
        return ResponseEntity.ok(authenticationService.registerUser(userRegistrationRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        System.out.println("Request to log in user received. \nRequest: " + userLoginRequest);
        return ResponseEntity.ok(authenticationService.loginUser(userLoginRequest));
    }
}
