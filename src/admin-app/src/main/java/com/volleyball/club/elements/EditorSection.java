package com.volleyball.club.elements;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class EditorSection extends JPanel{
    Component editorComponent;
    EditorType edType;

    String desc;
    JLabel descLabel;

    String name;
    JLabel nameLabel;

    public EditorSection(String name, String description, EditorType type) {
        super(new GridBagLayout());
        this.name = name;
        this.desc = description;
        this.edType = type;
        switch (edType) {
            case DATE_TIME:
                editorComponent = new DateTimePicker();
                break;
            case TEXT_AREA:
                editorComponent = new JTextArea();
                break;
            case TEXT_FIELD:
                editorComponent = new JTextField();
                break;
            default:
                return;
        }
        nameLabel = new JLabel(name, SwingConstants.LEFT);
        descLabel = new JLabel(desc, SwingConstants.LEFT);
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

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(editorComponent, gbc);
    }

    public void setValue(Object newValue) {
        switch (edType) {
            case DATE_TIME:
                ((DateTimePicker)editorComponent).setDateTime((String)newValue);
                break;
            case TEXT_AREA:
                ((JTextArea)editorComponent).setText((String)newValue);
                break;
            case TEXT_FIELD:
                ((JTextField)editorComponent).setText((String)newValue);
                break;
            default:
                return;
        }
    }

    public Object getValue() {
        switch (edType) {
            case DATE_TIME:
                return ((DateTimePicker)editorComponent).getDateTime();
            case TEXT_AREA:
                return ((JTextArea)editorComponent).getText();
            case TEXT_FIELD:
                return ((JTextField)editorComponent).getText();
            default:
                return null;
        }
    }
}
