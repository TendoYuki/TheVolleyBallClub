<?php

namespace Controllers;

use Database\DatabaseConnection;

function back_redirect() {
    $id = $_POST["id"];
    switch($_POST["type"]) {
        case 'training':
            header("Location: /planning/view/?training_id=$id"); 
            break;
        case 'competition':
            header("Location: /planning/view/?match_id=$id"); 
            break;
    }
}



if(!(isset($_SESSION['userConnect']) || isset($_SESSION['adminConnect']))) {
    header("Location: /"); 
}
else if(!(isset($_POST["type"]) && isset($_POST["id"]))) {
    header("Location: /"); 
}

function handleParticipate() {
    $connection = new DatabaseConnection();

    $stmt = null;

    switch($_POST["type"]) {
        case 'training':
            $stmt = $connection->getConnection()->prepare("SELECT idTraining,
                startDateTimeTraining,
                endDateTimeTraining,
                maxParticipantTraining,
                count(User_idUser) AS `participant_ct`
                FROM training
                LEFT JOIN `user_has_training` ON `user_has_training`.`Training_idTraining`=idTraining
                WHERE idTraining=?;
            ");
            break;
        case 'competition':
            $stmt = $connection->getConnection()->prepare("SELECT idCompetition,
                startDateTimeCompetition,
                endDateTimeCompetition,
                maxParticipantCompetition,
                count(User_idUser) AS `participant_ct`
                FROM competition
                LEFT JOIN `user_has_competition` ON `user_has_competition`.`Competition_idCompetition`=idCompetition
                WHERE idCompetition=?
            ");
            break;
    }

    $stmt->bindValue(1, $_POST["id"]);
    $stmt->execute();

    $res = $stmt->fetch();

    $formatter = new \IntlDateFormatter(
        'fr_FR',
        \IntlDateFormatter::LONG,
        \IntlDateFormatter::NONE,
        'Europe/Paris'
    );

    $s_time = null;
    $e_time = null;
    $remaining_places = null;

    switch($_POST["type"]) {
        case 'training':
            $s_time = strtotime($res["startDateTimeTraining"]);
            $e_time = strtotime($res["endDateTimeTraining"]);
            $remaining_places = $res["maxParticipantTraining"]-$res["participant_ct"];
            break;
        case 'competition':
            $s_time = strtotime($res["startDateTimeCompetition"]);
            $e_time = strtotime($res["endDateTimeCompetition"]);
            $remaining_places = $res["maxParticipantCompetition"]-$res["participant_ct"];
            break;
    }

    // Formats the competition date to dd/MM/yyyy
    $date_str = $formatter->format($s_time);

    // If the training/competition aready ended, redirect
    if($s_time < time()) {
        back_redirect();
        return;
    }

    switch($_POST["action"]) {
        case 'stop_participate':
            switch($_POST["type"]) {
                case 'training':
                    $stmt = $connection->getConnection()->prepare("SELECT User_idUser, Training_idTraining
                        FROM user_has_training
                        WHERE Training_idTraining=? AND User_idUser=?
                    ");

                    $stmt->bindValue(1, $_POST["id"]);
                    $stmt->bindValue(2, $_SESSION['userConnect']);
                    $stmt->execute();

                    $res1 = $stmt->fetch();
                    if(!isset($res1["User_idUser"])) {
                        back_redirect();
                        return;
                    }

                    $stmt = $connection->getConnection()->prepare("DELETE FROM user_has_training WHERE User_idUser=? AND Training_idTraining=?");
                    $stmt->bindValue(1, $_SESSION['userConnect']);
                    $stmt->bindValue(2, $_POST["id"]);
                    $stmt->execute();
                    back_redirect(); 
                    return;
                case 'competition':
                    $stmt = $connection->getConnection()->prepare("SELECT User_idUser, Competition_idCompetition
                        FROM user_has_competition
                        WHERE Competition_idCompetition=? AND User_idUser=?
                    ");

                    $stmt->bindValue(1, $_POST["id"]);
                    $stmt->bindValue(2, $_SESSION['userConnect']);
                    $stmt->execute();

                    $res1 = $stmt->fetch();
                    $can_unsubscribe = false; 
                    if(isset($res1["User_idUser"])) {
                        $can_unsubscribe = true;
                    } else {
                        back_redirect();
                        return;
                    }

                    $stmt = $connection->getConnection()->prepare("DELETE FROM user_has_competition WHERE User_idUser=? AND Competition_idCompetition=?");
                    $stmt->bindValue(1, $_SESSION['userConnect']);
                    $stmt->bindValue(2, $_POST["id"]);
                    $stmt->execute();
                    back_redirect(); 
                    return;
            }   
            break;
        case 'participate':
            if($remaining_places <= 0) {
                back_redirect();
                return;
            }
            switch($_POST["type"]) {
                case 'training':
                    $stmt = $connection->getConnection()->prepare("INSERT INTO user_has_training (User_idUser, Training_idTraining) VALUES (?,?)");
                    $stmt->bindValue(1, $_SESSION['userConnect']);
                    $stmt->bindValue(2, $_POST["id"]);
                    $stmt->execute();
                    back_redirect(); 
                    return;
                case 'competition':
                    $stmt = $connection->getConnection()->prepare("INSERT INTO user_has_competition (User_idUser, Competition_idCompetition) VALUES (?,?)");
                    $stmt->bindValue(1, $_SESSION['userConnect']);
                    $stmt->bindValue(2, $_POST["id"]);
                    $stmt->execute();
                    back_redirect(); 
                    return;
            } 
                
            break;
    }
}

handleParticipate();