package com.volleyball.club.observation;

import java.util.List;
import java.util.ArrayList;

/** Generic observable class */
public abstract class Observable {
    /** List of all observers */
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
