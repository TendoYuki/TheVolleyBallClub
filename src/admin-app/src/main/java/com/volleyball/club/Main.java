package com.volleyball.club;
import java.sql.*; 

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","root");  
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}