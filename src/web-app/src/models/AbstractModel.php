<?php

namespace Models;

use Database\DatabaseConnection;

abstract class AbstractModel {
    private $connection;

    public function __construct(){
        $this->connection = new DatabaseConnection();
    }
    public abstract static function delete(int $id);
    public abstract function updateDatabase();

    public function getConnection() {
        return $this->connection->getConnection();
    }
}