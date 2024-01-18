package com.volleyball.club.elements.editor;

import javax.swing.JComboBox;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

/**
 * Editor section that has an editable drop down 
 */
public abstract class EditorSectionDropDown extends EditorSection {
    /** drop down */
    private JComboBox<String> editorComponent;

    /**
     * Creates a new editor section with drop down
     * @param name Name of the section
     * @param description Description of the section
     */
    public EditorSectionDropDown(String name, String description, String[] choices) {
        super(name, description);
        editorComponent = new JComboBox<String>();
        setChoices(choices);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 20, 0);
        add(editorComponent, gbc);
    }

    /**
     * Changes the choices available 
     * @param choices List of choices
     */
    public void setChoices(String[] choices) {
        editorComponent.removeAllItems();
        editorComponent.addItem("");
        for (String entry : choices) {
            editorComponent.addItem((String)entry);
        }
    }

    @Override
    public void setValue(Object newValue) {
        editorComponent.setSelectedItem(newValue);
    }

    @Override
    public Object getValue() {
        return editorComponent.getSelectedItem();
    }

    /**
     * Adds a listener called when the value changes
     * @param al ActionListener
     */
    @Override
    public void addModifyListener(ActionListener al) {
        editorComponent.addActionListener(al);
    }

    @Override
    public void clear() {
        editorComponent.setSelectedItem(0);
    }

}
