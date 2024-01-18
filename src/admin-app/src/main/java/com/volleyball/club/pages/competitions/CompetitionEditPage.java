package com.volleyball.club.pages.competitions;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import com.volleyball.club.controllers.EditorActionController;
import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.models.LocationModel;
import com.volleyball.club.datetime.DateTime;
import com.volleyball.club.elements.editor.EditorActions;
import com.volleyball.club.elements.editor.EditorSectionDateTime;
import com.volleyball.club.elements.editor.EditorSectionDropDown;
import com.volleyball.club.elements.editor.EditorSectionNumberField;
import com.volleyball.club.observation.Observable;
import com.volleyball.club.observation.Observer;
import com.volleyball.club.pages.EditPage;
import com.volleyball.club.pages.GUI;

/** Edition page of the competitions */
public class CompetitionEditPage extends EditPage{

    /** Editor section of the start time of the competition */
    private EditorSectionDateTime startTimeEditorSection;
    /** Editor section of the start end of the competition */
    private EditorSectionDateTime endTimeEditorSection;
    /** Editor section of the maxParticipants of the competition */
    private EditorSectionNumberField maxParticipantEditorSection;
    /** Editor section of the location of the competition */
    private EditorSectionDropDown locationEditorSection;

    /**
     * Creates a new competition edition page
     * @param competitionPage Linked competition page 
     * @param model Model to edit
     * @param backupModel Backup of the model to edit before edition
     */
    public CompetitionEditPage(CompetitionPage competitionPage, CompetitionModel model, CompetitionModel backupModel, GUI gui) {
        super();

        setBorder(new EmptyBorder(new Insets(0, 20, 0, 20)));
        GridBagConstraints gbc = new GridBagConstraints();
        EmptyBorder esMargin = new EmptyBorder(new Insets(0, 0, 15, 0));
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        startTimeEditorSection = new EditorSectionDateTime(
            "Start Date Time",
            "Select the competition's starting date and time",
            null,
            model.getEndDateTime()
        ) {
            @Override
            public void update(Observable observable) {
                setMaximumDateTime(((CompetitionModel)observable).getEndDateTime());
                setValue(((CompetitionModel)observable).getStartDateTime());
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
            "Select the competition's ending date and time",
            model.getStartDateTime(),
            null
        ) {
            @Override
            public void update(Observable observable) {
                setMinimumDateTime(((CompetitionModel)observable).getStartDateTime());
                setValue(((CompetitionModel)observable).getEndDateTime());
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

        maxParticipantEditorSection = new EditorSectionNumberField(
            "Max participants count",
            "Select the competition's max participants count",
            6,
            32,
            1, 
            6
        ) {
            @Override
            public void update(Observable observable) {
                setValue(((CompetitionModel)observable).getMaxParticipant());
            }
        };
        maxParticipantEditorSection.addModifyListener(arg0 -> {
            model.setMaxParticipant((int)maxParticipantEditorSection.getValue());
            model.updateObservers();
        });
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        add(maxParticipantEditorSection, gbc);

        ArrayList<String> locationsList = new ArrayList<String>();

        Connection con = DBConnectionManager.getConnection();

        try{
            PreparedStatement stmt = con.prepareStatement("SELECT nameLocation FROM location;");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                locationsList.add(rs.getString("nameLocation"));
        }catch(Exception e){
            System.out.println(e);
        }

        locationEditorSection = new EditorSectionDropDown(
            "Location",
            "Select the competition's location",
            locationsList.toArray(new String[0])
        ) {
            @Override
            public void update(Observable observable) {
                setValue(((CompetitionModel)observable).getLocation());
            }
        };

        locationEditorSection.addModifyListener(arg0 -> {
            try{
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM location WHERE nameLocation=?;");
                stmt.setString(1, (String)locationEditorSection.getValue());
                ResultSet rs = stmt.executeQuery();
                if(rs.next())
                    model.setLocation(rs.getString("nameLocation"));
            }catch(Exception e){
                System.out.println(e);
            }
            model.updateObservers();
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0;
        add(locationEditorSection, gbc);

        EditorActions ea = new EditorActions();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 0;
        gbc.weightx = 1;
        add(ea, gbc);

        new EditorActionController(ea) {
            @Override
            public void onCancel() {
                // Loads the previous state into the current active model
                model.setID(backupModel.getID());
                model.setStartDateTime(backupModel.getStartDateTime());
                model.setEndDateTime(backupModel.getEndDateTime());
                model.setMaxParticipant(backupModel.getMaxParticipant());
                model.setLocation(backupModel.getLocation());
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
                        PreparedStatement stmt = con.prepareStatement("DELETE FROM competition WHERE idCompetition=?;");
                        stmt.setInt(1, model.getID());
                        stmt.execute();
                        model.resetDefaultValues();
                        competitionPage.loadResults();
                    }catch(Exception e){
                        System.out.println(e);
                    }

                }
            }
            @Override
            public void onSave() {
                Connection con = DBConnectionManager.getConnection();
                try{
                    int locationId = LocationModel.getLocationIdFromName((String)locationEditorSection.getValue());
                    PreparedStatement stmt = con.prepareStatement(
                        "UPDATE competition SET "+
                        "startDateTimeCompetition=?,"+
                        "endDateTimeCompetition=?,"+
                        "maxParticipantCompetition=?,"+
                        "Location_idLocation=? "+
                        "WHERE idCompetition=?;"
                    );
                    stmt.setString(1, startTimeEditorSection.getValue().toString());
                    stmt.setString(2, endTimeEditorSection.getValue().toString());
                    stmt.setInt(3, (int)maxParticipantEditorSection.getValue());
                    if (locationId == -1) {
                        stmt.setNull(4, java.sql.Types.INTEGER);
                    } else {
                        stmt.setInt(4, locationId);
                    }
                    stmt.setInt(5, model.getID());
                    stmt.execute();
                    competitionPage.loadResults();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        };
        CompetitionResultPage competitionResultPage = new CompetitionResultPage(model.getResultModel(),gui, competitionPage);
        CompetitionResultPageController competitionResultPageController = new CompetitionResultPageController(competitionResultPage, gui); 
        
        CompetitonResultCreatePage competitionCreateResultPage = new CompetitonResultCreatePage(model);
        CompetitionCreateResultPageController competitionCreateResultPageController = new CompetitionCreateResultPageController(competitionCreateResultPage, gui); 

        Insets btnBorders = new Insets(5, 5, 0, 0);
        JButton addResultButton = new JButton("Add Competition Results");
        addResultButton.addActionListener(competitionCreateResultPageController);
        JButton viewResultButton = new JButton("View Competition Results");
        viewResultButton.addActionListener(competitionResultPageController);

        gbc.insets = btnBorders;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weighty = 1;
        gbc.weightx = 1;
        if(!model.hasResult()) {
            remove(viewResultButton);
            add(addResultButton, gbc);
        }
        else {
            remove(addResultButton);
            add(viewResultButton, gbc);
        }
        revalidate();
        repaint();

        model.addObserver(new Observer() {
            @Override
            public void update(Observable observable) {
                gbc.insets = btnBorders;
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.weighty = 1;
                gbc.weightx = 1;
                CompetitionModel model = (CompetitionModel)observable;
                if(!model.hasResult()) {
                    remove(viewResultButton);
                    add(addResultButton, gbc);
                }
                else {
                    remove(addResultButton);
                    add(viewResultButton, gbc);
                }
                revalidate();
                repaint();
            }
        });

        model.addObserver(startTimeEditorSection);
        model.addObserver(endTimeEditorSection);
        model.addObserver(locationEditorSection);
        model.addObserver(maxParticipantEditorSection);
    }

    @Override
    public void clear() {
        startTimeEditorSection.clear();
        endTimeEditorSection.clear();
        locationEditorSection.clear();
        maxParticipantEditorSection.clear();
    }
}
