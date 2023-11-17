package com.volleyball.club.elements;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JPanel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

public class DateTimePicker extends JPanel{
    TimePicker timePicker;
    DatePicker datePicker;

    public DateTimePicker() {
        super();
        timePicker = new TimePicker();
        datePicker = new DatePicker();
        add(timePicker);
        add(datePicker);
    }

    public String getDateTime() {
        return datePicker.getDate() +  " " + timePicker.getTime();
    }

    public void setDateTime(String dateTime) {
        String[] parts = dateTime.split(" ");
        String date = parts[0];
        String time = parts[1];
        LocalDate ld = LocalDate.parse(date);
        LocalTime lt = LocalTime.parse(time);
        timePicker.setTime(lt);
        datePicker.setDate(ld);
    }
}
