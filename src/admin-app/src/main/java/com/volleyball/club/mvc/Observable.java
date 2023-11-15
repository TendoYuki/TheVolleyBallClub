package com.volleyball.club.mvc;

import java.util.List;
import java.util.ArrayList;

public class Observable {
    private List<Observer> observers = new ArrayList<Observer>();
    /**
     * Adds an observer to its list
     * @param obs Observer to add
     */
    public void addObserver(Observer obs) {
        if(!observers.contains(obs)) observers.add(obs);
    }
    /**
     * Removes an observer from its list
     * @param obs Observer to remove
     */
    public void removeObserver(Observer obs) {
        if(observers.contains(obs)) observers.remove(obs);
    }
    /**
     * Notifies every observer by calling their update method with the current observable passed as a parameter.
     */
    public void updateObservers() {
        observers.forEach(observer -> {
            observer.update(this);
        });
    }
}
