<?php

namespace Controllers;

use Models\User;
use Exceptions\DisplayableException;
use Cryptography\PasswordManager;
use Controllers\AbstractController;
use Models\IdCard;
use Validation\AccountValidator;

class UserController extends AbstractController implements IRequestHandler{
    protected static function validate() {
        AccountValidator::checkEmailUnicity($_POST['email-field']);
        AccountValidator::checkEmailFormat($_POST['email-field']);
        AccountValidator::checkPasswordStrength($_POST['password-field']);
        AccountValidator::checkValidName($_POST['name-field']);
        AccountValidator::checkValidSurname($_POST['surname-field']);
        AccountValidator::checkValidGender($_POST['gender-field']);
        AccountValidator::checkValidGroup($_POST['group-field']);
        AccountValidator::checkValidBirthdate($_POST['birthdate-field']);
        AccountValidator::checkAvatarType($_FILES["avatar-field"]['type']);
        AccountValidator::checkAvatarSize($_FILES["avatar-field"]['size']);
    }

    public static function new() {
        try {
            UserController::validate();
    
            // Temporary payment id
            // TODO: Replace it with real payment id
            $PAYMENT_ID_TEMP = 1;
        
            // Creates a new user in the database
            $id = User::new(
                $_POST['name-field'],
                $_POST['surname-field'],
                PasswordManager::hash($_POST['password-field']),
                $_POST['email-field'],
                $_POST['birthdate-field'],
                $_POST['gender-field'],
                file_get_contents($_FILES["avatar-field"]['tmp_name']),
                $_POST['group-field'],
                $PAYMENT_ID_TEMP
            );
    
            // Connects the newly created user
            $_SESSION['userConnect'] = $id; 
    
            // Redirects to the dashboard
            header('Location: /dashboard');
        } catch(DisplayableException $e) {
            $_SESSION["error"] = $e->getErrorCode();
    
            // Sends back the form's data to refill the form
            $_SESSION['form-data'] = $_POST;
            header('Location: /sign-up');
        }
    }
    
    public static function delete() {
        User::delete($_POST['id-field']);
    }

    public static function handleMedicalCertificateUpload() {
        // AccountValidator::checkDocumentType($_FILES["medical-certificate-field"]['type']);
        // AccountValidator::checkDocumentSize($_FILES["medical-certificate-field"]['size']);
        $user = User::fetch($_POST['id-field']);
        if($user->getMedicalCertificateId()) {
            $medical_certificate = IdCard::fetch($user->getMedicalCertificateId());
            if($medical_certificate->getIsValid()) {
                return;
            } else {
                $user->removeMedicalCertificate();
            }
        }

        $user->addMedicalCertificate(
            file_get_contents($_FILES["medical-certificate-field"]['tmp_name'])
        );
    }
    public static function handleIdCardUpload() {
        // AccountValidator::checkDocumentType($_FILES["id-card-field"]['type']);
        // AccountValidator::checkDocumentSize($_FILES["id-card-field"]['size']);
        $user = User::fetch($_POST['id-field']);
        if($user->getIdCardId()) {
            $id_card = IdCard::fetch($user->getIdCardId());
            if($id_card->getIsValid()) {
                return;
            } else {
                $user->removeIdCard();
            }
        }

        $user->addIdCard(
            file_get_contents($_FILES["id-card-field"]['tmp_name'])
        );
    }

    public static function handleMedicalCertificateDeletion() {
        User::fetch($_POST['id-field'])->removeMedicalCertificate();
    }
    public static function handleIdCardDeletion() {
        User::fetch($_POST['id-field'])->removeIdCard();
    }

    public static function handleMedicalCertificateValidation() {
        User::fetch($_POST['id-field'])->setValidationStateMedicalCertificate(
            $_POST['medical-certificate-validation-state-field']
        );
    }
    public static function handleIdCardValidation() {
        User::fetch($_POST['id-field'])->setValidationStateIdCard(
            $_POST['id-card-validation-state-field']
        );
    }

    public static function update() {
        try {
            UserController::validate();

            User::fetch($_POST['id-field'])
                ->setEmail($_POST['email-field'])
                ->setPassword(PasswordManager::hash($_POST['password-field']))
                ->setName($_POST['name-field'])
                ->setSurname($_POST['surname-field'])
                ->setGender($_POST['gender-field'])
                ->setGroupID($_POST['group-field'])
                ->setBirthdate($_POST['birthdate-field'])
                ->setImageUser(file_get_contents($_FILES["avatar-field"]['tmp_name']))
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
                UserController::new();
                break;
            case 'delete':
                // Delete only if the user requesting the deletion is the one connected
                // Or if the admin is connected
                if(
                    ($_SESSION['userConnect']==$_POST['id-field']) ||
                    (isset($_SESSION['adminConnect']))
                ) UserController::delete();
                break;
            case 'update':
                // Edit only if the user requesting the edition is the one connected
                // Or if the admin is connected
                if(
                    ($_SESSION['userConnect']==$_POST['id-field']) ||
                    (isset($_SESSION['adminConnect']))
                ) UserController::update();        
                break;
            case 'upload-medical-certificate':
                // Upload only if the user requesting the edition is the one connected
                if(
                    ($_SESSION['userConnect']==$_POST['id-field'])
                ) UserController::handleMedicalCertificateUpload();     
                break;
            case 'upload-id-card':
                // Upload only if the user requesting the edition is the one connected
                if(
                    ($_SESSION['userConnect']==$_POST['id-field'])
                ) UserController::handleIdCardUpload();     
                break;
            case 'validate-medical-certificate':
                // Validate only if the user requesting the edition is the admin
                if(
                    isset($_SESSION['adminConnect'])
                ) UserController::handleMedicalCertificateValidation();    
                break;
            case 'validate-id-card':
                // Validate only if the user requesting the edition is the admin
                if(
                    isset($_SESSION['adminConnect'])
                ) UserController::handleIdCardValidation();    
                break;
        }
    }
    public static function redirect() {
        if(isset($_SESSION["error"])) {
            // TODO : Change this location to point to admin edit page
            header('Location: /sign-up');
        }
        else header('Location: /');
    }
}

UserController::handleRequest();
UserController::redirect();