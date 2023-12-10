<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php
        if(isset($_POST["email-field"]) && isset($_POST["password-field"]))   {
            require('../db_connect.php');
            // Verify if it's a user
            $stmt = $con->prepare('SELECT * FROM user WHERE emailUser=? AND passwordUser=?');
            $sel = "jefYY3Hkd73H";
            $hash = hash('sha256',$_POST['password-field'].$sel);
            $stmt->execute([$_POST['email-field'], $hash]);
            $user = $stmt->fetch();
            // Verify if it's an admin
            $stmt = $con->prepare('SELECT * FROM admin WHERE loginAdmin=? AND passwordAdmin=?');
            $stmt->execute([$_POST['email-field'], $hash]);
            $admin = $stmt->fetch();
            
            if(isset($user['idUser'])) {
                session_start();
                echo("User connected");
                $_SESSION['userConnect'] = $user['idUser']; 
            }
            else if(isset($admin['idAdmin'])) {
                session_start();
                echo("Admin connected");
                $_SESSION['adminConnect'] = $admin['idAdmin']; 
            }
            // User not in the database
            else{
                //TODO User not in the database or wrong password
                echo("Wrong");
            }
        }   
    ?>
</body>
</html>
