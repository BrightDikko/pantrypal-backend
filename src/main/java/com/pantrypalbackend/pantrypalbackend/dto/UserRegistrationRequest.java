package com.pantrypalbackend.pantrypalbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.pantrypalbackend.pantrypalbackend.constants.ErrorMessage.EMAIL_CANNOT_BE_EMPTY;
import static com.pantrypalbackend.pantrypalbackend.constants.ErrorMessage.EMPTY_FIRST_NAME;
import static com.pantrypalbackend.pantrypalbackend.constants.ErrorMessage.EMPTY_LAST_NAME;
import static com.pantrypalbackend.pantrypalbackend.constants.ErrorMessage.INCORRECT_EMAIL;
import static com.pantrypalbackend.pantrypalbackend.constants.ErrorMessage.PASSWORD_CHARACTER_LENGTH;

@Data
public class UserRegistrationRequest {

    @NotBlank(message = EMPTY_FIRST_NAME)
    private String firstName;

    @NotBlank(message = EMPTY_LAST_NAME)
    private String lastName;

    @Size(min = 6, max = 16, message = PASSWORD_CHARACTER_LENGTH)
    private String password;

    @Email(message = INCORRECT_EMAIL)
    @NotBlank(message = EMAIL_CANNOT_BE_EMPTY)
    private String email;
}
