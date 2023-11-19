package com.volleyball.club.pages.trainings;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.lang.Integer;

import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.datetime.DateTime;
import com.volleyball.club.datetime.exceptions.InvalidDateTimeFormatException;
import com.volleyball.club.pages.Page;

/**
 * Training page displaying all trainings 
 */
public class TrainingPage extends Page{
    /** Model of the table containing all trainings */
    private static DefaultTableModel defaultTable = new DefaultTableModel(new String[]{"ID","Start","End"},0){
        @Override
        public boolean isCellEditable(int row, int column) {
            // Make all cells non-editable
            return false;
        }
    };
    
    /** Table to display the model */
    private static JTable table;
    /** Model of the currently selected row */
    private TrainingModel trainingModel = new TrainingModel();
    /** Backup model of the currently selected row, used for cancel logic */
    private TrainingModel backupModel = new TrainingModel();
    /** Edition page of the trainings */
    private TrainingEditPage trainingEditPage;

    /** Creates a new training page */
    public TrainingPage(){
        super();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridwidth=2;
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Training Page", SwingConstants.CENTER), gbc);
        
        setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        table = new JTable(defaultTable);

        trainingEditPage = new TrainingEditPage(this, trainingModel, backupModel);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int id = Integer.valueOf((String)defaultTable.getValueAt(table.getSelectedRow(), 0));
                String startDateTime = (String)defaultTable.getValueAt(table.getSelectedRow(), 1);
                String endDateTime = (String)defaultTable.getValueAt(table.getSelectedRow(), 2);

                try {
                    // Changes the model with the new current one and notifies the view to update
                    trainingModel.setEndDateTime(new DateTime(endDateTime));
                    trainingModel.setStartDateTime(new DateTime(startDateTime));
                    trainingModel.setID(id);
                    trainingModel.updateObservers();

                    // Changes the backup model with the new current one
                    backupModel.setEndDateTime(new DateTime(endDateTime));
                    backupModel.setStartDateTime(new DateTime(startDateTime));
                    backupModel.setID(id);
                } catch (InvalidDateTimeFormatException e) {
                    System.out.println(e);
                }
            }
        });

        JScrollPane scroll = new JScrollPane(table);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth=1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(scroll,gbc);

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.gridheight=GridBagConstraints.REMAINDER;
        gbc.gridwidth=1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(trainingEditPage,gbc);
    }
    
    /** Loads the database inside of the table model */
    public void loadResults(){
        Connection con = DBConnectionManager.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM training ORDER BY startDateTimeTraining;"
            );
            ResultSet resSet = stmt.executeQuery();
            defaultTable.setRowCount(0);
            String start="",end="", id="";
            while(resSet.next()){   
                start = resSet.getString("startDateTimeTraining");
                end = resSet.getString("endDateTimeTraining");
                id = resSet.getString("idTraining"); 
                defaultTable.addRow(new String[]{id,start,end});
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        table.setModel(defaultTable);
    }

    /**
     * Clears the editor's fields
     */
    public void clearEditor() {
        trainingEditPage.clear();
    }
}
