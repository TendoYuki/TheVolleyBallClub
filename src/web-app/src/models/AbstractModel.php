<?php
include_once("/srv/http/endpoint/config/config.php");
include_once(MODELS.'database.php');

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