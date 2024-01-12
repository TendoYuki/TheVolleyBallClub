<?php
include_once(EXCEPTIONS.'DisplayableException.php');

class EmailAlreadyExistsException extends DisplayableException {
    public function __construct(){
        parent::__construct("email-already-exist");
    }
}