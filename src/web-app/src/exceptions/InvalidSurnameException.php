<?php
include_once(EXCEPTIONS.'DisplayableException.php');

class InvalidSurnameException extends DisplayableException {
    public function __construct(){
        parent::__construct("surname-invalid");
    }
}