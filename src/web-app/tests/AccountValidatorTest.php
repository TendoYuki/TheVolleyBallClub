<?php

use PHPUnit\Framework\TestCase;

use Exceptions\EmailAlreadyExistsException;
use Exceptions\EmailFormatException;
use Exceptions\InvalidImageTypeException;
use Exceptions\InvalidAvatarSizeException;
use Exceptions\InvalidBirthdateException;
use Exceptions\InvalidGenderException;
use Exceptions\InvalidGroupException;
use Exceptions\InvalidNameException;
use Exceptions\InvalidSurnameException;
use Exceptions\WeakPasswordException;

use PharIo\Manifest\InvalidEmailException;
use Validation\AccountValidator;

final class AccountValidatorTest extends TestCase {
    public function testCheckValidAvatarSize() {
        $this->expectNotToPerformAssertions();
        AccountValidator::checkAvatarSize(1024000); 
    }
    public function testCheckInvalidAvatarSize() {
        $this->expectException(InvalidAvatarSizeException::class);
        AccountValidator::checkAvatarSize(3000000);
    }
    public function testCheckEmailFormat(){
        $this->expectNotToPerformAssertions();
        AccountValidator::checkEmailFormat("test@test.com");
    }
    public function testCheckEmailInvalidFormat(){
        $this->expectException(EmailFormatException::class);
        AccountValidator::checkEmailFormat("test@test");
    }
    public function testHasTrue(){
        $this->assertTrue(AccountValidator::has("Hello","ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    }
    public function testHasFalse(){
        $this->assertFalse(AccountValidator::has("Hello","1234567890"));
    }
    public function testCheckPasswordStrengthValid(){
        $this->expectNotToPerformAssertions();
        AccountValidator::checkPasswordStrength("azertyuiop!A1");
    }
    public function testCheckPasswordStrengthInvalid(){
        $this->expectException(WeakPasswordException::class);
        AccountValidator::checkPasswordStrength("azerty");
    }
    public function testCheckValidGender(){
        $this->expectNotToPerformAssertions();
        AccountValidator::checkValidGender(1);
    }
    public function testCheckInvalidGender(){
        $this->expectException(InvalidGenderException::class);
        AccountValidator::checkValidGender(-1);
    }
    public function testCheckValidBirthdate(){
        $this->expectNotToPerformAssertions();
        AccountValidator::checkValidBirthdate("2002-06-15");
    }
    public function testCheckInvalidBirthdate(){
        $this->expectException(InvalidBirthdateException::class);
        AccountValidator::checkValidBirthdate("2010-06-15");
    }
    public function testCheckValidName(){
        $this->expectNotToPerformAssertions();
        AccountValidator::checkValidName("Yui");
    }
    public function testCheckInvalidName(){
        $this->expectException(InvalidNameException::class);
        AccountValidator::checkValidName("Yui1245");
    }
    public function testCheckValidSurname(){
        $this->expectNotToPerformAssertions();
        AccountValidator::checkValidSurname("Yui");
    }
    public function testCheckInvalidSurname(){
        $this->expectException(InvalidSurnameException::class);
        AccountValidator::checkValidSurname("Yui&@");
    }
    public function testCheckValidGroup(){
        $this->expectNotToPerformAssertions();
        AccountValidator::checkValidGroup(1);
    }
    public function testCheckInvalidGroup(){
        $this->expectException(InvalidGroupException::class);
        AccountValidator::checkValidGroup(-1);
    }
}