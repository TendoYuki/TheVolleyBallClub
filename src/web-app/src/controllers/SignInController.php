<?php

namespace Controllers;

use Cryptography\PasswordManager;
use Authorization\Authenticator;
use Authorization\AuthorizationLevel;

switch(Authenticator::authenticate(
        $_POST['email-field'],
        PasswordManager::hash($_POST['password-field']))
    ) {
    case AuthorizationLevel::User: 
    case AuthorizationLevel::Admin: 
        header('Location: /');
        break;
    case AuthorizationLevel::Guest:
        // Sets the error message
        $_SESSION["error"] = "Email ou mot de passe incorrect";
        // Sends back the form's data to refill the form
        $_SESSION['form-data'] = $_POST;
        header('Location: /sign-in'); 
        break;
}