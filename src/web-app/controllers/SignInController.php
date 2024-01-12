<?php

namespace Controllers;

include_once("/srv/http/endpoint/config/config.php");

use Cryptography\PasswordManager;
use Models\DatabaseConnection;

if(isset($_SESSION['userConnect']) || isset($_SESSION['adminConnect'])) {
    header("Location: /"); 
}
if(isset($_POST["email-field"]) && isset($_POST["password-field"]))   {
    $connection = new DatabaseConnection();

    $hash = PasswordManager::hash($_POST['password-field']);

    // Verify if it's a user
    $stmt = $connection->getConnection()->prepare('SELECT * FROM user WHERE emailUser=? AND passwordUser=?');
    $stmt->execute([$_POST['email-field'], $hash]);
    $user = $stmt->fetch();

    // Verify if it's an admin
    $stmt = $connection->getConnection()->prepare('SELECT * FROM admin WHERE loginAdmin=? AND passwordAdmin=?');
    $stmt->execute([$_POST['email-field'], $hash]);
    $admin = $stmt->fetch();
    
    if(isset($user['idUser'])) {
        $_SESSION['userConnect'] = $user['idUser']; 
        // header('Location: /dashboard/user/');
        header('Location: /sign-in');
    }
    else if(isset($admin['idAdmin'])) {
        $_SESSION['adminConnect'] = $admin['idAdmin'];
        // header('Location: /dashboard/admin/');
        header('Location: /sign-in');
    }
    // User not in the database
    else{
        $_SESSION["error"] = "Email ou mot de passe incorrect";

        // Sends back the form's data to refill the form
        $_SESSION['form-data'] = $_POST;
        header('Location: /sign-in');
    }
}   