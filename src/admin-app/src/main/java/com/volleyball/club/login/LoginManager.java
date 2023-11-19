package com.volleyball.club.login;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.login.exceptions.IncorrectLoginException;
import com.volleyball.club.login.exceptions.IncorrectPasswordException;
import com.volleyball.club.observation.Observable;

public class LoginManager extends Observable{
    private boolean isConnected = false;
    private static LoginManager instance = new LoginManager();
    private static final String PASSWORD_SALT = "jefYY3Hkd73H";

    private LoginManager() {
        
    }

    public static LoginManager getInstance() {
        return instance;
    }

    /**
     * Hashes a password with the sha256 algorithm
     * @param password Password to hash 
     * @return Hashed password
     */
    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest((password + PASSWORD_SALT).getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) 
                hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    /**
     * Logs an admin to the app
     * @param login Login of the admin
     * @param password Password of the admin
     * @throws IncorrectLoginException
     * @throws IncorrectPasswordException
     */
    public void authentify(String login, String password) throws IncorrectLoginException, IncorrectPasswordException{
        String query = "SELECT * FROM admin;";
        Connection con = DBConnectionManager.getConnection();
        String hashedPassword = hashPassword(password);
        try{
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs= stmt.executeQuery();
            boolean loginCorrect = false;
            while(rs.next() && !loginCorrect){
                String currLogin = rs.getString("loginAdmin");
                String currPassword = rs.getString("passwordAdmin");
                if(currLogin.equals(login)){
                    loginCorrect = true;
                    if(!currPassword.equals(hashedPassword)){
                        throw new IncorrectPasswordException();
                    }
                }
            }
            if(!loginCorrect) throw new IncorrectLoginException();
            isConnected = true;
            updateObservers();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Deauthentifies the current admin if logged in
     */
    public void deauthentify() {
        isConnected = false;
        updateObservers();
    }

    /**
     * 
     * @return True if an admin is logged in
     */
    public boolean isConnected() {
        return isConnected;
    }
}
