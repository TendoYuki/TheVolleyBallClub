package com.volleyball.club.pages.competitions;

import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.pages.GUI;

/** Controls the competition result creation page */
public class CompetitionCreateResultPageController extends NavigationController{
    /**
     * Creates the controller for the competition result creation page
     * @param page Result Creation page
     * @param gui Main gui of the client
     */
    public CompetitionCreateResultPageController(CompetitonResultCreatePage page, GUI gui) {
        super(page, gui);
    }
    @Override
    public void onPageSelected() {
        ((CompetitonResultCreatePage)getPage()).clear();
    }
}
