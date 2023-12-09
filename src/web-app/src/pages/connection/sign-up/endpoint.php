<?php

    if(isset($_POST["email-field"]) && isset($_POST["password-field"]))   {
        require('../db_connect.php');
        $stmt = $con->prepare('SELECT * FROM user WHERE login=? AND password=?');
        $sel = "jefYY3Hkd73H";
        $hash = hash('sha256',$_POST['password-field'].$sel);
        $stmt->execute([$_POST['email-field'], $hash]);
        foreach($stmt->fetchAll() as $user) {
            if($_POST['email-field'] == $user['emailUser']) throw new EmailAlreadyExistsException();

            if($user->next ==null){
                $lastlicence = $user['licenseNumber'];
            }
        }
        $stmt = $con->prepare(
            'INSERT INTO user (nameUser,surnameUser,passwordUser,emailUser,birthdateUser,registerDate,imageUser,licenseNumber,Group_idGroup) 
            VALUES (?,?,?,?,?,?,?,?,?)'
        );
        if($lastlicence ==null) $licence = 8810023000;
        else $licence = $lastlicence+1;
        $date = date('YYYY-MM-dd');
        $stmt->execute(
            [
                $_POST['name-field'],$_POST['surname-field'],$_POST['password-field'],$_POST['email-field'],$_POST['birthdate-field'],
                $date,$_POST['avatar-field'],$licence,$_POST['group-field']
            ]
        );
        header('Location: '.'/pages/connection/dashboard/user/');
    }   

    /**
     * avatar-field
     * group-field
     * birthdate-field
     * surname-field
     * name-field
     * email-field
     * password-field
     */
?>

