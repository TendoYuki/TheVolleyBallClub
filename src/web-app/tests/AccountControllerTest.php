<?php
include_once("/srv/http/endpoint/config/config.php");

include_once(CONTROLLERS.'AccountController.php');
include_once(EXCEPTIONS.'EmailAlreadyExistsException.php');
include_once(EXCEPTIONS.'EmailFormatException.php');
include_once(EXCEPTIONS.'InvalidAvatarTypeException.php');
include_once(EXCEPTIONS.'InvalidAvatarSizeException.php');
include_once(EXCEPTIONS.'InvalidBirthdateException.php');
include_once(EXCEPTIONS.'InvalidGenderException.php');
include_once(EXCEPTIONS.'InvalidNameException.php');
include_once(EXCEPTIONS.'InvalidSurnameException.php');
include_once(EXCEPTIONS.'WeakPasswordException.php');

use PHPUnit\Framework\TestCase;

use Controllers\AbstractController;

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