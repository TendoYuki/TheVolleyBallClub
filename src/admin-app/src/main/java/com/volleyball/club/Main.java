package com.volleyball.club;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.plaf.DimensionUIResource; 

public class Main extends JFrame{
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            DriverManager.getConnection("jdbc:mysql://localhost:3306/deocovoit","root","");  
        } catch (Exception e) {
            System.out.println(e);
        }
        JFrame f = new JFrame();

        f.setVisible(true);
        f.setSize(new DimensionUIResource(1024, 512));
        f.setResizable(false);
        f.setLocationRelativeTo(null);
    }

}