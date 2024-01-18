package com.volleyball.club.pages.events;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import com.volleyball.club.controllers.EditorActionController;
import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.datetime.DateTime;
import com.volleyball.club.elements.editor.EditorActions;
import com.volleyball.club.elements.editor.EditorSectionDateTime;
import com.volleyball.club.elements.editor.EditorSectionTextArea;
import com.volleyball.club.elements.editor.EditorSectionTextField;
import com.volleyball.club.observation.Observable;
import com.volleyball.club.pages.EditPage;

/** Edition page of the events */
public class EventEditPage extends EditPage{

    /** Editor section of the start time of the event */
    private EditorSectionDateTime startTimeEditorSection;
    /** Editor section of the start end of the event */
    private EditorSectionDateTime endTimeEditorSection;
    /** Editor section of the maxParticipants of the event */
    private EditorSectionTextField nameEditorSection;
    /** Editor section of the maxParticipants of the event */
    private EditorSectionTextArea descriptionEditorSection;

    /**
     * Creates a new event edition page
     * @param eventPage Linked event page 
     * @param model Model to edit
     * @param backupModel Backup of the model to edit before edition
     */
    public EventEditPage(EventPage eventPage, EventModel model, EventModel backupModel) {
        super();

        setBorder(new EmptyBorder(new Insets(0, 20, 0, 20)));
        GridBagConstraints gbc = new GridBagConstraints();
        EmptyBorder esMargin = new EmptyBorder(new Insets(0, 0, 15, 0));
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        startTimeEditorSection = new EditorSectionDateTime(
            "Start Date Time",
            "Select the event's starting date and time",
            null,
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
            public void update(Observable observable) {
                setValue(((EventModel)observable).getName());
            }
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
            public void update(Observable observable) {
                setValue(((EventModel)observable).getDescription());
            }
        };
        descriptionEditorSection.addModifyListener(arg0 -> {
            model.setDescription((String)descriptionEditorSection.getValue());
            model.updateObservers();
        });
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0;
        add(descriptionEditorSection, gbc);

        EditorActions ea = new EditorActions();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 1;
        gbc.weightx = 1;
        add(ea, gbc);


        new EditorActionController(ea) {
            @Override
            public void onCancel() {
                // Loads the previous state into the current active model
                model.setID(backupModel.getID());
                model.setStartDateTime(backupModel.getStartDateTime());
                model.setEndDateTime(backupModel.getEndDateTime());
                model.setName(backupModel.getName());
                model.setDescription(backupModel.getDescription());
                model.updateObservers();
            }
            @Override
            public void onDelete() {
                int res = JOptionPane.showConfirmDialog(
                    null,
                    "Do you really want to delete this entry",
                    "Delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                if(res == JOptionPane.YES_OPTION){
                    Connection con = DBConnectionManager.getConnection();
                    try{
                        PreparedStatement stmt = con.prepareStatement("DELETE FROM event WHERE idEvent=?;");
                        stmt.setInt(1, model.getID());
                        stmt.execute();
                        model.resetDefaultValues();
                        eventPage.loadResults();
                    }catch(Exception e){
                        System.out.println(e);
                    }

                }
            }
            @Override
            public void onSave() {
                Connection con = DBConnectionManager.getConnection();
                try{
                    PreparedStatement stmt = con.prepareStatement(
                        "UPDATE event SET "+
                        "startDateTimeEvent=? ,"+
                        "endDateTimeEvent=?, "+
                        "nameEvent=?,"+
                        "descEvent=? "+
                        "WHERE idEvent=?;"
                    );
                    stmt.setString(1, startTimeEditorSection.getValue().toString());
                    stmt.setString(2, endTimeEditorSection.getValue().toString());
                    stmt.setString(3, (String)nameEditorSection.getValue());
                    stmt.setString(4, (String)descriptionEditorSection.getValue());
                    stmt.setInt(5, model.getID());
                    stmt.execute();
                    eventPage.loadResults();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        };

        model.addObserver(startTimeEditorSection);
        model.addObserver(endTimeEditorSection);
        model.addObserver(nameEditorSection);
        model.addObserver(descriptionEditorSection);
    }

    @Override
    public void clear() {
        startTimeEditorSection.clear();
        endTimeEditorSection.clear();
        nameEditorSection.clear();
        descriptionEditorSection.clear();
    }
}
