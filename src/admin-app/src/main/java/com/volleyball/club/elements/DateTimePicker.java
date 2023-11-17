package com.volleyball.club.elements;

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
        // TODO : dt splitting
    }
}
