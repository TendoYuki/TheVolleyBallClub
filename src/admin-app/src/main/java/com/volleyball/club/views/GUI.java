package com.volleyball.club.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;

import com.volleyball.club.controllers.CompetitionPageController;
import com.volleyball.club.controllers.EventPageController;
import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.controllers.SponsorPageController;
import com.volleyball.club.controllers.TrainingPageController;
import com.volleyball.club.models.NavbarModel;

public class GUI extends JFrame{
    private Page activePage = null;

    public GUI() {
        /** ----------- NAVBAR INITIALIZATION ----------- */

        NavbarModel navModel = new NavbarModel();
        Navbar navbar = new Navbar(navModel);
        navModel.addObserver(navbar);
        add(navbar, BorderLayout.NORTH);

        /** ----------- LOGIN PAGE ----------- */

        JButton loginMenuBarBTN = new JButton("Login");
        loginMenuBarBTN.setFocusPainted(false);
        LoginPage loginPage = new LoginPage();

        loginMenuBarBTN.addActionListener(new NavigationController(loginPage, this));
        navModel.addMenu(loginMenuBarBTN);

        /** ----------- EVENT PAGE ----------- */

        JButton eventMenu = new JButton("Event");
        EventPage eventPage = new EventPage();

        eventMenu.addActionListener(new EventPageController(eventPage, this));
        navModel.addMenu(eventMenu);

        /** ----------- COMPETITION PAGE ----------- */

        JButton competitionMenu = new JButton("Competition");
        CompetitionPage competitionPage = new CompetitionPage();

        competitionMenu.addActionListener(new CompetitionPageController(competitionPage, this));
        navModel.addMenu(competitionMenu);

        /** ----------- SPONSOR PAGE ----------- */

        JButton sponsorMenu = new JButton("Sponsor");
        SponsorPage sponsorPage = new SponsorPage();

        sponsorMenu.addActionListener(new SponsorPageController(sponsorPage, this));
        navModel.addMenu(sponsorMenu);

        /** ----------- TRAINING PAGE ----------- */

        JButton trainingMenu = new JButton("Training");
        TrainingPage trainingPage = new TrainingPage();

        trainingMenu.addActionListener(new TrainingPageController(trainingPage, this));
        navModel.addMenu(trainingMenu);

        /** ----------- CHANGING APP ICON ----------- */

        String filePath = new File("").getAbsolutePath();
        setIconImage((new ImageIcon(filePath + "/../../public/logo.png")).getImage());

        /** ----------- SETTING UP GUI PREFERENCES ----------- */

        setSize(new Dimension(1024, 512));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Changes the active page of the gui to a new one
     * @param newActivePage New active page to replace the old one
     */
    public void switchActivePage(Page newActivePage) {
        if(activePage != null) remove(activePage);
        add(newActivePage, BorderLayout.CENTER);
        activePage = newActivePage;
        revalidate();
        repaint();
    }
}
