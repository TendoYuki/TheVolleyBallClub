package com.volleyball.club.observation;

/** Interface that can observe an observable class */
public interface Observer {
    /**
     * Called by the observable when its value changes
     * @param observable Observable that called the update method
     */
    public void update(Observable observable);
}
