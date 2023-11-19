package com.volleyball.club;

import javax.swing.JFrame;

import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.pages.GUI;

/** Main class that instantiates the gui of the client */
public class Main extends JFrame{
    /**
     * Main method
     * @param args Launch arguments
     */
    public static void main(String[] args) {
        new GUI();
        DBConnectionManager.getConnection();
    }
}