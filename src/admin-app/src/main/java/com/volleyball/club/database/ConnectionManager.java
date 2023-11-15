package com.volleyball.club.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    private static final String DB_NAME = "volleyball_club";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Pabloescobar";

    public static Connection getConnection() {
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB_NAME,USERNAME,PASSWORD);  
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    
}
