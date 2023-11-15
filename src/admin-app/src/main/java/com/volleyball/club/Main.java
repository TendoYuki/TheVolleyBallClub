package com.volleyball.club;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import java.awt.Dimension;

public class Main extends JFrame{
    public static void main(String[] args) {
        System.out.println("Hello");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            DriverManager.getConnection("jdbc:mysql://localhost:3306/deocovoit","root","");  
        } catch (Exception e) {
            System.out.println(e);
        }
        JFrame f = new JFrame();

        f.setSize(new Dimension(1024, 512));
        JPanel p = new JPanel();
        p.setMinimumSize(new Dimension(1024, 512));
        f.add(p, BorderLayout.CENTER);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);
    }

}