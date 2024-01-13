<?php
require './vendor/autoload.php';
include_once("/srv/http/endpoint/config/config.php");

use Routing\MainRouter;

(new MainRouter())->handleRequest(parse_uri($_SERVER['REQUEST_URI']));