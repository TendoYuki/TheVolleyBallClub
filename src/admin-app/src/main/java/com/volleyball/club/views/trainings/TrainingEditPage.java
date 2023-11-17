package com.volleyball.club.views.trainings;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import com.volleyball.club.elements.DateTimePicker;
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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipady = 15;
        
        es1 = new EditorSection("Start Date Time", "Select the training's starting date and time", EditorType.DATE_TIME);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(es1, gbc);
        
        es2 = new EditorSection("End Date Time", "Select the training's ending date and time", EditorType.DATE_TIME);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(es2, gbc);
    }

    public void changeStart(String s){
        es1.setValue(s);
    }


    public void changeEnd(String e){
        es2.setValue(e);
    }
    
}
