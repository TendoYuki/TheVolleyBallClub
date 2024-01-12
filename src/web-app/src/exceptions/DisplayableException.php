<?php

abstract class DisplayableException extends Exception {
    private $error_code = 'email-already-exist';
    public function __construct($error_code){
        parent::__construct("");
        $this->error_code = $error_code;
    }

    /**
     * Returns the specified error code to be passed back as session variable
     * to be processed by JS script to dislay feedback to user
     * @return string Error code
     */
    public function getErrorCode() {
        return DisplayableException::$error_code;
    }
}