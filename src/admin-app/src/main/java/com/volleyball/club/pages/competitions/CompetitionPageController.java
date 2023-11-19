package com.volleyball.club.pages.competitions;

import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.pages.GUI;

public class CompetitionPageController extends NavigationController {

    public CompetitionPageController(CompetitionPage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void onPageSelected() {
        ((CompetitionPage)getPage()).loadResults();
    }
}
