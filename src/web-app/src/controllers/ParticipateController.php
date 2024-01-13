<?php

namespace Controllers;

use Models\Competition;
use Models\Training;

class ParticipationController {
    public static function ParticipateToTraining($user_id, $training_id): bool {
        $training = Training::fetch($training_id);
        return $training->addParticipant($user_id);
    }
    public static function WithdrawFromTraining($user_id, $training_id): bool {
        $training = Training::fetch($training_id);
        return $training->removeParticipant($user_id);
    }
    public static function ParticipateToCompetition($user_id, $competition_id): bool {
        $competition = Competition::fetch($competition_id);
        return $competition->addParticipant($user_id);
    }
    public static function WithdrawFromCompetition($user_id, $competition_id): bool {
        $competition = Competition::fetch($competition_id);
        return $competition->removeParticipant($user_id);
    }
    public static function handleRequest() {
        switch($_POST["type"]) {
            case 'training':
                switch($_POST["action"]) {
                    case 'stop_participate':
                        return ParticipationController::WithdrawFromTraining($_SESSION['userConnect'], $_POST["id"]);
                    case 'participate':
                        return ParticipationController::ParticipateToTraining($_SESSION['userConnect'], $_POST["id"]);
                }
                break;
            case 'competition':
                switch($_POST["action"]) {
                    case 'stop_participate':
                        return ParticipationController::WithdrawFromCompetition($_SESSION['userConnect'], $_POST["id"]);
                    case 'participate':
                        return ParticipationController::ParticipateToCompetition($_SESSION['userConnect'], $_POST["id"]);
                }
                break;
        }
    } 
    public static function redirect() {
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
}

ParticipationController::handleRequest();
ParticipationController::redirect();