package com.volleyball.club;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;

import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.models.NavbarModel;
import com.volleyball.club.views.Event;
import com.volleyball.club.views.Navbar;
import com.volleyball.club.views.Sponsor;

import java.lang.Thread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.sql.Connection;

public class Main extends JFrame{
    private static JFrame JFGui;
    public static void main(String[] args) {
        JFGui = new JFrame();
        Connection con = DBConnectionManager.getConnection();
        
        NavbarModel navModel = new NavbarModel();
        Navbar navbar = new Navbar(navModel);
        navModel.addObserver(navbar);
        navModel.addMenu(new JMenu("Login"));
        
        JFGui.add(navbar, BorderLayout.NORTH);
        JFGui.add(new Sponsor(), BorderLayout.CENTER);
        
        JFGui.setSize(new Dimension(1024, 512));
        JFGui.setLocationRelativeTo(null);
        JFGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String filePath = new File("").getAbsolutePath();
        System.out.println(filePath + "/../../public/logo.png");
        JFGui.setIconImage((new ImageIcon(filePath + "/../../public/logo.png")).getImage());
        JFGui.setResizable(false);
        JFGui.setVisible(true);
    }
}