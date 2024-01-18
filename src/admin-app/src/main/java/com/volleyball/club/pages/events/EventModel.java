package com.volleyball.club.pages.events;

import com.volleyball.club.datetime.DateTime;
import com.volleyball.club.observation.Observable;

/** Model representing a single event */
public class EventModel extends Observable{
    /** ID of the event */
    private int ID;
    /** Start datetime of the event */
    private DateTime startDateTime;
    /** End datetime of the event */
    private DateTime endDateTime;
    /** Name of the event */
    private String name;
    /** Description of the event */
    private String description;

    /**
     * Creates a new event model
     * @param ID ID of the event
     * @param startDateTime Start datetime of the event
     * @param endDateTime End datetime of the event
     */
    public EventModel(int ID, DateTime startDateTime, DateTime endDateTime, String name, String description) {
        this.ID = ID;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.name = name;
        this.description = description;
    }

    /**
     * Clones a given event model
     * @param model model to clone
     */
    public EventModel(EventModel model) {
        this.ID = model.ID;
        this.startDateTime = new DateTime(model.startDateTime);
        this.endDateTime = new DateTime(model.endDateTime);
        this.name = model.name;
        this.description = model.description;
    }

    /** Creates an empty event model */
    public EventModel() {
        resetDefaultValues();
    }

    /**
     * Changes the id of the event stored in the model
     * @param id New id
     */
    public void setID(int id) {
        this.ID = id;
    }

    /**
     * Gets the id of the event stored in the model
     * @return id of the event stored in the model
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the start datetime of the event stored in the model
     * @return start datetime of the event stored in the model
     */
    public DateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Changes the start datetime of the event stored in the model
     * @param startDateTime new start datetime
     */
    public void setStartDateTime(DateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * Gets the end datetime of the event stored in the model
     * @return end datetime of the event stored in the model
     */
    public DateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Changes the end datetime of the event stored in the model
     * @param endDateTime new end datetime
     */
    public void setEndDateTime(DateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /** Resets the value of the model to null and id to -1 */
    public void resetDefaultValues() {
        this.ID = -1;
        this.startDateTime = null;
        this.endDateTime = null;
        this.name = null;
        this.description = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
