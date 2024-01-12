<?php
include_once("/srv/http/endpoint/config/config.php");
include_once(CONTROLLERS.'AbstractController.php');
include_once(CONTROLLERS.'AccountController.php');
include_once(EXCEPTIONS.'EmailAlreadyExistsException.php');
include_once(EXCEPTIONS.'InvalidAvatarTypeException.php');
include_once(EXCEPTIONS.'DisplayableException.php');
include_once(MODELS.'database.php');
include_once(MODELS.'User.php');
include_once(CRYPTO);

class UserController extends AbstractController{
    /** Connection to the database */
    private static $connection = new DatabaseConnection();

    public static function new() {
        if(isset($_POST["email-field"]) && isset($_POST["password-field"]))   {
            try {
                AccountController::checkEmailUnicity($_POST['email-field']);
                AccountController::checkEmailFormat($_POST['email-field']);
                AccountController::checkPasswordStrength($_POST['password-field']);
                AccountController::checkValidName($_POST['name-field']);
                AccountController::checkValidSurname($_POST['surname-field']);
                AccountController::checkValidGender($_POST['gender-field']);
                AccountController::checkValidBirthdate($_POST['birthdate-field']);
                AccountController::checkAvatarType($_FILES["avatar-field"]['type']);
                AccountController::checkAvatarSize($_FILES["avatar-field"]['size']);
        
                // Temporary payment id
                // TODO: Replace it with real payment id
                $PAYMENT_ID_TEMP = 1;
         
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
    }
    
    public static function delete() {
    
    }

    public static function edit() {
    
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
    case 'edit':
        // Edit only if the user requesting the edition is the one connected
        // Or if the admin is connected
        if(
            (isset($_SESSION['userConnect']) && $_SESSION['userConnect']==$_POST['id-field']) ||
            (isset($_SESSION['adminConnect']))
        ) UserController::edit();

        break;
}