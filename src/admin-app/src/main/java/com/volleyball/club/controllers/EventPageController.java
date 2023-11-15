package com.volleyball.club.controllers;

import java.awt.event.ActionEvent;

import com.volleyball.club.views.EventPage;
import com.volleyball.club.views.GUI;

public class EventPageController extends NavigationController {

    public EventPageController(EventPage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        ((EventPage)getPage()).loadResults();
    }
}
