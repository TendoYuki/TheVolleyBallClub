<?php

namespace Models;
use Database\DatabaseConnection;

class Competition extends AbstractModel{
    private int $id;
    private string $start_date_time;
    private string $end_date_time;
    private int | null $result_id;
    private int $location_id;
    private int $max_participant_competition; 	
    private array $participants = array();

    private static int $MAX_TIME_BEFORE_INSCRIPTION = 86400; // in seconds

    private function __construct($id) {
        parent::__construct();
        $this->id = $id;
        $stmt = $this->getConnection()->prepare('SELECT * FROM competition WHERE idCompetition=?');
        $stmt->bindValue(1, $id);
        $stmt->execute();
        $competition = $stmt->fetch();

        $this->start_date_time = $competition["startDateTimeCompetition"];
        $this->end_date_time = $competition["endDateTimeCompetition"];
        $this->result_id = $competition["Result_idResult"];
        $this->location_id = $competition["Location_idLocation"];
        $this->max_participant_competition = $competition["maxParticipantCompetition"];

        // Fetches all the users participating to the competition
        $stmt = $this->getConnection()->prepare('SELECT * FROM user_has_competition WHERE Competition_idCompetition=?');
        $stmt->bindValue(1, $id);
        $stmt->execute();
        $participants = $stmt->fetchAll();
        foreach($participants as $participant) array_push($this->participants, $participant["User_idUser"]);
    }

    /**
     * Adds the given participant to the event if it is possible
     * NOTE : does not require to call updateDatabase() 
     * As it instantly modifies the database
     * @param int $id Id of the participant to add
     * @return bool True if the addition was successful, else false
     */
    public function addParticipant(int $id): bool {
        if($this->placesLeft() <= 0 || $this->hasExpired()) return false;

        $stmt = $this->getConnection()->prepare(
            'INSERT INTO user_has_competition 
            (User_idUser, Competition_idCompetition)
            VALUES (?,?)'
        );
        $stmt->bindValue(1, $id);
        $stmt->bindValue(2, $this->id);
        $stmt->execute();

        // Adds the participant to the participants array
        array_push($this->participants, $id);
        return true;
    }

    /**
     * Removes the given participant to the event if it is possible
     * NOTE : does not require to call updateDatabase() 
     * As it instantly modifies the database
     * @param int $id Id of the participant to remove
     * @return bool True if the removal was successful, else false
     */
    public function removeParticipant(int $id): bool {
        if(!in_array($id, $this->participants) || $this->hasExpired()) return false;

        $stmt = $this->getConnection()->prepare(
            'DELETE FROM user_has_competition
            WHERE User_idUser=? AND Competition_idCompetition=?'
        );
        $stmt->bindValue(1, $id);
        $stmt->bindValue(2, $this->id);
        $stmt->execute();

        // Removes the participant from the participants array
        $this->participants = array_diff($this->participants, array($id));
        return true;
    }

    /**
     * Returns the number of places left in the competition
     * @return int Places left in the competition
     */
    public function placesLeft(): int {
        return $this->max_participant_competition - count($this->participants);
    }

    /**
     * Checks if the competition has expired (start date passed)
     * @return bool True if has expired, else false
     */
    public function hasExpired(): bool {
        return time() > (strtotime($this->start_date_time) - Competition::$MAX_TIME_BEFORE_INSCRIPTION);
    }

    public static function fetch($id): Competition{
        return new Competition($id);
    }

    /**
     * Creates a new competition in the database
     * @return int id of the new competition
     */
    public static function new(
        string $start_date_time,
        string $end_date_time,
        int $location_id,
        int $max_participant_competition,
        int $result_id = null
    ): int {
        $connection = new DatabaseConnection();
        $stmt = $connection->getConnection()->prepare(
            'INSERT INTO competition (
                startDateTimeCompetition,
                endDateTimeCompetition,
                Result_idResult,
                Location_idLocation,
                maxParticipantCompetition
            ) 
            VALUES (?,?,?,?)'
        );
        $stmt->bindValue(1, $start_date_time);
        $stmt->bindValue(2, $end_date_time);
        $stmt->bindValue(3, $result_id);
        $stmt->bindValue(4, $location_id);
        $stmt->bindValue(5, $max_participant_competition);

        $stmt->execute();
        return $connection->getConnection()->lastInsertId();

    }
    public static function delete(int $id) {
        $connection = new DatabaseConnection();
        $stmt = $connection->getConnection()->prepare(
            'DELETE FROM competition WHERE idCompetition=?'
        );
        $stmt->bindValue(1, $id);
        $stmt->execute();
    }
    public function updateDatabase() {
        $stmt = $this->getConnection()->prepare(
            'UPDATE competition
            SET startDateTimeCompetition=?,
            endDateTimeCompetition=?,
            Result_idResult=?,
            Location_idLocation=?,
            maxParticipantCompetition=?
            WHERE idCompetition=?'
        );
        $stmt->bindValue(1, $this->start_date_time);
        $stmt->bindValue(2, $this->end_date_time);
        $stmt->bindValue(3, $this->result_id);
        $stmt->bindValue(4, $this->location_id);
        $stmt->bindValue(5, $this->max_participant_competition);
        $stmt->bindValue(6, $this->id);
        $stmt->execute();
    }
}