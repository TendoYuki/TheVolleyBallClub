<?php

namespace Controllers;

include_once("/srv/http/endpoint/config/config.php");

session_unset();
header('Location: /');