<?php require_once("/srv/http/endpoint/app-config.php") ?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="shortcut icon" href="/public/favicon.ico" type="image/x-icon">
</head>
<body> 
    <?php
        if(isset($_SESSION['userConnect']) || isset($_SESSION['adminConnect'])) {
            header("Location: /"); 
        }
        if(isset($_POST["email-field"]) && isset($_POST["password-field"]))   {
            require('../db_connect.php');
            require('exceptions/emailAlreadyExistsException.php');
            $stmt = $con->prepare('SELECT * FROM user WHERE emailUser=?');
            $sel = "jefYY3Hkd73H";
            $hash = hash('sha256',$_POST['password-field'].$sel);
            $stmt->execute([$_POST['email-field']]);
            $user = $stmt->fetch();
            try {
                //Verify if the user already exists
                if(isset($user['idUser'])) throw new EmailAlreadyExistsException();
                $stmt = $con->prepare('SELECT * FROM admin WHERE loginAdmin=?');
                $stmt->execute([$_POST['email-field']]);
                $admin = $stmt->fetch();
                //Verify if the admin already exists
                if(isset($admin['idAdmin'])) throw new EmailAlreadyExistsException();

                // Creates the new user
                $stmt = $con->prepare(
                    'INSERT INTO user (nameUser,surnameUser,passwordUser,emailUser,birthdateUser,registerDate,imageUser,Group_idGroup,Payment_idPayment) VALUES (?,?,?,?,?,?,?,?,?);'
                );
                date_default_timezone_set('Europe/Paris');
                $date = date('Y-m-d');
                $PAYMENT_ID_TEMP = 1;

                $avatar_blob = file_get_contents($_FILES["avatar-field"]['tmp_name']);

                $stmt->bindValue(1, $_POST['name-field']);
                $stmt->bindValue(2, $_POST['surname-field']);
                $stmt->bindValue(3, $hash);
                $stmt->bindValue(4, $_POST['email-field']);
                $stmt->bindValue(5, $_POST['birthdate-field']);
                $stmt->bindValue(6, $date);
                $stmt->bindValue(7, $avatar_blob);
                $stmt->bindValue(8, $_POST['group-field']);
                $stmt->bindValue(9, $PAYMENT_ID_TEMP);
                $stmt->execute();

                // Connects the newly created user
                $stmt = $con->prepare('SELECT * FROM user WHERE emailUser=? AND passwordUser=?');
                $stmt->execute([$_POST['email-field'], $hash]);
                $user = $stmt->fetch();
                $_SESSION['userConnect'] = $user['idUser']; 

                header('Location: /dashboard/user/');
            } catch(EmailAlreadyExistsException $e) {
                header('Location: /connection/sign-up?error=L\'email existe déjà');
            }
        }   
    ?>
</body>
</html>
