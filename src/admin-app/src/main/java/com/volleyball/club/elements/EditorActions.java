package com.volleyball.club.elements;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EditorActions extends JPanel{
    private JButton saveBtn;
    private JButton deleteBtn;
    private JButton cancelBtn;

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

    public void addOnSaveActionListener(ActionListener al) {
        saveBtn.addActionListener(al);
    }

    public void addOnCancelActionListener(ActionListener al) {
        cancelBtn.addActionListener(al);
    }

    public void addOnDeleteActionListener(ActionListener al) {
        deleteBtn.addActionListener(al);
    }
}
