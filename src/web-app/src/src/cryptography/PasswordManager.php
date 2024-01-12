<?php

class PasswordManager {
    private static $salt = "jefYY3Hkd73H";

    /**
     * Hashes the given password with salted sha256 encryption
     * @param string $password Password to hash
     * @return string Hashed password
     */
    public static function hash($password) {
        return hash('sha256',$password.PasswordManager::$salt);
    }
}