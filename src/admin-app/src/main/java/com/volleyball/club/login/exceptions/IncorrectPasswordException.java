package com.volleyball.club.login.exceptions;

/** Exception occuring when the password is incorrect */
public class IncorrectPasswordException extends Exception {
    /** Invokes a new password exception */
    public IncorrectPasswordException() {
        super("Invalid password");
    }
}
