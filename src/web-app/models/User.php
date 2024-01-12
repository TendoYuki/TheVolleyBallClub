<?php

namespace Models;

include_once("/srv/http/endpoint/config/config.php");

use Optimizations\ImageCompressor;

class User extends AbstractModel{
    private int $id;
    private string $name;
    private string $surname;
    private string $password;
    private string $email;
    private string $birthdate;
    private string $register_date;
    private int $gender;
    private int $group_id;
    private int $payment_id;
    private string $image_user;

    public function __construct($id){
        parent::__construct();
        $this->id = $id;
        $stmt = $this->getConnection()->prepare('SELECT * FROM user WHERE idUser=?');
        $stmt->bindValue(1, $id);
        $stmt->execute();
        $user = $stmt->fetch();

        $this->name = $user["nameUser"];
        $this->surname = $user["surnameUser"];
        $this->password = $user["passwordUser"];
        $this->email = $user["emailUser"];
        $this->birthdate = $user["birthdateUser"];
        $this->register_date = $user["registerDate"];
        $this->gender = $user["gender"];
        $this->group_id = $user["Group_idGroup"];
        $this->payment_id = $user["payment_idPayment"];
        $this->image_user = $user["imageUser"];
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


    public function getSurname() {
        return $this->surname;
    }
    public function setSurname(string $surname) {
        $this->surname = $surname;
        return $this;
    }


    public function getPassword() {
        return $this->password;
    }
    public function setPassword(string $password) {
        $this->password = $password;
        return $this;
    }


    public function getEmail() {
        return $this->email;
    }
    public function setEmail(string $email) {
        $this->email = $email;
        return $this;
    }


    public function getGender() {
        return $this->gender;
    }
    public function setGender(int $gender) {
        $this->gender = $gender;
        return $this;
    }


    public function getRegisterDate() {
        return $this->register_date;
    }


    public function getBirthdate() {
        return $this->birthdate;
    }
    public function setBirthdate(string $birthdate) {
        $this->birthdate = $birthdate;
        return $this;
    }


    public function getImageUser() {
        return $this->image_user;
    }
    public function setImageUser(string $image_user) {
        $this->image_user = $image_user;
        return $this;
    }


    public function getGroupID() {
        return $this->group_id;
    }
    public function setGroupID(int $group_id) {
        $this->group_id = $group_id;
        return $this;
    }


    public function getPaymentID() {
        return $this->payment_id;
    }
    public function setPaymentID(int $payment_id) {
        $this->payment_id = $payment_id;
        return $this;
    }

    public function updateDatabase() {
        $stmt = $this->getConnection()->prepare(
            'UPDATE user
            SET nameUser=?,
            surnameUser=?,
            passwordUser=?,
            emailUser=?,
            birthdateUser=?,
            gender=?,
            imageUser=?,
            Group_idGroup=?,
            payment_idPayment=?
            WHERE idUser=?'
        );
        $stmt->bindValue(1, $this->name);
        $stmt->bindValue(2, $this->surname);
        $stmt->bindValue(3, $this->password);
        $stmt->bindValue(4, $this->email);
        $stmt->bindValue(5, $this->birthdate);
        $stmt->bindValue(6, $this->gender);
        $stmt->bindValue(7, $this->image_user);
        $stmt->bindValue(8, $this->group_id);
        $stmt->bindValue(9, $this->payment_id);
        $stmt->bindValue(10, $this->id);
        $stmt->execute();
    }

    /**
     * Creates a new user in the database
     * @return int id of the new entry
     */
    public static function new(
        string $name,
        string $surname,
        string $password,
        string $email,
        string $birthdate,
        int $gender,
        string $image_user,
        int $group_id,
        int $payment_id
    ) {
        date_default_timezone_set('Europe/Paris');

        $connection = new DatabaseConnection();
        $stmt = $connection->getConnection()->prepare(
            'INSERT INTO user (
                nameUser,
                surnameUser,
                passwordUser,
                emailUser,
                birthdateUser,
                gender,
                imageUser,
                Group_idGroup,
                payment_idPayment,
                registerDate
            ) 
            VALUES (?,?,?,?,?,?,?,?,?,?)'
        );
        $stmt->bindValue(1, $name);
        $stmt->bindValue(2, $surname);
        $stmt->bindValue(3, $password);
        $stmt->bindValue(4, $email);
        $stmt->bindValue(5, $birthdate);
        $stmt->bindValue(6, $gender);
        $stmt->bindValue(7, ImageCompressor::compress($image_user));
        $stmt->bindValue(8, $group_id);
        $stmt->bindValue(9, $payment_id);
        $stmt->bindValue(10, date('Y-m-d'));

        $stmt->execute();
        return $connection->getConnection()->lastInsertId();
    }

    /**
     * Deletes from the database the user having the given id
     * @param int $id id of the user to delete
     */
    public static function delete(int $id) {
        $connection = new DatabaseConnection();
        $stmt = $connection->getConnection()->prepare(
            'DELETE FROM user WHERE idUser=?'
        );
        $stmt->bindValue(1, $id);
        $stmt->execute();
    }
}