package com.pantrypalbackend.pantrypalbackend.exceptions;

public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException() {
        super("The email provided is already taken");
    }
}
