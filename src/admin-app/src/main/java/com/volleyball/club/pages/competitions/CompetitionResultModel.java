package com.volleyball.club.pages.competitions;

import com.volleyball.club.observation.Observable;

/** Model representing a competition result */
public class CompetitionResultModel extends Observable{
    /** ID of the competiton result */
    private int ID;
    
    /** Number of victories of the club */
    private int victoriesCount;

    /** Number of defeat of the club */
    private int defeatCount;

    /** Ranking of the club */
    private int ranking;

    /** Total number of club */
    private int clubCount;

    /**
     * Creates a competition result model
     * @param ID ID of the result
     * @param victoriesCount Number of victories of the club
     * @param defeatCount Number of looses of the club
     * @param ranking Ranking of the club
     * @param clubCount Total number of clubs
     */
    public CompetitionResultModel(int ID, int victoriesCount, int defeatCount, int ranking, int clubCount) {
        this.ID = ID;
        this.victoriesCount = victoriesCount;
        this.defeatCount = defeatCount;
        this.ranking = ranking;
        this.clubCount = clubCount;
    }

    public CompetitionResultModel(){
        resetDefaultValues();
    }

    /**
     * Gets the id of the result
     * @return id of the result
     */
    public int getID() {
        return ID;
    }

    /**
     * Changes the id of the result
     * @param iD new id
     */
    public void setID(int iD) {
        ID = iD;
    }

    /**
     * Gets the number of victories of the club
     * @return Number of victories of the club
     */
    public int getVictoriesCount() {
        return victoriesCount;
    }

    /**
     * Changes the count of club's victories
     * @param victoriesCount new victories count
     */
    public void setVictoriesCount(int victoriesCount) {
        this.victoriesCount = victoriesCount;
    }

    /**
     * Gets the number of defeats of the club
     * @return Number of defeats of the club
     */
    public int getDefeatCount() {
        return defeatCount;
    }

    /**
     * Changes the count of defeats of the club
     * @param defeatCount new count of defeats of the club
     */
    public void setDefeatCount(int defeatCount) {
        this.defeatCount = defeatCount;
    }

    /**
     * Gets the ranking of the club
     * @return Ranking of the club
     */
    public int getRanking() {
        return ranking;
    }

    /**
     * Changes the ranking of the club
     * @param ranking New ranking of the club
     */
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    /**
     * Gets the total count of clubs
     * @return Total count of clubs
     */
    public int getClubCount() {
        return clubCount;
    }

    /**
     * Changes the total count of clubs
     * @param clubCount New total count of clubs
     */
    public void setClubCount(int clubCount) {
        this.clubCount = clubCount;
    }

    public void resetDefaultValues() {
        this.ID = -1;
        this.victoriesCount = 0;
        this.defeatCount = 0;
        this.ranking = 1;
        this.clubCount = 0;
    }
}
