package com.volleyball.club.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.volleyball.club.pages.GUI;
import com.volleyball.club.pages.Page;

/**
 * Controller class that manages the navigation system and the main display
 */
public abstract class NavigationController implements ActionListener {
    /** Page to display, linked to the controller  */
    private Page page;
    /** Reference to the main GUI */
    private GUI gui;

    /**
     * Creates a navigation controller
     * @param page Page that will be displayed when activated
     * @param gui Gui to manage
     */
    public NavigationController(Page page, GUI gui) {
        this.page = page;
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.switchActivePage(page);
        onPageSelected();
    }

    /**
     * Return the page linked to the controller
     * @return Page linked to the controller
     */
    public Page getPage() {
        return page;
    }

    /**
     * Handler called when the page is selected
     */
    public abstract void onPageSelected();
}
