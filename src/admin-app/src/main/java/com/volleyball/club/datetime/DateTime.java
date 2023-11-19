package com.volleyball.club.datetime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import com.volleyball.club.datetime.exceptions.InvalidDateTimeFormatException;

/**
 * Class representing a Date and Time, its comparable and can parse string containing
 * datetimes in the format YYYY-MM-DD HH:mm:ss
 */
public class DateTime implements Comparable<DateTime>{
    
    /** Local date */
    private LocalDate ld;
    /** Local time */
    private LocalTime lt;

    /**
     * Creates a datetime from a localdate and a localtime
     * @param ld Local Date
     * @param lt Local Time
     */
    public DateTime(LocalDate ld, LocalTime lt) {
        this.ld = ld;
        if(lt != null) this.lt = lt.truncatedTo(ChronoUnit.SECONDS);
    }
    
    /**
     * Creates a datetime using the following format YYYY-MM-DD HH:mm:ss
     * @param dateTime Datetime string
     */
    public DateTime(String dateTime) throws InvalidDateTimeFormatException{
        try {
            String[] parts = dateTime.split(" ");
            String date = parts[0];
            String time = parts[1];
            this.ld = LocalDate.parse(date);
            if(lt != null) this.lt = LocalTime.parse(time).truncatedTo(ChronoUnit.SECONDS);
        } catch (Exception e) {
            throw new InvalidDateTimeFormatException();
        }
    }
    
    /**
     * Creates a datetime from a date and a time
     * @param date Date in format YYYY-MM-DD
     * @param time Time in format HH:mm:ss
     */
    public DateTime(String date, String time) {
        this.ld = LocalDate.parse(date);
        this.lt = LocalTime.parse(time).truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * Creates a datetime from a datetime (cloning)
     * @param dateTime Datetime object
     */
    public DateTime(DateTime dateTime) {
        this.ld = LocalDate.parse(dateTime.getLocalDate().toString());
        if(lt != null)
            this.lt = LocalTime.parse(
                dateTime.getLocalTime().toString()
            ).truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * 
     * @return LocalDate of the datetime
     */
    public LocalDate getLocalDate(){
        return ld;
    }

    /**
     * Sets the localDate of the datetime
     * @param localDate new localDate
     */
    public void setLocalDate(LocalDate localDate){
        ld = localDate;
    }
    
    /**
     * 
     * @return LocalTime of the datetime
     */
    public LocalTime getLocalTime(){
        return lt;
    }

    /**
     * Sets the localTime of the datetime
     * @param localTime new localTime
     */
    public void setLocalTime(LocalTime localTime){
        lt = localTime;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof DateTime)) return false;
        DateTime other = (DateTime)obj;
        return (
            toString().equals(other.toString())
        );
    }

    /**
     * Returns -1 if before other,
     * Returns 0 if equals
     * Returns 1 if after other
     * @param other Compared to
     */
    @Override
    public int compareTo(DateTime other) {
        int date = ld.compareTo(other.ld);
        if(lt != null) return date != 0 ? date : lt.compareTo(other.lt) ;
        else return date;
    }
    
    @Override
    public String toString() {
        if(lt != null)
            return ld.toString() + " " + lt.toString();
        else 
            return ld.toString();
    }
    
    /**
     * Creates a new datetime from the current local date and time
     * @return Current date and time
     */
    public static DateTime now() {
        return new DateTime(LocalDate.now(), LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
    }
}
