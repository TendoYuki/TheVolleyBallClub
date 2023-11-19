package com.volleyball.club.login.exceptions;

/** Exception occuring when the login is incorrect */
public class IncorrectLoginException extends Exception {
    /** Invokes a new login exception */
    public IncorrectLoginException() {
        super("Unknown user");
    }
}
