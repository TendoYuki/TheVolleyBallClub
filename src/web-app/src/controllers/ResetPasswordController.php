<?php

namespace Controllers;

include_once("/srv/http/endpoint/config/config.php");

use Database\DatabaseConnection;

if(isset($_SESSION['userConnect']) || isset($_SESSION['adminConnect'])) {
    header("Location: /"); 
}
if(isset($_POST["email-field"]))   {
    $connection = new DatabaseConnection();

    // Verify if it's a user
    $stmt = $connection->getConnection()->prepare('SELECT * FROM user WHERE emailUser=?');
    $stmt->execute([$_POST['email-field']]);
    $user = $stmt->fetch();
    // Verify if it's an admin
    $stmt = $connection->getConnection()->prepare('SELECT * FROM admin WHERE loginAdmin=?');
    $stmt->execute([$_POST['email-field']]);
    $admin = $stmt->fetch();
    
    if(isset($user['idUser'])) {
        // TODO : SEND MAIL TO CONFIRM ID
    }
    else if(isset($admin['idAdmin'])) {
        // TODO : SEND MAIL TO CONFIRM ID
    }
    // User not in the database
    else{
        $_SESSION["error"] = "Aucun compte n'est lié à cette adresse mail";
        header('Location: /connection/forgot-password');
    }
}   