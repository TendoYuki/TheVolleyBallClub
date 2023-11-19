package com.volleyball.club.controllers;

import com.volleyball.club.views.GUI;
import com.volleyball.club.views.events.EventPage;

public class EventPageController extends NavigationController {

    public EventPageController(EventPage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void onPageSelected() {
        ((EventPage)getPage()).loadResults();
    }
}
