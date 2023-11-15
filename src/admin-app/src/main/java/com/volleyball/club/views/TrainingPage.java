package com.volleyball.club.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.volleyball.club.database.DBConnectionManager;

public class TrainingPage extends Page{
    private static DefaultTableModel defaultTable = new DefaultTableModel(new String[]{"Start","End"}, 0){
        public boolean Edit(int row, int column){
            return false;
        }
    };
    private static JTable table;

    public TrainingPage(){
        super();
        table = new JTable(defaultTable);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setMinimumSize(new Dimension(500, 500));
        add(scroll,BorderLayout.CENTER);
        add(new JLabel("Training Page"), BorderLayout.NORTH);
    }
    
    public void loadResults(){
        String query = "SELECT * FROM training";
        ResultSet resSet = DBConnectionManager.execQuery(query);
        defaultTable.setRowCount(0);
        String start="",end="";
        try{
            while(resSet.next()){
                start = resSet.getString("startDateTimeTraining");
                end = resSet.getString("endDateTimeTraining");
                defaultTable.addRow(new String[]{start,end});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        table.setModel(defaultTable);
        revalidate();
        repaint();
    }
}
