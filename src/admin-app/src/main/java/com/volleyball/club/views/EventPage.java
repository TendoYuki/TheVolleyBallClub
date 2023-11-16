package com.volleyball.club.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.volleyball.club.database.DBConnectionManager;

public class EventPage extends Page{
    private static DefaultTableModel defaultTable = new DefaultTableModel(new String[]{"Start","End","Name","Description"}, 0){
        @Override
        public boolean isCellEditable(int row, int column) {
            // Make all id cells non-editable
            return column != 0;
        }
    };
    private static JTable table;

    public EventPage(){
        super();
        table = new JTable(defaultTable);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setMinimumSize(new Dimension(700, 500));
        add(scroll,BorderLayout.CENTER);
        add(new JLabel("Event Page"), BorderLayout.NORTH);
    }
    
    public void loadResults(){
        String query = "SELECT * FROM event";
        ResultSet resSet = DBConnectionManager.execQuery(query);
        defaultTable.setRowCount(0);
        String start="",end="",name="",desc="";
        try{
            while(resSet.next()){
                start = resSet.getString("startDateTime");
                end = resSet.getString("endDateTime");
                name = resSet.getString("nameEvent");
                desc = resSet.getString("descEvent");
                defaultTable.addRow(new String[]{start,end,name,desc});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        table.setModel(defaultTable);
        revalidate();
        repaint();
    }
}
