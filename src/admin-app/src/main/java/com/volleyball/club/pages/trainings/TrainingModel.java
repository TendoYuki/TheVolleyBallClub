package com.volleyball.club.pages.trainings;

import com.volleyball.club.datetime.DateTime;
import com.volleyball.club.observation.Observable;

/** Model representing a single training */
public class TrainingModel extends Observable{
    /** ID of the training */
    private int ID;
    /** Start datetime of the training */
    private DateTime startDateTime;
    /** End datetime of the training */
    private DateTime endDateTime;

    /**
     * Creates a new training model
     * @param ID ID of the training
     * @param startDateTime Start datetime of the training
     * @param endDateTime End datetime of the training
     */
    public TrainingModel(int ID, DateTime startDateTime, DateTime endDateTime) {
        this.ID = ID;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Clones a given training model
     * @param model model to clone
     */
    public TrainingModel(TrainingModel model) {
        this.ID = model.ID;
        this.startDateTime = new DateTime(model.startDateTime);
        this.endDateTime = new DateTime(model.endDateTime);
    }

    /** Creates an empty training model */
    public TrainingModel() {
        this.ID = -1;
        this.startDateTime = null;
        this.endDateTime = null;
    }

    /**
     * Changes the id of the training stored in the model
     * @param id New id
     */
    public void setID(int id) {
        this.ID = id;
    }

    /**
     * Gets the id of the training stored in the model
     * @return id of the training stored in the model
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the start datetime of the training stored in the model
     * @return start datetime of the training stored in the model
     */
    public DateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Changes the start datetime of the training stored in the model
     * @param startDateTime new start datetime
     */
    public void setStartDateTime(DateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * Gets the end datetime of the training stored in the model
     * @return end datetime of the training stored in the model
     */
    public DateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Changes the end datetime of the training stored in the model
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
    }
}
