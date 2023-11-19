package com.volleyball.club.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class that manages the connection to the database
 */
public class DBConnectionManager {
    /** Name of the database */
    private static final String DB_NAME = "volleyball_club";
    /** Username of a user having admin privilege */
    private static final String USERNAME = "root";
    /** Password of a user having admib privilege */
    private static final String PASSWORD = "Pabloescobar";
    /** Connection static instance */
    private static Connection con = getConnection();
    
    /**
     * Returns the connection to the database
     * @return Connection to the database
     */
    public static Connection getConnection() {
        if(con != null) return con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB_NAME,USERNAME,PASSWORD);  
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }  
}
