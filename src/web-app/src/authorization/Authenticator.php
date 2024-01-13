<?php

namespace Authorization;

use Database\DatabaseConnection;

class Authenticator{
    /**
     * Tries to authenticate as a user
     * @param string $email Email of the account
     * @param string $hash Hash of the account's password
     * @return bool True if logged in, false if credentials incorrect
     */
    private static function tryAuthenticateUser(string $email, string $hash): bool {
        $connection = new DatabaseConnection();
        // Verify if it's a user
        $stmt = $connection->getConnection()->prepare(
            'SELECT * FROM user WHERE emailUser=? AND passwordUser=?'
        );
        $stmt->execute([$email, $hash]);
        $user = $stmt->fetch();
        if(isset($user['idUser'])) {
            $_SESSION['userConnect'] = $user['idUser']; 
            return true;
        }
        return false;
    }

    /**
     * Tries to authenticate as an admin
     * @param string $email Email of the account
     * @param string $hash Hash of the account's password
     * @return bool True if logged in, false if credentials incorrect
     */
    private static function tryAuthenticateAdmin(string $email, string $hash): bool {
        $connection = new DatabaseConnection();
        // Verify if it's an admin
        $stmt = $connection->getConnection()->prepare(
            'SELECT * FROM admin WHERE loginAdmin=? AND passwordAdmin=?'
        );
        $stmt->execute([$email, $hash]);
        $admin = $stmt->fetch();
        if(isset($admin['idAdmin'])) {
            $_SESSION['adminConnect'] = $admin['idAdmin'];
            return true;
        }
        return false;
    }
    
    /**
     * Tries to authenticate as either a user or an admin
     * @param string $email Email of the account
     * @param string $hash Hash of the account's password
     * @return AuthorizationLevel Authorization level of the authentication, Admin if admin account logged, User if user logged, or Guest if no account was found
     */
    public static function authenticate(string $email, string $hash): AuthorizationLevel {
        if(Authenticator::tryAuthenticateUser($email, $hash)) return AuthorizationLevel::User;
        if(Authenticator::tryAuthenticateAdmin($email, $hash)) return AuthorizationLevel::Admin;
        return AuthorizationLevel::Guest;
    }
}