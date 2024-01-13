<?php

namespace Controllers;

use Models\Admin;
use Exceptions\DisplayableException;
use Cryptography\PasswordManager;
use Controllers\AbstractController;

class AdminController extends AbstractController{

    public static function new() {
        try {
            AccountController::checkEmailUnicity($_POST['login-field']);
            AccountController::checkPasswordStrength($_POST['password-field']);
        
            // Creates a new admin in the database
            $id = Admin::new(
                $_POST['login-field'],
                PasswordManager::hash($_POST['password-field']),
            );
    
            // Connects the newly created admin
            $_SESSION['adminConnect'] = $id; 
    
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
        Admin::delete($_POST['id-field']);
    }

    public static function update() {
        try {
            $admin = Admin::fetch($_POST['id-field']);
            if(isset($_POST['login-field'])) {
                AccountController::checkEmailUnicity($_POST['login-field']);
                $admin->setLogin($_POST['login-field']);
            }
            if(isset($_POST['password-field'])) {
                AccountController::checkPasswordStrength($_POST['password-field']);
                $admin->setPassword(PasswordManager::hash($_POST['password-field']));
            }
            $admin->updateDatabase();
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
        AdminController::new();
        break;
    case 'delete':
        // Delete only if the admin the deletion is the one connected
        if(!($_SESSION['adminConnect'] == $_POST['id-field']))  {
            header("Location: /"); 
        } AdminController::delete();

        break;
    case 'update':
        // Edit only if the admin requesting the edition is the one connected
        if(!($_SESSION['adminConnect'] == $_POST['id-field']))  {
            header("Location: /"); 
        } AdminController::update();

        break;
}