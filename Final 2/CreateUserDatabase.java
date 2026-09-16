/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author mered
 */
public class CreateUserDatabase {
    
    private static final String locationDB = "jdbc:derby://localhost:1527/FantasticDatabase";
    private static final String USER = "FantasticDatabase";
    private static final String PASS = "FantasticDatabase";
    
    public static void main(String[] args)
    {
        try (Connection conn = DriverManager.getConnection(locationDB, USER, PASS); Statement statement = conn.createStatement())
        {
            String createUserTable = "CREATE TABLE USERPASSWORD ("
                    + "USERNAME VARCHAR(30) NOT NULL, "
                    + "PASSWORD VARCHAR(30) NOT NULL, "
                    + "PRIMARY KEY (USERNAME))";
            
            statement.execute(createUserTable);
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
}
