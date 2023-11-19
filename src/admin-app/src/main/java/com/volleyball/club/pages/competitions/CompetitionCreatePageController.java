package com.volleyball.club.pages.competitions;

import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.pages.GUI;

public class CompetitionCreatePageController extends NavigationController {
    public CompetitionCreatePageController(CompetitionCreatePage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void onPageSelected() {
        ((CompetitionCreatePage)getPage()).clear();
    }
}
