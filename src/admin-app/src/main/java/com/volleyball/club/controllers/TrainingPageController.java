package com.volleyball.club.controllers;

import java.awt.event.ActionEvent;

import com.volleyball.club.views.TrainingPage;
import com.volleyball.club.views.GUI;

public class TrainingPageController extends NavigationController {

    public TrainingPageController(TrainingPage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        ((TrainingPage)getPage()).loadResults();
    }
}
