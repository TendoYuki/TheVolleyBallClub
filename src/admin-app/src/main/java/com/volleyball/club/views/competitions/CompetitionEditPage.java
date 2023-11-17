package com.volleyball.club.views.competitions;

import javax.swing.JLabel;

import com.volleyball.club.elements.DateTimePicker;
import com.volleyball.club.elements.EditorSection;
import com.volleyball.club.elements.EditorType;
import com.volleyball.club.views.EditPage;

public class CompetitionEditPage extends EditPage{

    JLabel startDTLabel;
    DateTimePicker startDTPicker;

    JLabel endDTLabel;
    DateTimePicker endDTPicker;

    public CompetitionEditPage() {
        super();
        EditorSection es1 = new EditorSection("Start Date Time", "Select the event's starting date and time", EditorType.DATE_TIME);
        add(es1);

    }
}
