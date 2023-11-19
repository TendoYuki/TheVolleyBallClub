package com.volleyball.club.elements;

import com.volleyball.club.views.DateTime;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

public abstract class EditorSectionDateTime extends EditorSection {
    private DateTimePicker editorComponent;

    public EditorSectionDateTime(String name, String description, DateTime minimumDateTime, DateTime maximumDateTime) {
        super(name, description);

        editorComponent = new DateTimePicker(minimumDateTime, maximumDateTime);

        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(editorComponent, gbc);
    }

    @Override
    public void setValue(Object newValue) {
        if(!(newValue instanceof DateTime)) return;
        ((DateTimePicker)editorComponent).setDateTime((DateTime)newValue);
    }

    @Override
    public Object getValue() {
        return ((DateTimePicker)editorComponent).getDateTime();
    }

    /**
     * Changes the minimum datetieme allowed
     * @param minimumDateTime
     */
    public void setMinimumDateTime(DateTime minimumDateTime) {
        this.editorComponent.setMinimumDateTime(minimumDateTime);
    }

    /**
     * Changes the maximum datetieme allowed
     * @param maximumDateTime
     */
    public void setMaximumDateTime(DateTime maximumDateTime) {
        this.editorComponent.setMaximumDateTime(maximumDateTime);
    }

    /**
     * Adds a listener called when either the time or the date changes by user action
     * @param al Action listener
     */
    public void addModifyListener(ActionListener al) {
        editorComponent.addModifyListener(al);
    }
    
}
