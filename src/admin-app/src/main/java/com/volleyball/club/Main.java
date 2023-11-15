package com.volleyball.club;

import javax.swing.JFrame;

import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.views.*;

import java.awt.Dimension;
import java.sql.Connection;

public class Main extends JFrame{
    private static JFrame JFGui;
    public static void main(String[] args) {
        JFGui = new JFrame();
        Connection con = DBConnectionManager.getConnection();
        JFGui.setSize(new Dimension(1024, 512));
        JFGui.setLocationRelativeTo(null);
        JFGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFGui.setResizable(false);
        JFGui.setVisible(true);
        JFGui.add(new Event());
    }
}