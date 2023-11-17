package com.volleyball.club.views.trainings;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import com.volleyball.club.elements.DateTimePicker;
import com.volleyball.club.elements.EditorActions;
import com.volleyball.club.elements.EditorSection;
import com.volleyball.club.elements.EditorType;
import com.volleyball.club.views.EditPage;

public class TrainingEditPage extends EditPage{

    JLabel startDTLabel;
    DateTimePicker startDTPicker;

    JLabel endDTLabel;
    DateTimePicker endDTPicker;
    
    EditorSection es1;
    EditorSection es2;


    public TrainingEditPage() {
        super();
        setBorder(new EmptyBorder(new Insets(0, 20, 0, 20)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        
        EmptyBorder esMargin = new EmptyBorder(new Insets(0, 0, 15, 0));

        es1 = new EditorSection("Start Date Time", "Select the training's starting date and time", EditorType.DATE_TIME);
        es1.setBorder(esMargin);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        add(es1, gbc);

        es2 = new EditorSection("End Date Time", "Select the training's ending date and time", EditorType.DATE_TIME);
        es2.setBorder(esMargin);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0;
        add(es2, gbc);

        EditorActions ea = new EditorActions();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1;
        gbc.weightx = 1;
        add(ea, gbc);
    }

    public void changeStart(String s){
        es1.setValue(s);
    }


    public void changeEnd(String e){
        es2.setValue(e);
    }
    
}
