package com.volleyball.club;

import javax.swing.JFrame;

import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.views.GUI;

public class Main extends JFrame{
    public static void main(String[] args) {
        new GUI();
        DBConnectionManager.getConnection();
    }
}