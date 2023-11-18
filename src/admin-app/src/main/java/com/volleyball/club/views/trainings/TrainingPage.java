package com.volleyball.club.views.trainings;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;


import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.views.Page;

public class TrainingPage extends Page{
    private static DefaultTableModel defaultTable = new DefaultTableModel(new String[]{"ID","Start","End"},0){
        @Override
        public boolean isCellEditable(int row, int column) {
            // Make all cells non-editable
            return false;
        }
    };
    
    private static JTable table;

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

        TrainingEditPage tep = new TrainingEditPage();
        
        setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        JButton submit = new JButton("submit");
        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        table = new JTable(defaultTable);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                tep.changeStart((String)defaultTable.getValueAt(table.getSelectedRow(), 1));
                tep.changeEnd((String)defaultTable.getValueAt(table.getSelectedRow(), 2));
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
        add(tep,gbc);
    }
    
    public void loadResults(){
        String query = "SELECT * FROM training";
        ResultSet resSet = DBConnectionManager.execQuery(query);
        defaultTable.setRowCount(0);
        String start="",end="", id="";
        try{
            while(resSet.next()){
                start = resSet.getString("startDateTimeTraining");
                end = resSet.getString("endDateTimeTraining");
                id = resSet.getString("idTraining");
                defaultTable.addRow(new String[]{id,start,end});
            }
        }catch(Exception e){
            System.out.println(e);
        } 
        table.setModel(defaultTable);
        revalidate();
        repaint();
    }
}
