package com.volleyball.club.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.volleyball.club.database.DBConnectionManager;

public class CompetitionPage extends Page{
    private static DefaultTableModel defaultTable = new DefaultTableModel(new String[]{"Start","End"}, 0){
        @Override
        public boolean isCellEditable(int row, int column) {
            // Make all id cells non-editable
            return column != 0;
        }
    };
    private static JTable table;

    public CompetitionPage(){
        super();
        TimePicker tp = new TimePicker();
        DatePicker cp = new DatePicker();
        JPanel tdisplay = new JPanel();
        tdisplay.add(tp);
        tdisplay.add(cp);
        table = new JTable(defaultTable);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setMinimumSize(new Dimension(500, 500));
        add(scroll,BorderLayout.CENTER);
        add(tdisplay,BorderLayout.SOUTH);
        add(new JLabel("Competition Page"), BorderLayout.NORTH);
        
    }

    public void loadResults(){
        String query = "SELECT * FROM competition";
        ResultSet resSet = DBConnectionManager.execQuery(query);
        defaultTable.setRowCount(0);
        String start="",end="";
        try{
            while(resSet.next()){
                start = resSet.getString("startDateTimeCompetition");
                end = resSet.getString("endDateTimeCompetition");
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
