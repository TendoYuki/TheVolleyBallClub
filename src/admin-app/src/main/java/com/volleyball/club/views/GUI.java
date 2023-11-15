package com.volleyball.club.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;


import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.models.NavbarModel;

public class GUI extends JFrame{
    private Page activePage = null;

    public GUI() {
        NavbarModel navModel = new NavbarModel();
        Navbar navbar = new Navbar(navModel);
        navModel.addObserver(navbar);
        add(navbar, BorderLayout.NORTH);

        JMenu loginMenu = new JMenu("Login");
        LoginPage loginPage = new LoginPage();


        loginMenu.addMouseListener(new NavigationController(loginPage, this));
        navModel.addMenu(loginMenu);

        JMenu eventMenu = new JMenu("Event");
        Event eventPage = new Event();

        eventMenu.addMouseListener(new NavigationController(eventPage, this));
        navModel.addMenu(eventMenu);

        setSize(new Dimension(1024, 512));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String filePath = new File("").getAbsolutePath();
        setIconImage((new ImageIcon(filePath + "/../../public/logo.png")).getImage());
        setResizable(false);
        setVisible(true);
    }

    public void switchActivePage(Page newActivePage) {
        if(activePage != null) remove(activePage);
        add(newActivePage, BorderLayout.CENTER);
        activePage = newActivePage;
        revalidate();
        repaint();
    }
}
