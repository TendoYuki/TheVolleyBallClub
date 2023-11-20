package com.volleyball.club.pages.competitions;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.elements.editor.EditorSectionNumberField;
import com.volleyball.club.observation.Observable;
import com.volleyball.club.pages.CreatePage;

/** Page that allows competition result creation */
public class CompetitonResultCreatePage extends CreatePage{
    
    /** Editor section of the victories count */
    private EditorSectionNumberField victoriesCountSection;

    /** Editor section of the defeats count */
    private EditorSectionNumberField defeatsCountSection;

    /** Editor section of the rank */
    private EditorSectionNumberField rankSection;

    /** Editor section of the club counts */
    private EditorSectionNumberField clubCountsSection;

    /** Model of the competition getting created */
    private CompetitionResultModel model = new CompetitionResultModel();

    /** Creates a competition creation page */
    public CompetitonResultCreatePage(CompetitionModel competitionModel) {
        super();
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(new Insets(0, 20, 0, 20)));
        GridBagConstraints gbc = new GridBagConstraints();
        EmptyBorder esMargin = new EmptyBorder(new Insets(0, 0, 15, 0));
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        JPanel sectionsWrapper = new JPanel(new GridBagLayout());

        /** ---------- VICTORIES COUNT SECTION ---------------- */

        victoriesCountSection = new EditorSectionNumberField(
            "Victories Count",
            "Input the number of wins of the club",
            0,
            100,
            1,
            0
        ) {
            @Override
            public void update(Observable observable) {
                setValue(((CompetitionResultModel)observable).getVictoriesCount());
            }
        };
        victoriesCountSection.addModifyListener(arg0 -> {
            model.setVictoriesCount((Integer)victoriesCountSection.getValue());
            model.updateObservers();
        });

        /** ---------- DEFEATS COUNT SECTION ---------------- */

        defeatsCountSection = new EditorSectionNumberField(
            "Defeats Count",
            "Input the number of looses of the club",
            0,
            100,
            1,
            0
        ) {
            @Override
            public void update(Observable observable) {
                setValue(((CompetitionResultModel)observable).getDefeatCount());
            }
        };
        defeatsCountSection.addModifyListener(arg0 -> {
            model.setDefeatCount((Integer)defeatsCountSection.getValue());
            model.updateObservers();
        });

        /** ---------- CLUBS COUNT SECTION ---------------- */

        clubCountsSection = new EditorSectionNumberField(
            "Participating Clubs Count",
            "Input the number of clubs that participated to the competition",
            0,
            100,
            1,
            0
        ) {
            @Override
            public void update(Observable observable) {
                setValue(((CompetitionResultModel)observable).getClubCount());
            }
        };
        clubCountsSection.addModifyListener(arg0 -> {
            model.setClubCount((Integer)clubCountsSection.getValue());
            model.updateObservers();
        });

        /** ---------- RANK SECTION ---------------- */

        rankSection = new EditorSectionNumberField(
            "Club Rank",
            "Input the rank of the club",
            1,
            100,
            1,
            1
        ) {
            @Override
            public void update(Observable observable) {
                setValue(((CompetitionResultModel)observable).getRanking());
            }
        };
        rankSection.addModifyListener(arg0 -> {
            model.setRanking((Integer)rankSection.getValue());
            model.updateObservers();
        });

        /** ---------------------------------------- */

        gbc.weightx = 1;
        victoriesCountSection.setBorder(esMargin);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        sectionsWrapper.add(victoriesCountSection, gbc);

        defeatsCountSection.setBorder(esMargin);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0;
        sectionsWrapper.add(defeatsCountSection, gbc);

        clubCountsSection.setBorder(esMargin);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        sectionsWrapper.add(clubCountsSection, gbc);

        rankSection.setBorder(esMargin);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0;
        sectionsWrapper.add(rankSection, gbc);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(event -> {
            Connection con = DBConnectionManager.getConnection();
            try{
                String generatedColumns[] = { "idResult" };
                PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO result (victoriesCount, defeatCount, ranking, totalClubsCount) VALUES (?,?,?,?);",
                    generatedColumns
                );
                stmt.setString(1, victoriesCountSection.getValue().toString());
                stmt.setString(2, defeatsCountSection.getValue().toString());
                stmt.setString(3, rankSection.getValue().toString());
                stmt.setString(4, clubCountsSection.getValue().toString());
                stmt.execute();
                ResultSet rs = stmt.getGeneratedKeys();
                int id = -1;
                if (rs.next()) {
                    id = rs.getInt(1);
                    System.out.println(id);
                }

                stmt = con.prepareStatement(
                    "UPDATE competition SET Result_idResult=? WHERE idCompetition=?;"
                );
                if(id != -1) {
                    stmt.setInt(1, id);
                    stmt.setInt(2, competitionModel.getID());
                    stmt.execute();
                }
                JOptionPane.showMessageDialog(null, "Entry successfully created","Success", JOptionPane.INFORMATION_MESSAGE);
                clear();
            }catch(Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "An error occured","Error", JOptionPane.ERROR_MESSAGE);
            }
            clear();
        });
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        add(sectionsWrapper, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.weightx = 1;
        add(submitButton, gbc);

        model.addObserver(victoriesCountSection);
        model.addObserver(defeatsCountSection);
        model.addObserver(clubCountsSection);
        model.addObserver(rankSection);
    }
    
    /**
     * Clears the fields
     */
    public void clear() { 

    }
}
