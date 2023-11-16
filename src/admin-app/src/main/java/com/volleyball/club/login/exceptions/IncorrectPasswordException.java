package com.volleyball.club.login.exceptions;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super("Invalid password");
    }
}
