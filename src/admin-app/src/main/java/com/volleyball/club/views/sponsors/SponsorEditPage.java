package com.volleyball.club.views.sponsors;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import com.volleyball.club.elements.DateTimePicker;
import com.volleyball.club.elements.EditorSection;
import com.volleyball.club.elements.EditorType;
import com.volleyball.club.views.EditPage;

public class SponsorEditPage extends EditPage{

    JLabel startDTLabel;
    DateTimePicker startDTPicker;

    JLabel endDTLabel;
    DateTimePicker endDTPicker;

    public SponsorEditPage() {
        super();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipady = 15;
        
        EditorSection es1 = new EditorSection("Name", "Change the name of the sponsor", EditorType.TEXT_FIELD);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(es1, gbc);

        // EditorSection es2 = new EditorSection("End Date Time", "Select the training's ending date and time", EditorType.DATE_TIME);

        // gbc.gridx = 0;
        // gbc.gridy = 1;
        // add(es2, gbc);
    }
}