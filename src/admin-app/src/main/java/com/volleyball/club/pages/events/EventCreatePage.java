package com.volleyball.club.pages.events;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.datetime.DateTime;
import com.volleyball.club.elements.editor.EditorSectionDateTime;
import com.volleyball.club.elements.editor.EditorSectionTextArea;
import com.volleyball.club.elements.editor.EditorSectionTextField;
import com.volleyball.club.observation.Observable;
import com.volleyball.club.pages.CreatePage;

/** Page used to create a event entry */
public class EventCreatePage extends CreatePage{ 

    /** Editor section of the start time of the event */
    private EditorSectionDateTime startTimeEditorSection;
    /** Editor section of the start end of the event */
    private EditorSectionDateTime endTimeEditorSection;
    /** Editor section of the maxParticipants of the event */
    private EditorSectionTextField nameEditorSection;
    /** Editor section of the maxParticipants of the event */
    private EditorSectionTextArea descriptionEditorSection;

    /** Model of the event getting created */
    private EventModel model = new EventModel();

    /** Creates a event creation page */
    public EventCreatePage() {
        super();

        setBorder(new EmptyBorder(new Insets(0, 20, 0, 20)));
        GridBagConstraints gbc = new GridBagConstraints();
        EmptyBorder esMargin = new EmptyBorder(new Insets(0, 0, 15, 0));
        gbc.anchor = GridBagConstraints.CENTER;

        startTimeEditorSection = new EditorSectionDateTime(
            "Start Date Time",
            "Select the event's starting date and time",
            DateTime.now(),
            model.getEndDateTime()
        ) {
            @Override
            public void update(Observable observable) {
                setMaximumDateTime(((EventModel)observable).getEndDateTime());
                setValue(((EventModel)observable).getStartDateTime());
            }
        };
        startTimeEditorSection.addModifyListener(arg0 -> {
            model.setStartDateTime((DateTime)startTimeEditorSection.getValue());
            model.updateObservers();
        });

        startTimeEditorSection.setBorder(esMargin);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        add(startTimeEditorSection, gbc);

        endTimeEditorSection = new EditorSectionDateTime(
            "End Date Time",
            "Select the event's ending date and time",
            model.getStartDateTime(),
            null
        ) {
            @Override
            public void update(Observable observable) {
                setMinimumDateTime(((EventModel)observable).getStartDateTime());
                setValue(((EventModel)observable).getEndDateTime());
                setValue(null);
            }
        };
        endTimeEditorSection.addModifyListener(arg0 -> {
            model.setEndDateTime((DateTime)endTimeEditorSection.getValue());
            model.updateObservers();
        });

        endTimeEditorSection.setBorder(esMargin);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0;
        add(endTimeEditorSection, gbc);

        nameEditorSection = new EditorSectionTextField(
            "Name",
            "Name of the event"
        ) {
            @Override
            public void update(Observable observable) {}
        };

        nameEditorSection.addModifyListener(arg0 -> {
            model.setName((String)nameEditorSection.getValue());
            model.updateObservers();
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        add(nameEditorSection, gbc);

        descriptionEditorSection = new EditorSectionTextArea(
            "Description",
            "A small description of the even"
        ) {
            @Override
            public void update(Observable observable) {}
        };
        descriptionEditorSection.addModifyListener(arg0 -> {
            model.setDescription((String)descriptionEditorSection.getValue());
            model.updateObservers();
        });
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0;
        add(descriptionEditorSection, gbc);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(event -> {
            try{
                Connection con = DBConnectionManager.getConnection();
                PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO event (startDateTimeEvent, endDateTimeEvent, nameEvent, descEvent) VALUES (?,?,?,?);"
                );
                stmt.setString(1, startTimeEditorSection.getValue().toString());
                stmt.setString(2, endTimeEditorSection.getValue().toString());
                stmt.setString(3, (String)nameEditorSection.getValue());
                stmt.setString(4, (String)descriptionEditorSection.getValue());
                stmt.execute();
                JOptionPane.showMessageDialog(null, "Entry successfully created","Success", JOptionPane.INFORMATION_MESSAGE);
                clear();
            }catch(Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "An error occured","Error", JOptionPane.ERROR_MESSAGE);
            }
            clear();
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 0;
        gbc.weightx = 1;
        add(submitButton, gbc);

        model.addObserver(startTimeEditorSection);
        model.addObserver(endTimeEditorSection);
        model.addObserver(nameEditorSection);
        model.addObserver(descriptionEditorSection);
    }
    @Override
    public void clear() {
        model.resetDefaultValues();
        startTimeEditorSection.clear();
        endTimeEditorSection.clear();
        nameEditorSection.clear();
        descriptionEditorSection.clear();
    }
}
