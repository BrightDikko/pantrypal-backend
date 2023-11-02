package com.pantrypalbackend.pantrypalbackend.service;

import com.pantrypalbackend.pantrypalbackend.domain.User;
import com.pantrypalbackend.pantrypalbackend.dto.UserRegistrationRequest;

public interface AuthenticationService {
    User registerUser(UserRegistrationRequest userRegistrationRequest);
}
