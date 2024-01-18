package com.volleyball.club.elements.editor;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        editorComponent = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(editorComponent); 
        setMinimumSize(new Dimension(250, 130));
        editorComponent.setLineWrap(true);
        editorComponent.setWrapStyleWord(true);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 20, 0);
        add(scrollPane, gbc);
    }

    @Override
    public void setValue(Object newValue) {
        ((JTextArea)editorComponent).setText((String)newValue);
    }
    
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
    public Object getValue() {
        return ((JTextArea)editorComponent).getText();
    }

    @Override
    public void clear() {
        editorComponent.setText("");
    }

}
