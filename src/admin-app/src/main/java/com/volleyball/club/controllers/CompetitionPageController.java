package com.volleyball.club.controllers;

import java.awt.event.ActionEvent;

import com.volleyball.club.views.CompetitionPage;
import com.volleyball.club.views.GUI;

public class CompetitionPageController extends NavigationController {

    public CompetitionPageController(CompetitionPage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        ((CompetitionPage)getPage()).loadResults();
    }
}
