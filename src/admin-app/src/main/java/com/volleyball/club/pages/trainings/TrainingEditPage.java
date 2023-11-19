package com.volleyball.club.pages.trainings;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import com.volleyball.club.controllers.EditorActionController;
import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.datetime.DateTime;
import com.volleyball.club.elements.editor.EditorActions;
import com.volleyball.club.elements.editor.EditorSectionDateTime;
import com.volleyball.club.mvc.Observable;
import com.volleyball.club.pages.EditPage;

public class TrainingEditPage extends EditPage{

    private EditorSectionDateTime es1;
    private EditorSectionDateTime es2;

    public TrainingEditPage(TrainingPage trainingPage, DefaultTableModel defaultTableModel, TrainingModel model, TrainingModel backupModel) {
        super();

        setBorder(new EmptyBorder(new Insets(0, 20, 0, 20)));
        GridBagConstraints gbc = new GridBagConstraints();
        EmptyBorder esMargin = new EmptyBorder(new Insets(0, 0, 15, 0));
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        es1 = new EditorSectionDateTime(
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
        es1.addModifyListener(arg0 -> {
            model.setStartDateTime((DateTime)es1.getValue());
            model.updateObservers();
        });

        es1.setBorder(esMargin);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        add(es1, gbc);

        es2 = new EditorSectionDateTime(
            "End Date Time",
            "Select the training's ending date and time",
            model.getStartDateTime(),
            null
        ) {
            @Override
            public void update(Observable observable) {
                System.out.println("zfezf");
                setMinimumDateTime(((TrainingModel)observable).getStartDateTime());
                setValue(((TrainingModel)observable).getEndDateTime());
                setValue(null);

            }
        };
        es2.addModifyListener(arg0 -> {
            model.setEndDateTime((DateTime)es2.getValue());
            model.updateObservers();
        });
        

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
                    stmt.setString(1, es1.getValue().toString());
                    stmt.setString(2, es2.getValue().toString());
                    stmt.setInt(3, model.getID());
                    stmt.execute();
                    trainingPage.loadResults();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        };

        model.addObserver(es1);
        model.addObserver(es2);
    }
    /**
     * Clears the edit pages's fields
     */
    public void clear() {
        es1.clear();
        es2.clear();
    }
}
