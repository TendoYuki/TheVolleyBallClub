package com.volleyball.club.pages.trainings;

import com.volleyball.club.mvc.Observable;
import com.volleyball.club.pages.DateTime;

public class TrainingModel extends Observable{

    private int ID;
    private DateTime startDateTime;
    private DateTime endDateTime;

    public TrainingModel(int ID, DateTime startDateTime, DateTime endDateTime) {
        this.ID = ID;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public TrainingModel(TrainingModel model) {
        this.ID = model.ID;
        this.startDateTime = new DateTime(model.startDateTime);
        this.endDateTime = new DateTime(model.endDateTime);
    }

    public TrainingModel() {
        this.ID = -1;
        this.startDateTime = null;
        this.endDateTime = null;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public int getID() {
        return ID;
    }

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(DateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public DateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(DateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void resetDefaultValues() {
        this.ID = -1;
        this.startDateTime = null;
        this.endDateTime = null;
    }
}
