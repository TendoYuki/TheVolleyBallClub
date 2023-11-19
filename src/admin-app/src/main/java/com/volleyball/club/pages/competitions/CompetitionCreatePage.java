package com.volleyball.club.pages.competitions;

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
import com.volleyball.club.observation.Observable;
import com.volleyball.club.pages.CreatePage;

/** Page used to create a competition entry */
public class CompetitionCreatePage extends CreatePage{ 

    /** Editor section of the start time of the competition */
    private EditorSectionDateTime startTimeEditorSection;
    /** Editor section of the start end of the competition */
    private EditorSectionDateTime endTimeEditorSection;
    /** Model of the competition getting created */
    private CompetitionModel model = new CompetitionModel();

    /** Creates a competition creation page */
    public CompetitionCreatePage() {
        super();

        setBorder(new EmptyBorder(new Insets(0, 20, 0, 20)));
        GridBagConstraints gbc = new GridBagConstraints();
        EmptyBorder esMargin = new EmptyBorder(new Insets(0, 0, 15, 0));
        gbc.anchor = GridBagConstraints.CENTER;

        startTimeEditorSection = new EditorSectionDateTime(
            "Start Date Time",
            "Select the competition's starting date and time",
            DateTime.now(),
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

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(event -> {
            Connection con = DBConnectionManager.getConnection();
            try{
                PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO competition (startDateTimeCompetition, endDateTimeCompetition) VALUES (?,?);"
                );
                stmt.setString(1, startTimeEditorSection.getValue().toString());
                stmt.setString(2, endTimeEditorSection.getValue().toString());
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
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.weightx = 1;
        add(submitButton, gbc);

        model.addObserver(startTimeEditorSection);
        model.addObserver(endTimeEditorSection);
    }
    @Override
    public void clear() {
        model.resetDefaultValues();
        startTimeEditorSection.clear();
        endTimeEditorSection.clear();
    }
}
