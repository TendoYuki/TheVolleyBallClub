package com.volleyball.club.pages.competitions;

import java.awt.GridBagConstraints;

import com.volleyball.club.pages.EditPage;

/** Edition page of the competitions */
public class CompetitionEditPage extends EditPage{
    /** Creates an edition page for the competitions */
    public CompetitionEditPage() {
        super();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipady = 15;
        
        // EditorSection es1 = new EditorSection("Start Date Time", "Select the training's starting date and time", EditorType.DATE_TIME);

        // gbc.gridx = 0;
        // gbc.gridy = 0;
        // add(es1, gbc);

        // EditorSection es2 = new EditorSection("End Date Time", "Select the training's ending date and time", EditorType.DATE_TIME);

        // gbc.gridx = 0;
        // gbc.gridy = 1;
        // add(es2, gbc);
    }
    @Override
    public void clear() {
        
    }
}
