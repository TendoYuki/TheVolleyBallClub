package com.volleyball.club.elements.editor;

import javax.swing.JTextArea;

import java.awt.GridBagConstraints;

/**
 * Editor section that has an editable text area 
 */
public abstract class EditorSectionTextArea extends EditorSection {
    /** Text area */
    private JTextArea editorComponent;

    /**
     * Creates a new editor section with text area
     * @param name Name of the section
     * @param description Description of the section
     */
    public EditorSectionTextArea(String name, String description) {
        super(name, description);
        editorComponent = new JTextArea();

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(editorComponent, gbc);
    }

    @Override
    public void setValue(Object newValue) {
        ((JTextArea)editorComponent).setText((String)newValue);
    }

    @Override
    public Object getValue() {
        return ((JTextArea)editorComponent).getText();
    }

}
