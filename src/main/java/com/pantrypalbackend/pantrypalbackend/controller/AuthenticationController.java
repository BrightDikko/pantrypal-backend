package com.pantrypalbackend.pantrypalbackend.controller;

import com.pantrypalbackend.pantrypalbackend.constants.PathConstants;
import com.pantrypalbackend.pantrypalbackend.dto.UserLoginRequest;
import com.pantrypalbackend.pantrypalbackend.dto.UserLoginResponse;
import com.pantrypalbackend.pantrypalbackend.dto.UserRegistrationRequest;
import com.pantrypalbackend.pantrypalbackend.dto.UserRegistrationResponse;
import com.pantrypalbackend.pantrypalbackend.service.Impl.AuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConstants.AUTH)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/register")
    @Operation(summary = "User Registration",
            description = "Register a new user with their details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody( // Swagger RB - Fully qualified name
                    content = @Content(schema = @Schema(implementation = UserRegistrationRequest.class))))
    @ApiResponse(responseCode = "200",
            description = "User registered successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserRegistrationResponse.class)))
    public ResponseEntity<UserRegistrationResponse>
    register(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        return ResponseEntity.ok(authenticationService.registerUser(userRegistrationRequest));
    }

    @PostMapping("/login")
    @Operation(summary = "User Login",
            description = "Authenticate a user and return a login token.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody( // Swagger RB - Fully qualified name
                    content = @Content(schema = @Schema(implementation = UserLoginRequest.class))))
    @ApiResponse(responseCode = "200",
            description = "User logged in successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserLoginResponse.class)))
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(authenticationService.loginUser(userLoginRequest));
    }
}
