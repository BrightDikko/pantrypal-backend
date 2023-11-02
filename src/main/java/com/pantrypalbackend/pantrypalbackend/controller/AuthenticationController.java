package com.pantrypalbackend.pantrypalbackend.controller;

import com.pantrypalbackend.pantrypalbackend.domain.User;
import com.pantrypalbackend.pantrypalbackend.dto.AuthenticationResponse;
import com.pantrypalbackend.pantrypalbackend.dto.UserRegistrationRequest;
import com.pantrypalbackend.pantrypalbackend.service.Impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        System.out.println("Request to register user received. \nRequest: " + userRegistrationRequest);
        return ResponseEntity.ok(authenticationService.registerUser(userRegistrationRequest));
    }
}
