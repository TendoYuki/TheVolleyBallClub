<?php
include_once(EXCEPTIONS.'DisplayableException.php');

class InvalidNameException extends DisplayableException {
    public function __construct(){
        parent::__construct("name-invalid");
    }
}