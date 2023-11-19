package com.volleyball.club.datetime.exceptions;

public class InvalidDateTimeFormatException extends Exception{
    public InvalidDateTimeFormatException() {
        super("The provided date isn't parsable ( incorrect formatting ), make sure the date you provided is in the following format : YYYY-MM-DD HH:mm:ss");
    }
}
