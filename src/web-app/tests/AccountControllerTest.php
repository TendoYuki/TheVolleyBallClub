<?php

use PHPUnit\Framework\TestCase;

use Exceptions\EmailAlreadyExistsException;
use Exceptions\EmailFormatException;
use Exceptions\InvalidAvatarTypeException;
use Exceptions\InvalidAvatarSizeException;
use Exceptions\InvalidBirthdateException;
use Exceptions\InvalidGenderException;
use Exceptions\InvalidNameException;
use Exceptions\InvalidSurnameException;
use Exceptions\WeakPasswordException;

use Controllers\AbstractController;
use Controllers\AccountController;

final class AccountControllerTest extends TestCase {
    public function testCheckValidAvatarSize() {
        $this->expectNotToPerformAssertions();
        AccountController::checkAvatarSize(1024000); 
    }
    public function testCheckInvalidAvatarSize() {
        $this->expectException(InvalidAvatarSizeException::class);
        AccountController::checkAvatarSize(3000000);
    }
}