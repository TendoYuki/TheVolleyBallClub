<?php
include_once("/srv/http/endpoint/config/config.php");
include_once(CRYPTO);

if(isset($_SESSION['userConnect']) || isset($_SESSION['adminConnect'])) {
    header("Location: /"); 
}
if(isset($_POST["email-field"]) && isset($_POST["password-field"]))   {
    include_once(MODELS.'database.php');

    $hash = PasswordManager::hash($_POST['password-field']);

    // Verify if it's a user
    $stmt = $con->prepare('SELECT * FROM user WHERE emailUser=? AND passwordUser=?');
    $stmt->execute([$_POST['email-field'], $hash]);
    $user = $stmt->fetch();

    // Verify if it's an admin
    $stmt = $con->prepare('SELECT * FROM admin WHERE loginAdmin=? AND passwordAdmin=?');
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