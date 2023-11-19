package com.volleyball.club.pages.trainings;

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
import com.volleyball.club.observation.Observable;
import com.volleyball.club.pages.EditPage;

/** Edition page of the trainings */
public class TrainingEditPage extends EditPage{

    /** Editor section of the start time of the training */
    private EditorSectionDateTime startTimeEditorSection;
    /** Editor section of the start end of the training */
    private EditorSectionDateTime endTimeEditorSection;

    /**
     * Creates a new training edition page
     * @param trainingPage Linked training page 
     * @param model Model to edit
     * @param backupModel Backup of the model to edit before edition
     */
    public TrainingEditPage(TrainingPage trainingPage, TrainingModel model, TrainingModel backupModel) {
        super();

        setBorder(new EmptyBorder(new Insets(0, 20, 0, 20)));
        GridBagConstraints gbc = new GridBagConstraints();
        EmptyBorder esMargin = new EmptyBorder(new Insets(0, 0, 15, 0));
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        startTimeEditorSection = new EditorSectionDateTime(
            "Start Date Time",
            "Select the training's starting date and time",
            null,
            model.getEndDateTime()
        ) {
            @Override
            public void update(Observable observable) {
                setMaximumDateTime(((TrainingModel)observable).getEndDateTime());
                setValue(((TrainingModel)observable).getStartDateTime());
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
            "Select the training's ending date and time",
            model.getStartDateTime(),
            null
        ) {
            @Override
            public void update(Observable observable) {
                setMinimumDateTime(((TrainingModel)observable).getStartDateTime());
                setValue(((TrainingModel)observable).getEndDateTime());
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

        EditorActions ea = new EditorActions();
        gbc.gridx = 0;
        gbc.gridy = 2;
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
                        PreparedStatement stmt = con.prepareStatement("DELETE FROM training WHERE idTraining=?;");
                        stmt.setInt(1, model.getID());
                        stmt.execute();
                        model.resetDefaultValues();
                        trainingPage.loadResults();
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
                        "UPDATE training SET "+
                        "startDateTimeTraining=? ,"+
                        "endDateTimeTraining=? "+
                        "WHERE idTraining=?;"
                    );
                    stmt.setString(1, startTimeEditorSection.getValue().toString());
                    stmt.setString(2, endTimeEditorSection.getValue().toString());
                    stmt.setInt(3, model.getID());
                    stmt.execute();
                    trainingPage.loadResults();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        };

        model.addObserver(startTimeEditorSection);
        model.addObserver(endTimeEditorSection);
    }

    @Override
    public void clear() {
        startTimeEditorSection.clear();
        endTimeEditorSection.clear();
    }
}
