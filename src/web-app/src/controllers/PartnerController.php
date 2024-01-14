<?php

namespace Controllers;

use Controllers\AbstractController;
use Exceptions\DisplayableException;
use Models\Partner;
use Validation\ImageValidator;

class PartnerController extends AbstractController implements IRequestHandler{
    protected static function validate() {
        ImageValidator::checkImageType($_FILES["logo-partner-field"]['type']);
    }

    public static function new() {
        try {
            PartnerController::validate();

            Partner::new(
                $_POST["name-field"],
                file_get_contents($_FILES["logo-partner-field"]["tmp_name"]),
                $_POST["website-field"]
            );
        } catch(DisplayableException $e) {
            $_SESSION["error"] = $e->getErrorCode();
    
            // Sends back the form's data to refill the form
            $_SESSION['form-data'] = $_POST;
        }
    }
    
    public static function delete() {
        Partner::delete($_POST['id-field']);
    }

    public static function update() {
        try {
            PartnerController::validate();

            Partner::fetch($_POST["id-field"])
                ->setName($_POST["name-field"])
                ->setWebpage($_POST["website-field"])
                ->setLogo(file_get_contents($_FILES["logo-partner-field"]["tmp_name"]))
                ->updateDatabase();
        } catch(DisplayableException $e) {
            $_SESSION["error"] = $e->getErrorCode();
    
            // Sends back the form's data to refill the form
            $_SESSION['form-data'] = $_POST;
        }
    }

    public static function handleRequest(): void {
        switch($_POST["action"]) {
            case 'create':
                PartnerController::new();
                break;
            case 'delete':
                PartnerController::delete();
                break;
            case 'update':
                PartnerController::update();
                break;
        }
    }
    public static function redirect() {
    }
}

PartnerController::handleRequest();
PartnerController::redirect();

/**
 * Fields -> [
 *  "name" : string
 *  "logo-partner" : image
 *  "webpage" : string
 * ]
 */