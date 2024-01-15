<?php

use PHPUnit\Framework\TestCase;


use Exceptions\InvalidPostCodeException;

use Validation\AddressValidator;

final class AddressValidatorTest extends TestCase {
    public function testCheckValidPostCode() {
        $this->expectNotToPerformAssertions();
        AddressValidator::checkPostCodeValidity(88100); 
    }
    public function testCheckInvalidPostCode() {
        $this->expectException(InvalidPostCodeException::class);
        AddressValidator::checkPostCodeValidity(88100258); 
    }
}