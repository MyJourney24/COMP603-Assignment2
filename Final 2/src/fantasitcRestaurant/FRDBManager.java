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
public final class FRDBManager {
    
    private static final String locationDB = "jdbc:derby://localhost:1527/FantasticDatabase";
    private static final String USER = "app";
    private static final String PASS = "app";
    
    Connection conn;
    public FRDBManager()
    {
        establishConnection();
        connectFantasticRestaurantDB();
    }
    
    public Connection getConnection() {
        try {
            if (this.conn == null || this.conn.isClosed()) {
                establishConnection();
                System.out.println("established");
            }
        } 
        catch (Exception e) {
                System.out.println(e);
        }
        
        System.out.println("connection exist already");
        return this.conn;
    }

    //Establish connection
    public void establishConnection() {
        //Establish a connection to Database
        try
        {
            conn = DriverManager.getConnection(locationDB, USER, PASS);
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        
    }
    
    public void closeConnection() {
    try 
    {
        if (this.conn != null && !this.conn.isClosed()) {
            this.conn.close();
            System.out.println("Database connection closed successfully.");
        }
    } 
    catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public Statement statement;
    
    
    public void connectFantasticRestaurantDB() {
        //use the conn, initialize database by creating BOOK Table and insert records
        try
        {
            statement = conn.createStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
//    public static void main(String[] args)
//    {
//        FRDBManager frdbManager = new FRDBManager();
//        frdbManager.establishConnection();
//        frdbManager.connectFantasticRestaurantDB();
//        // 1. First call: Connection is null, so it creates a new one
//        
//        
//        
//        
//        try
//        {
//            String getData = "SELECT USERNAME, PASSWORD FROM USERPASSWORD";
//            ResultSet rs = frdbManager.statement.executeQuery(getData); 
//            while (rs.next())
//            {
//                String presentUsername = rs.getString("USERNAME");
//                String presentPassword = rs.getString("PASSWORD");
//                System.out.print(presentUsername + " " + presentPassword + "\n");
//            }
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
        
//        //creates userpassword table
//        try (Connection conn = DriverManager.getConnection(locationDB, USER, PASS); Statement statement = conn.createStatement())
//        {
//            String createUserTable = "CREATE TABLE USERPASSWORD ("
//                    + "USERNAME VARCHAR(30) NOT NULL, "
//                    + "PASSWORD VARCHAR(30) NOT NULL, "
//                    + "PRIMARY KEY (USERNAME))";
//            
//            statement.execute(createUserTable);
//            
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
//        
//        //creates booking table
//        try (Connection conn = DriverManager.getConnection(locationDB, USER, PASS); Statement statement = conn.createStatement())
//        {
//            String createUserTable = "CREATE TABLE USERBOOKING ("
//                    + "BOOKINGID INT NOT NULL, "
//                    + "USERNAME VARCHAR(30) NOT NULL, "
//                    + "DAYOFWEEK INT NOT NULL, "
//                    + "TIMEOFDAY INT NOT NULL, "
//                    + "PEOPLE INT NOT NULL, "
//                    + "DURATION INT NOT NULL, "
//                    + "PRIMARY KEY (BOOKINGID), "
//                    + "FOREIGN KEY (USERNAME) REFERENCES APP.USERPASSWORD(USERNAME))";
//            
//            statement.execute(createUserTable);
//            
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
//        
//        //create order table
//        try (Connection conn = DriverManager.getConnection(locationDB, USER, PASS); Statement statement = conn.createStatement())
//        {
//            String createUserTable = "CREATE TABLE USERORDER ("
//                    + "BOOKINGID INT NOT NULL, "
//                    + "DISH VARCHAR(30) NOT NULL, "
//                    + "SERVINGS INT NOT NULL, "
//                    + "PRIMARY KEY (BOOKINGID), "
//                    + "FOREIGN KEY (BOOKINGID) REFERENCES APP.USERBOOKING(BOOKINGID))";
//            
//            statement.execute(createUserTable);
//            
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
        
    }
    
//}
