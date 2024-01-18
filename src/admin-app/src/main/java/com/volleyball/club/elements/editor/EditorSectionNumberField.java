package com.volleyball.club.elements.editor;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SpinnerNumberModel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

/**
 * Editor section that has an editable number field 
 */
public abstract class EditorSectionNumberField extends EditorSection {
    /** Numeric field */
    private JSpinner editorComponent;
    /** Spinner model */
    private SpinnerNumberModel spinnerModel;
    /** Initial value of the field */
    private int initialValue;

    /**
     * Creates a new editor section with number field
     * @param name Name of the section
     * @param description Description of the section
     * @param min Minimum value
     * @param max Maximum value
     * @param step Value of each step
     * @param initialValue Starting value
     */
    public EditorSectionNumberField(String name, String description, int min, int max, int step, int initialValue) {
        super(name, description);
        this.initialValue = initialValue; 
        spinnerModel = new SpinnerNumberModel(
            initialValue,
            min,
            max,
            step
        );
        editorComponent = new JSpinner(spinnerModel);

        // Prevents text input
        ((JSpinner.DefaultEditor)editorComponent.getEditor()).getTextField().setEditable(false);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 20, 0);
        add(editorComponent, gbc);
    }

    @Override
    public void setValue(Object newValue) {
        spinnerModel.setValue(newValue);
    }

    @Override
    public Object getValue() {
        return spinnerModel.getValue();
    }

    /**
     * Adds a listener called when the value changes
     * @param al ActionListener
     */
    @Override
    public void addModifyListener(ActionListener al) {
        spinnerModel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent arg0) {
                al.actionPerformed(null);
            }
        });
    }

    @Override
    public void clear() {
        spinnerModel.setValue(initialValue);
    }
}
