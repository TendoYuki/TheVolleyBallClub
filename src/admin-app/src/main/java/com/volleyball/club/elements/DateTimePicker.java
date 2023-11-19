package com.volleyball.club.elements;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.volleyball.club.views.DateTime;

public class DateTimePicker extends JPanel{
    private TimePicker timePicker;
    private DatePicker datePicker;
    private DateTime minimumDateTime;
    private DateTime maximumDateTime;
    private boolean manualInput = true;

    public DateTimePicker(DateTime minmumDateTime, DateTime maximumDateTime) {
        this();
        this.minimumDateTime = minmumDateTime;
        this.maximumDateTime = maximumDateTime;
    }

    public DateTimePicker() {
        super();
        timePicker = new TimePicker();
        datePicker = new DatePicker();
        timePicker.addTimeChangeListener(event -> {
            checkValidity();
        });
        datePicker.addDateChangeListener(event -> {
            checkValidity();
        });
        add(timePicker);
        add(datePicker);
    }

    /** Checks if the datetime stored is between the lower and upper bound,
     *  IF not, clamps its value to the nearest bound
     */
    private void checkValidity() {
        if(!manualInput) return;
        if(datePicker.getDate() == null || timePicker.getTime() == null) return;
        
        DateTime dateTime = new DateTime(datePicker.getDate(), timePicker.getTime());

        // Checks if the date is before the upper bound
        // IF not makes its value equal to the upper bound
        if(maximumDateTime != null && dateTime.compareTo(maximumDateTime) > 0) {
            JOptionPane.showMessageDialog(null, "Time cannot be after " + maximumDateTime,"Error", JOptionPane.ERROR_MESSAGE);
            setDateTime(maximumDateTime);
        }
        
        // Checks if the date is after the lower bound
        // IF not makes its value equal to the lower bound
        if(minimumDateTime != null && dateTime.compareTo(minimumDateTime) < 0) {
            JOptionPane.showMessageDialog(null, "Time cannot be before " + minimumDateTime,"Error", JOptionPane.ERROR_MESSAGE);
            setDateTime(minimumDateTime);
        }
    }

    /**
     * Returns the current selected datetime of the picker
     * @return
     */
    public DateTime getDateTime() {
        return new DateTime(datePicker.getDate(), timePicker.getTime());
    }

    /**
     * Sets the current selected datetime of the picker
     * @param dateTime
     */
    public void setDateTime(DateTime dateTime) {
        manualInput = false;
        timePicker.setTime(dateTime.getLocalTime());
        datePicker.setDate(dateTime.getLocalDate());
        manualInput = true;
    }

    public DateTime getMinimumDateTime() {
        return minimumDateTime;
    }

    public void setMinimumDateTime(DateTime minmumDateTime) {
        this.minimumDateTime = minmumDateTime;
    }

    public DateTime getMaximumDateTime() {
        return maximumDateTime;
    }

    public void setMaximumDateTime(DateTime maximumDateTime) {
        this.maximumDateTime = maximumDateTime;
    } 

    /**
     * Adds a listener called when either the time or the date changes by user action
     * @param al Action listener
     */
    public void addModifyListener(ActionListener al) {
        timePicker.addTimeChangeListener(event -> {
            if(!manualInput) return;
            if(datePicker.getDate() == null || timePicker.getTime() == null) return;
            al.actionPerformed(null);
        });
        datePicker.addDateChangeListener(event -> {
            if(!manualInput) return;
            if(datePicker.getDate() == null || timePicker.getTime() == null) return;
            al.actionPerformed(null);
        });
    }
}
