package com.volleyball.club.pages.sponsors;

import com.volleyball.club.controllers.NavigationController;
import com.volleyball.club.pages.GUI;

public class SponsorPageController extends NavigationController {

    public SponsorPageController(SponsorPage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void onPageSelected() {
        ((SponsorPage)getPage()).loadResults();
    }
}
