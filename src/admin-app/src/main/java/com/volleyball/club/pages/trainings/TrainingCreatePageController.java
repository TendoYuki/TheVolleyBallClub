package com.volleyball.club.pages.trainings;

import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.pages.GUI;

public class TrainingCreatePageController extends NavigationController {
    public TrainingCreatePageController(TrainingCreatePage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void onPageSelected() {
        ((TrainingCreatePage)getPage()).clear();
    }
}
