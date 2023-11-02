package com.pantrypalbackend.pantrypalbackend.service;

import com.pantrypalbackend.pantrypalbackend.dto.AuthenticationResponse;
import com.pantrypalbackend.pantrypalbackend.dto.UserRegistrationRequest;

public interface AuthenticationService {
    AuthenticationResponse registerUser(UserRegistrationRequest userRegistrationRequest);
}
