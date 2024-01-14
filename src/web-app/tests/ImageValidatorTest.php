<?php

use PHPUnit\Framework\TestCase;


use Exceptions\InvalidImageTypeException;

use Controllers\AbstractController;
use Validation\ImageValidator;

final class ImageValidatorTest extends TestCase {
    public function testCheckValidImageType() {
        $this->expectNotToPerformAssertions();
        ImageValidator::checkImageType("image/jpeg"); 
    }
    public function testCheckInvalidImageType() {
        $this->expectException(InvalidImageTypeException::class);
        ImageValidator::checkImageType("document/pdf"); 
    }
}