<?php

use PHPUnit\Framework\TestCase;


use Exceptions\InvalidDocumentTypeException;

use Validation\DocumentValidator;

final class DocumentValidatorTest extends TestCase {
    public function testCheckValidDocumentType() {
        $this->expectNotToPerformAssertions();
        DocumentValidator::checkDocumentType("application/pdf"); 
    }
    public function testCheckInvalidDocumentType() {
        $this->expectException(InvalidDocumentTypeException::class);
        DocumentValidator::checkDocumentType("document/pdf"); 
    }
}