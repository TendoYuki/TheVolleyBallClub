package com.volleyball.club.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.volleyball.club.database.DBConnectionManager;

public class Event extends Page{
    private static DefaultTableModel defaultTable = new DefaultTableModel(new String[]{"Start","End","Name","Description"}, 0){
        public boolean Edit(int row, int column){
            return false;
        }
    };
    private static ResultSet resSet;

    public Event(){
        super();
        add(new JLabel("Event Page"), CENTER_ALIGNMENT);
    }
    
    public void loadResults(){
        String query = "SELECT * FROM event";
        ResultSet stmt = DBConnectionManager.execQuery(query);
        defaultTable.setRowCount(0);
        String start="",end="",name="",desc="";
        try{
            while(resSet.next()){
                start = resSet.getString("startDate Time");
                System.out.println(resSet.getString("idEvent"));
                end = resSet.getString("endDate Time");
                name = resSet.getString("nameEvent");
                desc = resSet.getString("descEvent");
                defaultTable.addRow(new String[]{start,end,name,desc});
            }
        }catch(Exception e){

        }
        JTable Table = new JTable(defaultTable);
        JScrollPane scroll = new JScrollPane(Table);
        scroll.setMinimumSize(new Dimension(500, 500));
        add(scroll,BorderLayout.CENTER);
    }
}
