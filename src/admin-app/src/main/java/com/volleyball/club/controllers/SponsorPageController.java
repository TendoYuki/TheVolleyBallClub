package com.volleyball.club.controllers;

import java.awt.event.ActionEvent;

import com.volleyball.club.views.SponsorPage;
import com.volleyball.club.views.GUI;

public class SponsorPageController extends NavigationController {

    public SponsorPageController(SponsorPage page, GUI gui) {
        super(page, gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        ((SponsorPage)getPage()).loadResults();
    }
}
