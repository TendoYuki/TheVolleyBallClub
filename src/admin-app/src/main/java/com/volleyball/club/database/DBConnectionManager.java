package com.volleyball.club.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class DBConnectionManager {
    private static final String DB_NAME = "volleyball_club";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Pabloescobar";
    private static Connection con = getConnection();
    

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

    public static ResultSet execQuery(String query){
        ResultSet res;
        try{
            Statement stmt = con.createStatement();
            res = stmt.executeQuery(query);
            System.out.println("Cococococo");
            return res;
        }catch(Exception e){
            
        }
        return null;
    }
    
}
