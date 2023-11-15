package com.volleyball.club.models;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;

import com.volleyball.club.mvc.Observable;

public class NavbarModel extends Observable{
    private List<Component> menus = new ArrayList<Component>(); 

    /**
     * Adds a menu to the navbar
     * @param menu Menu to add
     */
    public void addMenu(Component menu) {
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
    public List<Component> getMenus() {
        return new ArrayList<Component>(menus);
    }

}
