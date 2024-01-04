<?php
include_once("/srv/http/endpoint/config/config.php");

if(isset($_SESSION['userConnect']) || isset($_SESSION['adminConnect'])) {
    header("Location: /"); 
}
if(isset($_POST["email-field"]) && isset($_POST["password-field"]))   {
    include_once(MODELS.'database.php');
    include_once(ABSPATH.'exceptions/EmailAlreadyExistsException.php');
    include_once(ABSPATH.'exceptions/InvalidAvatarTypeException.php');
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
            'INSERT INTO user (nameUser,surnameUser,passwordUser,emailUser,birthdateUser,registerDate,imageUser,Group_idGroup,Payment_idPayment, gender) VALUES (?,?,?,?,?,?,?,?,?,?);'
        );
        date_default_timezone_set('Europe/Paris');
        $date = date('Y-m-d');
        $PAYMENT_ID_TEMP = 1;

        $avatar_blob = file_get_contents($_FILES["avatar-field"]['tmp_name']);

        $avatar_size = strlen($avatar_blob) / 1024; // size in KB

        // Starts conversion & compression to JPEG
        $avatar_image = imagecreatefromstring($avatar_blob);
        ob_start();

        // If image larger than 2 MB compress it by 70%
        if($avatar_size >= 2000) {
            imagejpeg($avatar_image,NULL, 30);
        } 
        // If image larger than 1 MB compress it by 50 %
        else if($avatar_size >= 1000) {
            imagejpeg($avatar_image,NULL, 50);
        }
        // If image larger than 500 KB compress it by 20 %
        else if($avatar_size >= 500) {
            imagejpeg($avatar_image,NULL, 80);
        }
        // Else dont compress, just convert to jpeg
        else {
            imagejpeg($avatar_image,NULL, 100);
        }

        // Ends conversion and compression
        $avatar_compressed_blob=ob_get_contents();
        ob_end_clean();

        $stmt->bindValue(1, $_POST['name-field']);
        $stmt->bindValue(2, $_POST['surname-field']);
        $stmt->bindValue(3, $hash);
        $stmt->bindValue(4, $_POST['email-field']);
        $stmt->bindValue(5, $_POST['birthdate-field']);
        $stmt->bindValue(6, $date);
        $stmt->bindValue(7, $avatar_compressed_blob);
        $stmt->bindValue(8, $_POST['group-field']);
        $stmt->bindValue(9, $PAYMENT_ID_TEMP);
        $stmt->bindValue(10, $_POST['gender-field']);
        $stmt->execute();

        // Connects the newly created user
        $stmt = $con->prepare('SELECT * FROM user WHERE emailUser=? AND passwordUser=?');
        $stmt->execute([$_POST['email-field'], $hash]);
        $user = $stmt->fetch();
        $_SESSION['userConnect'] = $user['idUser']; 

        header('Location: /dashboard');
    } catch(EmailAlreadyExistsException $e) {
        $_SESSION["error"] = "L'email existe déjà";

        // Sends back the form's data to refill the form
        $_SESSION['form-data'] = $_POST;
        header('Location: /sign-up');
    }
}   