package com.volleyball.club.controllers;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.volleyball.club.views.GUI;
import com.volleyball.club.views.LoginPage;
import com.volleyball.club.views.Page;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavigationController extends MouseAdapter {
    private Page page;
    private GUI gui;

    public NavigationController(Page page, GUI gui) {
        this.page = page;
        this.gui = gui;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        gui.switchActivePage(page);
    }
}
