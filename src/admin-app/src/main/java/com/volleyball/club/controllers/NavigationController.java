package com.volleyball.club.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.volleyball.club.pages.GUI;
import com.volleyball.club.pages.Page;

public abstract class NavigationController implements ActionListener {
    private Page page;
    private GUI gui;

    public NavigationController(Page page, GUI gui) {
        this.page = page;
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.switchActivePage(page);
        onPageSelected();
    }

    public Page getPage() {
        return page;
    }

    public abstract void onPageSelected();
}
