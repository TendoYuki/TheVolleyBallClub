package com.volleyball.club.login.exceptions;

public class IncorrectLoginException extends Exception {
    public IncorrectLoginException() {
        super("Unknown user");
    }
}
