<?php

use PHPUnit\Framework\TestCase;


use Exceptions\InvalidPostCodeException;

use Validation\AddressValidator;

final class AddressValidatorTest extends TestCase {
    public function testCheckValidAvatarSize() {
        $this->expectNotToPerformAssertions();
        AddressValidator::checkPostCodeValidity(88100); 
    }
    public function testCheckInvalidAvatarSize() {
        $this->expectException(InvalidPostCodeException::class);
        AddressValidator::checkPostCodeValidity(88100258); 
    }
}