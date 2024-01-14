<?php

namespace Models;
use Database\DatabaseConnection;

class Location extends AbstractModel{
    private int $id;
    private string $name;
    private string $city;
    private int $post_code;
    private string $address; 	

    private function __construct($id){
        parent::__construct();
        $this->id = $id;
        $stmt = $this->getConnection()->prepare('SELECT * FROM `location` WHERE idLocation=?');
        $stmt->bindValue(1, $id);
        $stmt->execute();
        $admin = $stmt->fetch();

        $this->name = $admin["nameLocation"];
        $this->city = $admin["cityLocation"];
        $this->post_code = $admin["postCodeLocation"];
        $this->address = $admin["addressLocation"]; 
    }

    public static function fetch($id): Location{
        return new Location($id);
    }

    public function getId() {
        return $this->id;
    }

    public function getName() {
        return $this->name;
    }
    public function setName(string $name) {
        $this->name = $name;
        return $this;
    }

    public function getCity() {
        return $this->city;
    }
    public function setCity(string $city) {
        $this->city = $city;
        return $this;
    }

    public function getAddress() {
        return $this->address;
    }
    public function setAddress(string $address) {
        $this->address = $address;
        return $this;
    }

    public function getPostCode() {
        return $this->post_code;
    }
    public function setPostCode(int $post_code) {
        $this->post_code = $post_code;
        return $this;
    }

    public function updateDatabase() {
        $stmt = $this->getConnection()->prepare(
            'UPDATE `location`
            SET cityLocation=?,
            postCodeLocation=?,
            addressLocation=?,
            nameLocation=? 	
            WHERE idPartner=?'
        );
        $stmt->bindValue(1, $this->city);
        $stmt->bindValue(2, $this->post_code);
        $stmt->bindValue(3, $this->address);
        $stmt->bindValue(4, $this->name);
        $stmt->bindValue(5, $this->id);
        $stmt->execute();
    }

    /**
     * Creates a new group in the database
     * @return int id of the new entry
     */
    public static function new(
        string $city,
        int $post_code,
        string $address,
        string $name
    ) {
        $connection = new DatabaseConnection();
        $stmt = $connection->getConnection()->prepare(
            'INSERT INTO `location` (
                cityLocation,
                postCodeLocation,
                addressLocation,
                nameLocation 	
            ) 
            VALUES (?,?,?,?)'
        );
        $stmt->bindValue(1, $city);
        $stmt->bindValue(2, $post_code);
        $stmt->bindValue(3, $address);
        $stmt->bindValue(4, $name);

        $stmt->execute();
        return $connection->getConnection()->lastInsertId();
    }

    /**
     * Deletes from the database the group having the given id
     * @param int $id id of the group to delete
     */
    public static function delete(int $id) {
        $connection = new DatabaseConnection();
        $stmt = $connection->getConnection()->prepare(
            'DELETE FROM `location` WHERE idLocation=?'
        );
        $stmt->bindValue(1, $id);
        $stmt->execute();
    }
}