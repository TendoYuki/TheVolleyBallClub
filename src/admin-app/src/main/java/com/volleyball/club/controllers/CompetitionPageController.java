package com.volleyball.club.controllers;

import com.volleyball.club.views.GUI;
import com.volleyball.club.views.competitions.CompetitionPage;

public class CompetitionPageController extends NavigationController {

    public CompetitionPageController(CompetitionPage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void onPageSelected() {
        ((CompetitionPage)getPage()).loadResults();
    }
}
