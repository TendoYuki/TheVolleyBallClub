package com.volleyball.club.elements;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import com.volleyball.club.datetime.DateTime;

/**
 * Date time picker element allowing to pick a date and time
 */
public class DateTimePicker extends JPanel{
    /** Time picker element */
    private TimePicker timePicker;
    /** Date picker element */
    private DatePicker datePicker;
    /** Minimum date time of the date and time element */
    private DateTime minimumDateTime;
    /** Maximum date time of the date and time element */
    private DateTime maximumDateTime;
    /** Whether or not the input is made by the end user */
    private boolean manualInput = true;

    /**
     * Creates a date time picker with boundaries
     * @param minmumDateTime Maximum allowed date
     * @param maximumDateTime Minimum allowed date
     */
    public DateTimePicker(DateTime minmumDateTime, DateTime maximumDateTime) {
        this();
        this.minimumDateTime = minmumDateTime;
        this.maximumDateTime = maximumDateTime;
    }

    /**
     * Creates a date time picker without boundaries
     */
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
     * Returns the currently selected datetime of the picker
     * @return Currently selected datetime of the picker
     */
    public DateTime getDateTime() {
        return new DateTime(datePicker.getDate(), timePicker.getTime());
    }

    /**
     * Clears the datetime picker's fields
     */
    public void clear() {
        datePicker.clear();
        timePicker.clear();
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

    /**
     * Returns the minimum date time allowed
     * @return Minimum date time allowed
     */
    public DateTime getMinimumDateTime() {
        return minimumDateTime;
    }

    /**
     * Changes the minimum date time allowed
     * @param minmumDateTime
     */
    public void setMinimumDateTime(DateTime minmumDateTime) {
        this.minimumDateTime = minmumDateTime;
    }

    /**
     * Returns the maximum date time allowed
     * @return
     */
    public DateTime getMaximumDateTime() {
        return maximumDateTime;
    }

    /**
     * Changes the maximum date time allowed
     * @param maximumDateTime
     */
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
