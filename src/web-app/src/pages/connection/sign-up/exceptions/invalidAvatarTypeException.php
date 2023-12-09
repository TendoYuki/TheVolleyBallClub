<?php
    class InvalidAvatarTypeException extends Exception {
        public function __construct(){
            parent::__construct("Avatar is not in a valid image format");
        }
    }
?>