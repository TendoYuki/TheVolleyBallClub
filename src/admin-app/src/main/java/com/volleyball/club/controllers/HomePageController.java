package com.volleyball.club.controllers;

import com.volleyball.club.views.GUI;
import com.volleyball.club.views.HomePage;

public class HomePageController extends NavigationController{
    public HomePageController(HomePage page, GUI gui) {
        super(page, gui);
    }
    
    @Override
    public void onPageSelected() { }
}
