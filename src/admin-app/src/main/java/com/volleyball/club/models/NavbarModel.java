package com.volleyball.club.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;

import com.volleyball.club.mvc.Observable;

public class NavbarModel extends Observable{
    private List<JMenu> menus = new ArrayList<JMenu>(); 

    /**
     * Adds a menu to the navbar
     * @param menu Menu to add
     */
    public void addMenu(JMenu menu) {
        // Avoid adding if already inside menus
        if(menus.contains(menu)) return;

        menus.add(menu);
        updateObservers();
    }

    /**
     * Removes a menu from the navbar
     * @param menu Menu to remove
     */
    public void removeMenu(JMenu menu) {
        // Avoid removing if already inside menus
        if(!menus.contains(menu)) return;
        
        menus.remove(menu);
        updateObservers();
    }

    /**
     * Returns all menus of the navbar
     * @return menus of the navbar
     */
    public List<JMenu> getMenus() {
        return new ArrayList<JMenu>(menus);
    }

}
