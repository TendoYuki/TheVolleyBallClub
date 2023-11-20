package com.volleyball.club.pages.competitions;

import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.pages.GUI;

/** Controls the competition result page */
public class CompetitionResultPageController extends NavigationController{
    /**
     * Creates the controller for the competition result page
     * @param page Competition result page
     * @param gui Main gui of the client
     */
    public CompetitionResultPageController(CompetitionResultPage page, GUI gui) {
        super(page, gui);
    }
    @Override
    public void onPageSelected() { }
}
