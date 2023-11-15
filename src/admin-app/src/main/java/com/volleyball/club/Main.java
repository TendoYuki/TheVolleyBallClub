package com.volleyball.club;

import javax.swing.JFrame;
import javax.swing.JMenu;

import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.models.NavbarModel;
import com.volleyball.club.views.Event;
import com.volleyball.club.views.Navbar;
import java.lang.Thread;

import java.awt.BorderLayout;
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

        NavbarModel navModel = new NavbarModel();
        Navbar navbar = new Navbar(navModel);
        navModel.addObserver(navbar);
        navModel.addMenu(new JMenu("Login"));

        JFGui.add(navbar, BorderLayout.NORTH);
        JFGui.add(new Event(), BorderLayout.CENTER);
    }
}