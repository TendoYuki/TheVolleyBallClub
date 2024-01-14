<?php

use PHPUnit\Framework\TestCase;


use Exceptions\InvalidDateException;

use Controllers\AbstractController;
use Validation\DateTimeValidator;

final class DateTimeValidatorTest extends TestCase {
    public function testCheckValidDateTime() {
        $this->expectNotToPerformAssertions();
        DateTimeValidator::checkDateTimeValidity("2002-06-15 14:42:00"); 

        //HMMMM bizare
    }
    public function testCheckInvalidDateTime() {
        $this->expectException(InvalidDateException::class);
        DateTimeValidator::checkDateTimeValidity("15-06-2002"); 
    }
}