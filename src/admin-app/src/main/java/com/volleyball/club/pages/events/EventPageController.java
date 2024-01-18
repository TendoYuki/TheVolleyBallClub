package com.volleyball.club.pages.events;

import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.pages.GUI;

public class EventPageController extends NavigationController {
    public EventPageController(EventPage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void onPageSelected() {
        ((EventPage)getPage()).clearEditor();
        ((EventPage)getPage()).loadResults();
    }
}
