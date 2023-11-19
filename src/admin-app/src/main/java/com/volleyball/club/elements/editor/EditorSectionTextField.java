package com.volleyball.club.elements.editor;

import java.awt.Component;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;

public abstract class EditorSectionTextField extends EditorSection {
    private Component editorComponent;

    public EditorSectionTextField(String name, String description) {
        super(name, description);
        editorComponent = new JTextField();

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
        return ((JTextField)editorComponent).getText();
    }

}
