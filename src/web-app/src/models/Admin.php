<?php

namespace Models;
use Database\DatabaseConnection;

class Admin extends AbstractModel{
    private int $id;
    private string $password;
    private string $login;

    private function __construct($id){
        parent::__construct();
        $this->id = $id;
        $stmt = $this->getConnection()->prepare('SELECT * FROM admin WHERE idAdmin=?');
        $stmt->bindValue(1, $id);
        $stmt->execute();
        $admin = $stmt->fetch();

        $this->login = $admin["loginAdmin"];
        $this->password = $admin["passwordAdmin"];
    }

    public static function fetch($id): Admin{
        return new Admin($id);
    }

    public function getId() {
        return $this->id;
    }

    public function getPassword() {
        return $this->password;
    }
    public function setPassword(string $password) {
        $this->password = $password;
        return $this;
    }


    public function getLogin() {
        return $this->login;
    }
    public function setLogin(string $login) {
        $this->login = $login;
        return $this;
    }

    public function updateDatabase() {
        $stmt = $this->getConnection()->prepare(
            'UPDATE user
            SET loginAdmin=?,
            passwordAdmin=?,
            WHERE idUser=?'
        );
        $stmt->bindValue(1, $this->login);
        $stmt->bindValue(2, $this->password);
        $stmt->bindValue(10, $this->id);
        $stmt->execute();
    }

    /**
     * Creates a new admin in the database
     * @return int id of the new entry
     */
    public static function new(
        string $login,
        string $password
    ) {
        $connection = new DatabaseConnection();
        $stmt = $connection->getConnection()->prepare(
            'INSERT INTO user (
                loginAdmin,
                passwordAdmin 	
            ) 
            VALUES (?,?)'
        );
        $stmt->bindValue(1, $login);
        $stmt->bindValue(2, $password);

        $stmt->execute();
        return $connection->getConnection()->lastInsertId();
    }

    /**
     * Deletes from the database the admin having the given id
     * @param int $id id of the admin to delete
     */
    public static function delete(int $id) {
        $connection = new DatabaseConnection();
        $stmt = $connection->getConnection()->prepare(
            'DELETE FROM admin WHERE idAdmin=?'
        );
        $stmt->bindValue(1, $id);
        $stmt->execute();
    }
}