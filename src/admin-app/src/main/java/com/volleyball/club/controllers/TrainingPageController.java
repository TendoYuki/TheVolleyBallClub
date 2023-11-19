package com.volleyball.club.controllers;

import com.volleyball.club.views.GUI;
import com.volleyball.club.views.trainings.TrainingPage;

public class TrainingPageController extends NavigationController {
    public TrainingPageController(TrainingPage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void onPageSelected() {
        ((TrainingPage)getPage()).loadResults();
    }
}
