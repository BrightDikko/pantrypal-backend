package com.pantrypalbackend.pantrypalbackend.service;

import com.pantrypalbackend.pantrypalbackend.dto.UserLoginRequest;
import com.pantrypalbackend.pantrypalbackend.dto.UserLoginResponse;
import com.pantrypalbackend.pantrypalbackend.dto.UserRegistrationResponse;
import com.pantrypalbackend.pantrypalbackend.dto.UserRegistrationRequest;

public interface AuthenticationService {
    UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest);
    UserLoginResponse loginUser(UserLoginRequest userLoginRequest);

}
