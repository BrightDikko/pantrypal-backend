package com.pantrypalbackend.pantrypalbackend.service.Impl;

import com.pantrypalbackend.pantrypalbackend.domain.User;
import com.pantrypalbackend.pantrypalbackend.dto.UserLoginRequest;
import com.pantrypalbackend.pantrypalbackend.dto.UserLoginResponse;
import com.pantrypalbackend.pantrypalbackend.dto.UserRegistrationRequest;
import com.pantrypalbackend.pantrypalbackend.dto.UserRegistrationResponse;
import com.pantrypalbackend.pantrypalbackend.exceptions.EmailAlreadyTakenException;
import com.pantrypalbackend.pantrypalbackend.exceptions.UserDoesNotExistException;
import com.pantrypalbackend.pantrypalbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {
    public final PasswordEncoder passwordEncoder;
    public final UserRepository userRepository;

    public UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) {

        String encodedPassword = passwordEncoder.encode(userRegistrationRequest.getPassword());

        User user = User.builder()
                .firstName(userRegistrationRequest.getFirstName())
                .lastName(userRegistrationRequest.getLastName())
                .email(userRegistrationRequest.getEmail())
                .password(encodedPassword)
                .build();

        try {
            userRepository.save(user);
            return UserRegistrationResponse.builder()
                    .message("User with email " + user.getEmail() + " was registered successfully!")
                    .build();
        } catch (Exception e) {
            throw new EmailAlreadyTakenException();
        }
    }

    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) {

        Optional<User> userOptional = userRepository.findByEmail(userLoginRequest.getEmail());
        if (userOptional.isPresent()) {
            return UserLoginResponse.builder()
                    .user(userOptional.get())
                    .token("")
                    .build();
        } else {
            throw new UserDoesNotExistException();
        }
    }

}
