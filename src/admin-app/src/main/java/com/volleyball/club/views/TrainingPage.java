package com.volleyball.club.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.volleyball.club.database.DBConnectionManager;

public class TrainingPage extends Page{
    private static DefaultTableModel defaultTable = new DefaultTableModel(new String[]{/* TODO : add different rows */}, 0){
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
        add(new JLabel("Event Page"), CENTER_ALIGNMENT);
    }
    
    public void loadResults(){
        String query = "SELECT * FROM partner";
        ResultSet resSet = DBConnectionManager.execQuery(query);
        defaultTable.setRowCount(0);
        String name="",logo="";
        try{
            while(resSet.next()){
                name = resSet.getString("namePartner");
                logo = resSet.getString("logoPartner");
                defaultTable.addRow(new String[]{name,logo});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        table.setModel(defaultTable);
        revalidate();
        repaint();
    }
}
