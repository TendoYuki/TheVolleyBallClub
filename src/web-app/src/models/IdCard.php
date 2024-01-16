<?php

namespace Models;
use Database\DatabaseConnection;

class IdCard extends AbstractModel{
    private int $id;
    private string $blob;
    private bool $is_valid;

    private function __construct($id){
        parent::__construct();
        $this->id = $id;
        $stmt = $this->getConnection()->prepare('SELECT * FROM idCard WHERE idCardId=?');
        $stmt->bindValue(1, $id);
        $stmt->execute();
        $medical_certificate = $stmt->fetch();

        $this->blob = $medical_certificate["idCard"];
        $this->is_valid = $medical_certificate["valid"];
    }

    public static function fetch($id): IdCard{
        return new IdCard($id);
    }

    public function getId() {
        return $this->id;
    }

    public function getDocument() {
        return $this->blob;
    }
    public function setDocument(string $blob): IdCard {
        $this->blob = $blob;
        return $this;
    }

    public function getIsValid() {
        return $this->is_valid;
    }
    public function setIsValid(bool $is_valid): IdCard {
        $this->is_valid = $is_valid;
        return $this;
    }

    public function updateDatabase() {
        $stmt = $this->getConnection()->prepare(
            'UPDATE idCard
            SET idCard=?,
            valid=?
            WHERE idCardId=?'
        );
        $stmt->bindValue(1, $this->blob);
        $stmt->bindValue(2, $this->is_valid);
        $stmt->bindValue(3, $this->id);
        $stmt->execute();
    }

    /**
     * Creates a new medicalcertificate in the database
     * @return int id of the new entry
     */
    public static function new(
        string $blob
    ) {
        $connection = new DatabaseConnection();
        $stmt = $connection->getConnection()->prepare(
            'INSERT INTO idCard (
                idCard,
                valid
            ) 
            VALUES (?,NULL)'
        );
        $stmt->bindValue(1, $blob);

        $stmt->execute();
        return $connection->getConnection()->lastInsertId();
    }

    /**
     * Deletes from the database the medicalcertificate having the given id
     * @param int $id id of the medicalcertificate to delete
     */
    public static function delete(int $id) {
        $connection = new DatabaseConnection();
        $stmt = $connection->getConnection()->prepare(
            'DELETE FROM idCard WHERE idCardId=?'
        );
        $stmt->bindValue(1, $id);
        $stmt->execute();
    }
}