package com.volleyball.club.views;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTime implements Comparable<DateTime>{
    
    private LocalDate ld;
    private LocalTime lt;

    public DateTime(LocalDate ld, LocalTime lt) {
        this.ld = ld;
        this.lt = lt;
    }
    
    /**
     * Creates a datetime using the following format YYYY-MM-DD HH:mm:ss
     * @param dateTime Datetime string
     */
    public DateTime(String dateTime) {
        String[] parts = dateTime.split(" ");
        String date = parts[0];
        String time = parts[1];
        this.ld = LocalDate.parse(date);
        this.lt = LocalTime.parse(time);
    }
    
    /**
     * Creates a datetime from a date and a time
     * @param date Date in format YYYY-MM-DD
     * @param time Time in format HH:mm:ss
     */
    public DateTime(String date, String time) {
        this.ld = LocalDate.parse(date);
        this.lt = LocalTime.parse(time);
    }

    /**
     * Creates a datetime from a datetime (cloning)
     * @param dateTime Datetime object
     */
    public DateTime(DateTime dateTime) {
        this.ld = LocalDate.parse(dateTime.getLocalDate().toString());
        this.lt = LocalTime.parse(dateTime.getLocalTime().toString());
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
     */
    @Override
    public int compareTo(DateTime other) {
        int date = ld.compareTo(other.ld);
        return date != 0 ? date : lt.compareTo(other.lt) ;
    }
    
    @Override
    public String toString() {
        return ld.toString() + " " + lt.toString();
    }
}
