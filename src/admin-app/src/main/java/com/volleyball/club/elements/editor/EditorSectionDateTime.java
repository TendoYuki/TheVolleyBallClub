package com.volleyball.club.elements.editor;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import com.volleyball.club.datetime.DateTime;
import com.volleyball.club.elements.DateTimePicker;

/**
 * Editor section that has an editable datetime field 
 */
public abstract class EditorSectionDateTime extends EditorSection {
    /** Datetime picker */
    private DateTimePicker editorComponent;

    /**
     * Creates a new editor section with datetime field
     * @param name Name of the section
     * @param description Description of the section
     * @param minimumDateTime Minimum datetime that is selectable
     * @param maximumDateTime Maximum datetime that is selectable
     */
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

    /**
     * Clears the datetime picker's fields
     */
    @Override
    public void clear() {
        editorComponent.clear();
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
    @Override
    public void addModifyListener(ActionListener al) {
        editorComponent.addModifyListener(al);
    }
    
}
