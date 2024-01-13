<?php

namespace Controllers;

use Models\User;
use Exceptions\DisplayableException;
use Cryptography\PasswordManager;
use Controllers\AbstractController;

class UserController extends AbstractController{

    public static function new() {
        try {
            AccountController::checkEmailUnicity($_POST['email-field']);
            AccountController::checkEmailFormat($_POST['email-field']);
            AccountController::checkPasswordStrength($_POST['password-field']);
            AccountController::checkValidName($_POST['name-field']);
            AccountController::checkValidSurname($_POST['surname-field']);
            AccountController::checkValidGender($_POST['gender-field']);
            AccountController::checkValidGroup($_POST['group-field']);
            AccountController::checkValidBirthdate($_POST['birthdate-field']);
            AccountController::checkAvatarType($_FILES["avatar-field"]['type']);
            AccountController::checkAvatarSize($_FILES["avatar-field"]['size']);
    
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

    public static function update() {
        try {
            $user = new User($_POST['id-field']);
            if(isset($_POST['email-field'])) {
                AccountController::checkEmailUnicity($_POST['email-field']);
                AccountController::checkEmailFormat($_POST['email-field']);
                $user->setEmail($_POST['email-field']);
            }
            if(isset($_POST['password-field'])) {
                AccountController::checkPasswordStrength($_POST['password-field']);
                $user->setPassword(PasswordManager::hash($_POST['password-field']));
            }
            if(isset($_POST['name-field'])) {
                AccountController::checkValidName($_POST['name-field']);
                $user->setName($_POST['name-field']);
            }
            if(isset($_POST['surname-field'])) {
                AccountController::checkValidSurname($_POST['surname-field']);
                $user->setSurname($_POST['surname-field']);
            }
            if(isset($_POST['gender-field'])) {
                AccountController::checkValidGender($_POST['gender-field']);
                $user->setGender($_POST['gender-field']);
            }
            if(isset($_POST['group-field'])) {
                AccountController::checkValidGroup($_POST['group-field']);
                $user->setGroupID($_POST['group-field']);
            }
            if(isset($_POST['birthdate-field'])) {
                AccountController::checkValidBirthdate($_POST['birthdate-field']);
                $user->setBirthdate($_POST['birthdate-field']);
            }
            if(isset($_FILES["avatar-field"])) {
                AccountController::checkAvatarType($_FILES["avatar-field"]['type']);
                AccountController::checkAvatarSize($_FILES["avatar-field"]['size']);
                $user->setImageUser(file_get_contents($_FILES["avatar-field"]['tmp_name']));
            }
            $user->updateDatabase();
        } catch(DisplayableException $e) {
            $_SESSION["error"] = $e->getErrorCode();
    
            // Sends back the form's data to refill the form
            $_SESSION['form-data'] = $_POST;

            // TODO : Change this location to point to admin edit page
            header('Location: /sign-up');
        }
    
    }
}

switch($_POST["action"]) {
    case 'create':
        // If an admin or a user is connected then redirect 
        // Else start creation process 
        if(isset($_SESSION['userConnect']) || isset($_SESSION['adminConnect'])) {
            header("Location: /"); 
        } else UserController::new();

        break;
    case 'delete':
        // Delete only if the user requesting the deletion is the one connected
        // Or if the admin is connected
        if(
            (isset($_SESSION['userConnect']) && $_SESSION['userConnect']==$_POST['id-field']) ||
            (isset($_SESSION['adminConnect']))
        ) UserController::delete();

        break;
    case 'update':
        // Edit only if the user requesting the edition is the one connected
        // Or if the admin is connected
        if(
            (isset($_SESSION['userConnect']) && $_SESSION['userConnect']==$_POST['id-field']) ||
            (isset($_SESSION['adminConnect']))
        ) UserController::update();

        break;
}