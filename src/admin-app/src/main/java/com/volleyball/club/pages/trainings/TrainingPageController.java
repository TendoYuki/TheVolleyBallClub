package com.volleyball.club.pages.trainings;

import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.pages.GUI;

public class TrainingPageController extends NavigationController {
    public TrainingPageController(TrainingPage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void onPageSelected() {
        ((TrainingPage)getPage()).loadResults();
    }
}
