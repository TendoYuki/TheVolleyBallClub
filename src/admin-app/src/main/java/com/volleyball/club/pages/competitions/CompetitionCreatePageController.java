package com.volleyball.club.pages.competitions;

import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.pages.GUI;

/** Controls the competition creation page */
public class CompetitionCreatePageController extends NavigationController {
    /**
     * Creates the controller for the competition creation page
     * @param page Competition Creation page
     * @param gui Main gui of the client
     */
    public CompetitionCreatePageController(CompetitionCreatePage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void onPageSelected() {
        ((CompetitionCreatePage)getPage()).clear();
    }
}
