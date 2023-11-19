package com.volleyball.club.elements.navbar;

import javax.swing.JMenuBar;

import com.volleyball.club.models.NavbarModel;
import com.volleyball.club.observation.Observable;
import com.volleyball.club.observation.Observer;

public class Navbar extends JMenuBar implements Observer{
    private void updateMenus(NavbarModel model) {
        removeAll();
        model.getMenus().forEach(menu -> {
            add(menu);
        });
    }

    public Navbar(NavbarModel model) {
        super();
        updateMenus(model);
    }

    @Override
    public void update(Observable observable) {
        if(!(observable instanceof NavbarModel)) return;
        NavbarModel model = (NavbarModel)observable;
        updateMenus(model);

        validate();
        repaint();
    }
}
