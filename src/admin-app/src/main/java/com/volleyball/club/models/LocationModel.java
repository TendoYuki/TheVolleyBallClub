package com.volleyball.club.models;
import com.volleyball.club.database.DBConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** Model representing a single location */
public class LocationModel{
    public static int getLocationIdFromName(String name) {
        try{
            Connection con = DBConnectionManager.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM location WHERE nameLocation=?;");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("idLocation");
        }catch(Exception e){
            System.out.println(e);
        }
        return -1;
    }

    public static String getLocationNameFromId(int id) {
        try{
            Connection con = DBConnectionManager.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM location WHERE idLocation=?;");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getString("nameLocation");
        }catch(Exception e){
            System.out.println(e);
        }
        return "";
    }
}
