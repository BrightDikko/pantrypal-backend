package com.pantrypalbackend.pantrypalbackend.service.Impl;

import com.pantrypalbackend.pantrypalbackend.domain.User;
import com.pantrypalbackend.pantrypalbackend.dto.AuthenticationResponse;
import com.pantrypalbackend.pantrypalbackend.dto.UserRegistrationRequest;
import com.pantrypalbackend.pantrypalbackend.exceptions.EmailAlreadyTakenException;
import com.pantrypalbackend.pantrypalbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {
    public final PasswordEncoder passwordEncoder;
    public final UserRepository userRepository;

    public AuthenticationResponse registerUser(UserRegistrationRequest userRegistrationRequest) {

        String encodedPassword = passwordEncoder.encode(userRegistrationRequest.getPassword());

        User user = User.builder()
                .firstName(userRegistrationRequest.getFirstName())
                .lastName(userRegistrationRequest.getLastName())
                .email(userRegistrationRequest.getEmail())
                .password(encodedPassword)
                .build();

        try {
            userRepository.save(user);
            return AuthenticationResponse.builder()
                    .message("User with email " + user.getEmail() + " was registered successfully!")
                    .build();
        } catch (Exception e) {
            throw new EmailAlreadyTakenException();
        }
    }

}
