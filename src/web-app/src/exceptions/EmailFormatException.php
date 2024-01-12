<?php
include_once(EXCEPTIONS.'DisplayableException.php');

class EmailFormatException extends DisplayableException {
    public function __construct(){
        parent::__construct("email-format-invalid");
    }
}