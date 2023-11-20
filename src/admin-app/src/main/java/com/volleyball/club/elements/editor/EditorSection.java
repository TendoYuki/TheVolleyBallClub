package com.volleyball.club.elements.editor;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.volleyball.club.observation.Observer;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;

/**
 * Generic editable section that has a name and a description
 */
public abstract class EditorSection extends JPanel implements Observer{
    private JLabel descLabel;
    private JLabel nameLabel;

    public EditorSection(String name, String description) {
        super(new GridBagLayout());
        nameLabel = new JLabel(name, SwingConstants.LEFT);
        descLabel = new JLabel(description, SwingConstants.LEFT);
        descLabel.setForeground(new Color(136,136,136));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(nameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(descLabel, gbc);
    }

    /**
     * Sets the value of the editor section
     * @param newValue New value of the editor section
     */
    public abstract void setValue(Object newValue);

    /**
     * 
     * @return Value of the editor section
     */
    public abstract Object getValue();

    /**
     * Clears the editor section
     */
    public abstract void clear();

    /** Adds a listener for when the section's value changes */
    public abstract void addModifyListener(ActionListener al);
}
