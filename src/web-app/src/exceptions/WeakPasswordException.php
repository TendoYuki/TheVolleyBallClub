<?php
include_once(EXCEPTIONS.'DisplayableException.php');

class WeakPasswordException extends DisplayableException {
    public function __construct(){
        parent::__construct("weak-password");
    }
}