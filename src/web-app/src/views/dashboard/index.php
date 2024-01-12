<?php
include_once("/srv/http/endpoint/config/config.php");

$request = parse_uri($_SERVER['REQUEST_URI']);
$views = ABSPATH . 'views/';
$controllers = ABSPATH . 'controllers/';

// User dashboard routing
if(isset($_SESSION['userConnect'])) {

    // Routing system
    switch ($request) {
        case '/dashboard':
            require $views . 'dashboard/user/user-dashboard.php';
            define('HAS_LOADED_PAGE', true);
            break;
    }
}

// Admin dashboard routing
else if(isset($_SESSION['adminConnect'])) {

    // Routing system
    switch ($request) {
        case '/dashboard':
            require $views . 'dashboard/admin/members/members.php';
            define('HAS_LOADED_PAGE', true);
            break;

        case '/dashboard/members':
            require $views . 'dashboard/admin/members/members.php';
            define('HAS_LOADED_PAGE', true);
            break;

        case '/dashboard/members/view':
            require $views . 'dashboard/admin/members/view.php';
            define('HAS_LOADED_PAGE', true);
            break;

        case '/dashboard/members/edit':
            require $views . 'dashboard/admin/members/edit.php';
            define('HAS_LOADED_PAGE', true);
            break;
    }
}