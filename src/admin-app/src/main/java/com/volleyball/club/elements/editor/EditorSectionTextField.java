package com.volleyball.club.elements.editor;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Editor section that has an editable text field 
 */
public abstract class EditorSectionTextField extends EditorSection {
    /** Text field */
    private JTextField editorComponent;

    /**
     * Creates a new editor section with text field
     * @param name Name of the section
     * @param description Description of the section
     */
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
        ((JTextField)editorComponent).setText((String)newValue);
    }

    @Override
    public Object getValue() {
        return ((JTextField)editorComponent).getText();
    }

    /**
     * Adds a listener called when the value changes
     * @param al ActionListener
     */
    @Override
    public void addModifyListener(ActionListener al) {
        editorComponent.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                al.actionPerformed(null);
            }
        });
    }

    @Override
    public void clear() {
        editorComponent.setText("");
    }

}
