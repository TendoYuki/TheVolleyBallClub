<?php
include_once(EXCEPTIONS.'DisplayableException.php');

class InvalidAvatarTypeException extends DisplayableException {
    public function __construct(){
        parent::__construct("avatar-type-invalid");
    }
}