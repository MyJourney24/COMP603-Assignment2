/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author mered
 */
public class AddUserData {
    
    private static final String locationDB = "jdbc:derby://localhost:1527/FantasticDatabase";
    private static final String USER = "APP";
    private static final String PASS = "APP";
    
    public static void main(String[] args)
    {
        String insertUser = "INSERT INTO USERPASSWORD (USERNAME, PASSWORD) VALUES (?,?)";
        
        try (Connection conn = DriverManager.getConnection(locationDB, USER, PASS); 
                PreparedStatement statement = conn.prepareStatement(insertUser))
        {
            statement.setString(1, "test1");
            statement.setString(2, "password1");
            statement.addBatch();
            
            statement.setString(1, "test2");
            statement.setString(2, "password2");
            statement.addBatch();
            
            statement.setString(1, "test3");
            statement.setString(2, "password3");
            statement.addBatch();
            
            statement.setString(1, "test4");
            statement.setString(2, "password4");
            statement.addBatch();
            
            statement.executeBatch();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
}
