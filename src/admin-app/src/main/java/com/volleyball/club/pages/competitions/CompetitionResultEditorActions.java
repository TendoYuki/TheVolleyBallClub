package com.volleyball.club.pages.competitions;

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
public class CompetitionResultEditorActions extends JPanel{
    /** Save button element */
    private JButton saveBtn;
    /** Delete button element */
    private JButton cancelBtn;
    /** Cancel button element */
    private JButton backBtn;

    /** Creates an editor action element */
    public CompetitionResultEditorActions() {
        super();
        setLayout(new GridBagLayout());
        saveBtn = new JButton("Save");
        saveBtn.setBackground(new Color(152, 255, 120));
        cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(255, 110, 112));
        backBtn = new JButton("Back");
        backBtn.setBackground(new Color(156, 156, 156));

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
        add(cancelBtn, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.gridwidth=GridBagConstraints.REMAINDER;
        add(backBtn, gbc);
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

    public void addOnBackActionListener(ActionListener al) {
        backBtn.addActionListener(al);
    }
}
