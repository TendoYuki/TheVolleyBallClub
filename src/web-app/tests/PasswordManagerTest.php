<?php

use PHPUnit\Framework\TestCase;

use Cryptography\PasswordManager;

final class PasswordManagerTest extends TestCase {
    public function testPasswordManager() {
        $this->assertEquals(PasswordManager::hash("test"),hash('sha256',"test".PasswordManager::getSalt()));
    }
}