package com.volleyball.club.pages.events;

import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.pages.GUI;

public class EventCreatePageController extends NavigationController {
    public EventCreatePageController(EventCreatePage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void onPageSelected() {
        ((EventCreatePage)getPage()).clear();
    }
}
