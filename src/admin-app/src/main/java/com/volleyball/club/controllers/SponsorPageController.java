package com.volleyball.club.controllers;

import com.volleyball.club.views.GUI;
import com.volleyball.club.views.sponsors.SponsorPage;

public class SponsorPageController extends NavigationController {

    public SponsorPageController(SponsorPage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void onPageSelected() {
        ((SponsorPage)getPage()).loadResults();
    }
}
