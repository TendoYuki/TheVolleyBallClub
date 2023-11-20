package com.volleyball.club.pages.competitions;

import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.pages.GUI;

public class CompetitionCreateResultPageController extends NavigationController{
    public CompetitionCreateResultPageController(CompetitonResultCreatePage page, GUI gui) {
        super(page, gui);
    }
    @Override
    public void onPageSelected() {
        ((CompetitonResultCreatePage)getPage()).clear();
    }
}
