<?php

namespace Exceptions;

class InvalidAvatarTypeException extends DisplayableException {
    public function __construct(){
        parent::__construct("avatar-type-invalid");
    }
}