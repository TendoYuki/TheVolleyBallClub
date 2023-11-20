package com.volleyball.club.pages.competitions;

import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.pages.GUI;

/** Controls the competition page */
public class CompetitionPageController extends NavigationController {
    /**
     * Creates the controller for the competition page
     * @param page Competition page
     * @param gui Main gui of the client
     */
    public CompetitionPageController(CompetitionPage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void onPageSelected() {
        ((CompetitionPage)getPage()).clearEditor();
        ((CompetitionPage)getPage()).loadResults();
    }
}
