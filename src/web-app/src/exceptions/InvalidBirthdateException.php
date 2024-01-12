<?php
include_once(EXCEPTIONS.'DisplayableException.php');

class InvalidBirthdateException extends DisplayableException {
    public function __construct(){
        parent::__construct("birthdate-invalid");
    }
}