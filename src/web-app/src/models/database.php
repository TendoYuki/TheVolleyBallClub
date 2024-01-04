<?php
    $serverName = "localhost";
    $username = "root";
    $password = "Pabloescobar";
    $database = "volleyball_club";
    $con;

    try{
        $con = new PDO("mysql:host=$serverName;dbname=$database",$username,$password);
        $con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    }catch(PDOException $e){
        echo "Connection failed: " . $e->getMessage();
    }