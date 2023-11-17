package com.volleyball.club.views.competitions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.views.Page;

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
        JButton submit = new JButton("submit");
        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Time :" + tp.getTime());
                System.out.println("Date :" + cp.getDate());
            }
        });
        tdisplay.add(submit);
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