package com.volleyball.club.pages.competitions;

import com.volleyball.club.datetime.DateTime;
import com.volleyball.club.observation.Observable;

/** Model representing a single competition */
public class CompetitionModel extends Observable{
    /** ID of the competition */
    private int ID;
    /** Start datetime of the competition */
    private DateTime startDateTime;
    /** End datetime of the competition */
    private DateTime endDateTime;
    /** Model of the competition's results*/
    private CompetitionResultModel resultModel;
    /** Model of the competition's location*/
    private String location;
    /** Max participants */
    private int maxParticipant;

    /**
     * Creates a new competition model
     * @param ID ID of the competition
     * @param startDateTime Start datetime of the competition
     * @param endDateTime End datetime of the competition
     */
    public CompetitionModel(int ID, DateTime startDateTime, DateTime endDateTime, CompetitionResultModel resultModel,int maxParticipant, String location) {
        this.resultModel = resultModel;
        this.ID = ID;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
        this.maxParticipant = maxParticipant;
    }

    /**
     * Creates a new competition model
     * @param ID ID of the competition
     * @param startDateTime Start datetime of the competition
     * @param endDateTime End datetime of the competition
     */
    public CompetitionModel(int ID, DateTime startDateTime, DateTime endDateTime, CompetitionResultModel resultModel, int maxParticipant) {
        this(ID, startDateTime, endDateTime, resultModel, maxParticipant, null);
    }

    /**
     * Clones a given competition model
     * @param model model to clone
     */
    public CompetitionModel(CompetitionModel model) {
        this.ID = model.ID;
        this.startDateTime = new DateTime(model.startDateTime);
        this.endDateTime = new DateTime(model.endDateTime);
        this.resultModel = model.resultModel;
        this.maxParticipant = model.maxParticipant;
        this.location = model.location;
    }

    /** Creates an empty competition model */
    public CompetitionModel() {
        resetDefaultValues();
    }

    /**
     * Changes the id of the competition stored in the model
     * @param id New id
     */
    public void setID(int id) {
        this.ID = id;
    }

    /**
     * Gets the id of the competition stored in the model
     * @return id of the competition stored in the model
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the start datetime of the competition stored in the model
     * @return start datetime of the competition stored in the model
     */
    public DateTime getStartDateTime() {
        return startDateTime;
    }


    /**
     * Changes the start datetime of the competition stored in the model
     * @param startDateTime new start datetime
     */
    public void setStartDateTime(DateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * Gets the end datetime of the competition stored in the model
     * @return end datetime of the competition stored in the model
     */
    public DateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Changes the end datetime of the competition stored in the model
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
        this.resultModel = null;
        this.location = null;
        this.maxParticipant = -1;
    }

    /**
     * Gets the results model
     * @return results model
     */
    public CompetitionResultModel getResultModel() {
        return resultModel;
    }

    public boolean hasResult() {
        if(resultModel == null) 
            return false;
        return resultModel.getID() != -1;
    }

    /**
     * Changes the results model
     * @param resultModel new result model
     */
    public void setResultModel(CompetitionResultModel resultModel) {
        this.resultModel = resultModel;
    }

    /**
     * Gets the location
     * @return location
     */
    public String getLocation() {
        return location;
    }
    /**
     * Changes the location
     * @param location new location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxParticipant() {
        return maxParticipant;
    }

    public void setMaxParticipant(int maxParticipant) {
        this.maxParticipant = maxParticipant;
    }

    
}
