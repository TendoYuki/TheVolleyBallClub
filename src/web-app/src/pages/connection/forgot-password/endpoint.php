<?php require_once("/srv/http/endpoint/app-config.php") ?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mot de passe oublié</title>
</head>
<body class="preload">
    <?php
        if(isset($_SESSION['userConnect']) || isset($_SESSION['adminConnect'])) {
            header("Location: /"); 
        }
        if(isset($_POST["email-field"]))   {
            require('../db_connect.php');
            // Verify if it's a user
            $stmt = $con->prepare('SELECT * FROM user WHERE emailUser=?');
            $stmt->execute([$_POST['email-field']]);
            $user = $stmt->fetch();
            // Verify if it's an admin
            $stmt = $con->prepare('SELECT * FROM admin WHERE loginAdmin=?');
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
    ?>
</body>
</html>
