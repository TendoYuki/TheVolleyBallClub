<?php
include_once(EXCEPTIONS.'DisplayableException.php');

class InvalidAvatarSizeException extends DisplayableException {
    public function __construct(){
        parent::__construct("avatar-size-invalid");
    }
}