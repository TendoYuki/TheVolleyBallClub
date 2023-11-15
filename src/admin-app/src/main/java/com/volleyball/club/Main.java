package com.volleyball.club;

import javax.swing.JFrame;

import com.volleyball.club.database.DBConnectionManager;

import java.awt.Dimension;
import java.sql.Connection;

public class Main extends JFrame{
    private static JFrame JFGui;
    public static void main(String[] args) {
        JFGui = new JFrame();
        Connection con = DBConnectionManager.getConnection();
        JFGui.setSize(new Dimension(1024, 512));
        JFGui.setLocationRelativeTo(null);
        JFGui.setResizable(false);
        JFGui.setVisible(true);
    }
}