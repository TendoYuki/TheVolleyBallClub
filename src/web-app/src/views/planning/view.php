<?php
    if(isset($_GET["match_id"])) {
        include_once("match.php");
    } 
    else if (isset($_GET["training_id"])) {
        include_once("training.php");
    }
    else {
        header("Location: /planning");
    }
    