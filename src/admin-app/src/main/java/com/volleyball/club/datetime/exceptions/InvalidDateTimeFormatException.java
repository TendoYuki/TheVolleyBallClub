package com.volleyball.club.datetime.exceptions;

/**
 * Exception that occurs when a datetime cannot be parsed due to an invalid format
 */
public class InvalidDateTimeFormatException extends Exception{
    /**
     * Invokes a new InvalidDateTimeFormatException
     */
    public InvalidDateTimeFormatException() {
        super("The provided date isn't parsable ( incorrect formatting ), make sure the date you provided is in the following format : YYYY-MM-DD HH:mm:ss");
    }
}
