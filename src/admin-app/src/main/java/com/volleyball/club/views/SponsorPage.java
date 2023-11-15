package com.volleyball.club.views;

import java.awt.Dimension;
import java.sql.ResultSet;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SponsorPage extends Page{
    private static DefaultTableModel defaultTable = new DefaultTableModel(new String[]{/* TODO : add different rows */}, 0){
        public boolean Edit(int row, int column){
            return false;
        }
    };
    private static ResultSet resSet;

    public SponsorPage(){
        super();

        // TODO : Fill resSet

        defaultTable.setRowCount(0);
        try{
            while(resSet.next()){
                // TODO : add different rows
                defaultTable.addRow(new String[]{/* TODO : add different rows*/});
            }
        }catch(Exception e){

        }
        
        JTable Table = new JTable(defaultTable);
        JScrollPane scroll = new JScrollPane(Table);
        scroll.setMinimumSize(new Dimension(500, 500));
    }
    
}
