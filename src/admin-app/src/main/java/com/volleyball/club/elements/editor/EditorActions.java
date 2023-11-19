package com.volleyball.club.elements.editor;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Editor action element that has a delete save and cancel button with listeners
 */
public class EditorActions extends JPanel{
    /** Save button element */
    private JButton saveBtn;
    /** Delete button element */
    private JButton deleteBtn;
    /** Cancel button element */
    private JButton cancelBtn;

    /** Creates an editor action element */
    public EditorActions() {
        super();
        setLayout(new GridBagLayout());
        saveBtn = new JButton("Save");
        saveBtn.setBackground(new Color(152, 255, 120));
        deleteBtn = new JButton("Delete");
        deleteBtn.setBackground(new Color(255, 110, 112));
        cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(156, 156, 156));

        Insets btnBorders = new Insets(0, 5, 0, 0);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = btnBorders;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        add(saveBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        add(deleteBtn, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.gridwidth=GridBagConstraints.REMAINDER;
        add(cancelBtn, gbc);
    }

    /**
     * Adds an action listener called when the editor has its save button pressed
     * @param al Action listener 
     */
    public void addOnSaveActionListener(ActionListener al) {
        saveBtn.addActionListener(al);
    }

    /**
     * Adds an action listener called when the editor has its cancel button pressed
     * @param al Action listener 
     */
    public void addOnCancelActionListener(ActionListener al) {
        cancelBtn.addActionListener(al);
    }
    /**
     * Adds an action listener called when the editor has its delete button pressed
     * @param al Action listener 
     */

    public void addOnDeleteActionListener(ActionListener al) {
        deleteBtn.addActionListener(al);
    }
}
