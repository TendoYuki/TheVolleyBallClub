<?php
include_once("/srv/http/endpoint/config/config.php");

$request = trim(strtok($_SERVER['REQUEST_URI'], '?'));
$views = ABSPATH . 'views/';
$controllers = ABSPATH . 'controllers/';

include_once( $views . 'dashboard/index.php');

// Routing system

switch ($request) {
    case '':
    case '/':
        require $views . 'home.php';
        define('HAS_LOADED_PAGE', true);
        break;

    case '/reset-password':
        require $views . 'connection/reset-password.php';
        define('HAS_LOADED_PAGE', true);
        break;

    case '/sign-in':
        require $views . 'connection/sign-in.php';
        define('HAS_LOADED_PAGE', true);
        break;

    case '/sign-in/submit':
        require $controllers . 'SignInController.php';
        define('HAS_LOADED_PAGE', true);
        break;

    case '/sign-out':
        require $controllers . 'SignOutController.php';
        define('HAS_LOADED_PAGE', true);
        break;

    case '/sign-up':
        require $views . 'connection/sign-up.php';
        define('HAS_LOADED_PAGE', true);
        break;

    case '/sign-up/submit':
        require $controllers . 'SignUpController.php';
        define('HAS_LOADED_PAGE', true);
        break;

    case '/contact':
        require $views . 'contact.php';
        define('HAS_LOADED_PAGE', true);
        break;

    case '/info':
        require $views . 'informations.php';
        define('HAS_LOADED_PAGE', true);
        break;

    case '/planning':
        require $views . 'planning.php';
        define('HAS_LOADED_PAGE', true);
        break;

    default:
        if(!defined('HAS_LOADED_PAGE'))  {
            http_response_code(404);
            require $views . 'error-pages/404.php';
        }
}
