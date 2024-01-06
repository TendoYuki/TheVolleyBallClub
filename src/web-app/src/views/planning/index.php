<?php
include_once("/srv/http/endpoint/config/config.php");

$request = parse_uri($_SERVER['REQUEST_URI']);
$views = ABSPATH . 'views/';
$controllers = ABSPATH . 'controllers/';

// Routing system
switch ($request) {
    // case '/planning':
    //     require $views . 'planning/planning.php';
    //     define('HAS_LOADED_PAGE', true);
    //     break;

    // case '/planning/view':
    //         require $views . 'planning/view.php';
    //         define('HAS_LOADED_PAGE', true);
    //         break;
    default:
        define('HAS_LOADED_PAGE', true);
        echo $request;
}