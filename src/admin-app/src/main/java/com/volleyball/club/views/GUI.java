package com.volleyball.club.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.volleyball.club.controllers.CompetitionPageController;
import com.volleyball.club.controllers.EventPageController;
import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.controllers.SponsorPageController;
import com.volleyball.club.controllers.TrainingPageController;
import com.volleyball.club.login.LoginManager;
import com.volleyball.club.models.NavbarModel;
import com.volleyball.club.mvc.Observable;
import com.volleyball.club.mvc.Observer;
import com.volleyball.club.views.competitions.CompetitionPage;
import com.volleyball.club.views.events.EventPage;
import com.volleyball.club.views.sponsors.SponsorPage;
import com.volleyball.club.views.trainings.TrainingPage;

public class GUI extends JFrame{
    private Page activePage = null;

    public GUI() {
        super("Volleyball Club Admin Dashboard");
        /** ----------- NAVBAR INITIALIZATION ----------- */

        NavbarModel navModel = new NavbarModel();
        Navbar navbar = new Navbar(navModel);
        navModel.addObserver(navbar);
        add(navbar, BorderLayout.NORTH);

        /** ----------- HOME PAGE ----------- */

        HomePage homePage = new HomePage();

        /** ----------- LOGIN PAGE ----------- */

        JButton loginMenuBarBTN = new JButton("Login");
        loginMenuBarBTN.setFocusPainted(false);
        LoginPage loginPage = new LoginPage(this);

        JButton logoutMenuBarBTN = new JButton("Log out");
        logoutMenuBarBTN.setFocusPainted(false);

        loginMenuBarBTN.addActionListener(new NavigationController(loginPage, this));
        logoutMenuBarBTN.addActionListener(new NavigationController(loginPage, this));
        logoutMenuBarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginManager.getInstance().deauthentify();
            }
        });
        navModel.addMenu(loginMenuBarBTN);


        /** ----------- EVENT PAGE ----------- */

        JMenu eventMenu = new JMenu("Events");
        JMenuItem eventMenuViewBTN = new JMenuItem("View");
        EventPage eventPage = new EventPage();

        eventMenu.add(eventMenuViewBTN);
        eventMenuViewBTN.addActionListener(new EventPageController(eventPage, this));

        /** ----------- COMPETITION PAGE ----------- */

        JMenu competitionMenu = new JMenu("Competitions");
        JMenuItem competitionMenuViewBTN = new JMenuItem("View");
        CompetitionPage competitionPage = new CompetitionPage();

        competitionMenu.add(competitionMenuViewBTN);
        competitionMenuViewBTN.addActionListener(new CompetitionPageController(competitionPage, this));

        /** ----------- SPONSOR PAGE ----------- */

        JMenu sponsorMenu = new JMenu("Sponsors");
        JMenuItem sponsorMenuViewBTN = new JMenuItem("View");
        SponsorPage sponsorPage = new SponsorPage();

        sponsorMenu.add(sponsorMenuViewBTN);
        sponsorMenuViewBTN.addActionListener(new SponsorPageController(sponsorPage, this));

        /** ----------- TRAINING PAGE ----------- */

        JMenu trainingMenu = new JMenu("Trainings");
        JMenuItem trainingMenuViewBTN = new JMenuItem("View");
        TrainingPage trainingPage = new TrainingPage();
        trainingMenu.add(trainingMenuViewBTN);
        trainingMenuViewBTN.addActionListener(new TrainingPageController(trainingPage, this));

        /** ----------- CHANGING APP ICON ----------- */

        String filePath = new File("").getAbsolutePath();
        setIconImage((new ImageIcon(filePath + "/../../public/logo.png")).getImage());


        /** ----------- LOGGING LISTENING ------------ */

         LoginManager.getInstance().addObserver(new Observer() {
            @Override
            public void update(Observable observable) {
                if(LoginManager.getInstance().isConnected()){
                    navModel.replaceMenu(loginMenuBarBTN, logoutMenuBarBTN);
                    switchActivePage(homePage);
                    navModel.addMenu(eventMenu);
                    navModel.addMenu(competitionMenu);
                    navModel.addMenu(sponsorMenu);
                    navModel.addMenu(trainingMenu);

                }
                else{
                    navModel.replaceMenu(logoutMenuBarBTN, loginMenuBarBTN);
                    navModel.removeMenu(eventMenu);
                    navModel.removeMenu(competitionMenu);
                    navModel.removeMenu(sponsorMenu);
                    navModel.removeMenu(trainingMenu);
                }
            }
        });

        /** ----------- SETTING UP GUI PREFERENCES ----------- */

        switchActivePage(homePage);
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
