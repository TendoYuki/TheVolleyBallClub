package com.volleyball.club.views.trainings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.elements.DateTimePicker;
import com.volleyball.club.views.Page;

public class TrainingPage extends Page{
    private static DefaultTableModel defaultTable = new DefaultTableModel(new String[]{"ID","Start","End"},0){
        @Override
        public boolean isCellEditable(int row, int column) {
            // Make all id cells non-editable
            return column != 0;
        }
    };
    
    private static JTable table;

    public TrainingPage(){
        super();
        
        JPanel tdisplay = new JPanel();
        tdisplay.add(new TrainingEditPage());
        JButton submit = new JButton("submit");
        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // System.out.println("DateTime :" + dtp.getDateTime());
            }
        });
        tdisplay.add(submit);
        table = new JTable(defaultTable);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setMinimumSize(new Dimension(500, 500));
        add(scroll,BorderLayout.CENTER);
        add(tdisplay,BorderLayout.SOUTH);
        add(new JLabel("Training Page"), BorderLayout.NORTH);
    }
    
    public void loadResults(){
        String query = "SELECT * FROM training";
        ResultSet resSet = DBConnectionManager.execQuery(query);
        defaultTable.setRowCount(0);
        String start="",end="", id="";
        JButton delete = new JButton("delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int res = JOptionPane.showConfirmDialog(null,"Etes-vous sur ?");

                if(res == JOptionPane.YES_OPTION){
                    System.out.println("YES OPTION SELECTED");
                    int selectedRow = table.getSelectedRow();
                    if(selectedRow != -1){
                        String id = (String)defaultTable.getValueAt(selectedRow, 0);
                        System.out.println(id);
                    }
                }
            }
        });
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
        // add(delete);
        table.setModel(defaultTable);
        revalidate();
        repaint();
    }
}
