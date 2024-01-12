<?php
include_once(EXCEPTIONS.'DisplayableException.php');

class InvalidGenderException extends DisplayableException {
    public function __construct(){
        parent::__construct("gender-invalid");
    }
}