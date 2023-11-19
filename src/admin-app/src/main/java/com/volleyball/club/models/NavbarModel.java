package com.volleyball.club.models;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import com.volleyball.club.observation.Observable;

public class NavbarModel extends Observable{
    private List<Component> menus = new ArrayList<Component>(); 

    /**
     * Adds a menu at the end of the navbar
     * @param menu Menu to add
     */
    public void addMenu(Component menu) {
        // Avoid adding if already inside menus
        if(menus.contains(menu)) return;

        menus.add(menu);
        updateObservers();
    }

    /**
     * Replaces a given menu with another one
     * @param oldMenu Menu to replace
     * @param newMenu New menu
     */
    public void replaceMenu(Component oldMenu, Component newMenu) {
        // Avoid adding if already inside menus or if old menu doesn't exist
        if(!menus.contains(oldMenu)) return;
        if(menus.contains(newMenu)) return;

        int index = menus.indexOf(oldMenu);
        menus.remove(oldMenu);
        menus.add(index, newMenu);
        updateObservers();
    }

    /**
     * Adds a menu to the navbar at the given index
     * @param menu Menu to add
     * @param index Index of the menu
     */
    public void addMenu(Component menu, int index) {
        // Avoid adding if already inside menus
        if(menus.contains(menu)) return;

        if(index < 0) menus.add(menu);
        else menus.add(index, menu);
        updateObservers();
    }

    /**
     * Removes a menu from the navbar
     * @param menu Menu to remove
     */
    public void removeMenu(Component menu) {
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
