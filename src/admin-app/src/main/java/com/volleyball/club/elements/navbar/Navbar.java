package com.volleyball.club.elements.navbar;

import javax.swing.JMenuBar;

import com.volleyball.club.models.NavbarModel;
import com.volleyball.club.observation.Observable;
import com.volleyball.club.observation.Observer;

/**
 * Navigation bar that can be dynamically changed 
 */
public class Navbar extends JMenuBar implements Observer{
    /**
     * Creates a navbar displaying a navbar model
     * @param model Model to display
     */
    public Navbar(NavbarModel model) {
        super();
        updateMenus(model);
    }

    /**
     * Refreshes the display of the model
     * @param model
     */
    private void updateMenus(NavbarModel model) {
        removeAll();
        model.getMenus().forEach(menu -> {
            add(menu);
        });
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
