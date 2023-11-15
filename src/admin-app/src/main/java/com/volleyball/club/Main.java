package com.volleyball.club;

import javax.swing.JFrame;

import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.views.GUI;
import com.volleyball.club.views.Sponsor;

import java.sql.Connection;

public class Main extends JFrame{
    private static GUI JFGui;
    public static void main(String[] args) {
        JFGui = new GUI();
        Connection con = DBConnectionManager.getConnection();
        JFGui.switchActivePage(new Sponsor());
    }
}