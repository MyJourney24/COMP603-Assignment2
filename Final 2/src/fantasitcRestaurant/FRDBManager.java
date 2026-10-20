/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author mered
 */
public final class FRDBManager {
    
    private static final String locationDB = "jdbc:derby:FantasticRestaurantDB_Ebd;create=true";
    private static final String USER = "app";
    private static final String PASS = "app";
    
    private static FRDBManager instance;
    Connection conn;
    
    private FRDBManager()
    {
        connectFantasticRestaurantDB();
        
        if (!checkTableExisting("USERPASSWORD")) {
                insertUserPassword();
            }
        
        if (!checkTableExisting("USERBOOKING")) {
                insertUserBooking();
            }
        
        if (!checkTableExisting("USERORDER")) {
                insertUserOrder();
            }
    }
    
    public static synchronized FRDBManager getInstance() {
        if (instance == null) {
            instance = new FRDBManager();
        }
        return instance;
    }
    
    @Override
    public Object clone() throws
    CloneNotSupportedException {
    throw new CloneNotSupportedException();
    }
    
    public void connectFantasticRestaurantDB() {
        try
        {
            getConnection();
            statement = conn.createStatement();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    public Connection getConnection() {
        try {
            if (this.conn == null || this.conn.isClosed()) {
                establishConnection();
                System.out.println("established");
            }
            else
            {
                return this.conn;
            }
        } 
        catch (Exception e) {
                System.out.println(e);
                return this.conn;
        }
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
    
    
    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {

            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);//types);
            //Statement dropStatement=null;
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + "  is there");
                    flag = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
        }
        return flag;
    }
    
    public void insertUserPassword()
    {
        String createTableUserPassword = "CREATE TABLE USERPASSWORD  (USERNAME  VARCHAR(30) PRIMARY KEY,   PASSWORD   VARCHAR(30) NOT NULL)";
        String insertUser = "INSERT INTO USERPASSWORD (USERNAME, PASSWORD) VALUES (?,?)";
        
        try 
        {
            // create UserPassword table
            statement.execute(createTableUserPassword);
            
            try (PreparedStatement pstmt = conn.prepareStatement(insertUser))
            {
                // adds username and password
                pstmt.setString(1, "test1");
                pstmt.setString(2, "password1");
                pstmt.addBatch();

                pstmt.setString(1, "test2");
                pstmt.setString(2, "password2");
                pstmt.addBatch();

                pstmt.setString(1, "test3");
                pstmt.setString(2, "password3");
                pstmt.addBatch();

                pstmt.setString(1, "test4");
                pstmt.setString(2, "password4");
                pstmt.addBatch();

                pstmt.executeBatch();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void insertUserOrder()
    {
        String insertUser = "INSERT INTO USERORDER (BOOKINGID, DISH, SERVINGS) VALUES (?,?,?)";
        String createTableUserOrder = "CREATE TABLE USERORDER  (BOOKINGID INT NOT NULL, DISH VARCHAR(30) NOT NULL, SERVINGS INT NOT NULL, FOREIGN KEY (BOOKINGID) REFERENCES USERBOOKING(BOOKINGID))";
        
        try 
        {
            statement.execute(createTableUserOrder);
            
            try (PreparedStatement pstmt = conn.prepareStatement(insertUser))
            {
                //301724032	test1	2	13	2	1
                pstmt.setInt(1, 301724032);
                pstmt.setString(2, "Beef Pho");
                pstmt.setInt(3, 2);
                pstmt.addBatch();

                //1447097458	test1	4	13	2	1
                pstmt.setInt(1, 1447097458);
                pstmt.setString(2, "Beef Pho");
                pstmt.setInt(3, 4);
                pstmt.addBatch();

                //77173832	test2	5	15	2	2
                pstmt.setInt(1, 77173832);
                pstmt.setString(2, "Mushroom Risotto");
                pstmt.setInt(3, 5);
                pstmt.addBatch();

                //1676197067	test3	1	15	4	2
                pstmt.setInt(1, 1676197067);
                pstmt.setString(2, "-");
                pstmt.setObject(3, 0);
                pstmt.addBatch();

                pstmt.executeBatch();
            }
        }
        catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }

    
    public void insertUserBooking()
    {
        String insertUser = "INSERT INTO USERBOOKING (BOOKINGID, USERNAME, DAYOFWEEK, TIMEOFDAY, PEOPLE, DURATION) VALUES (?,?,?,?,?,?)";
        String createTableUserBooking = "CREATE TABLE USERBOOKING (BOOKINGID INT PRIMARY KEY, USERNAME VARCHAR(30) NOT NULL, DAYOFWEEK INT NOT NULL, TIMEOFDAY INT NOT NULL, PEOPLE INT NOT NULL, DURATION INT NOT NULL, FOREIGN KEY (USERNAME) REFERENCES USERPASSWORD(USERNAME))";
        
        try 
        {
            statement.execute(createTableUserBooking);
            
            try (PreparedStatement pstmt = conn.prepareStatement(insertUser))
            {
                pstmt.setInt(1, 301724032);
                pstmt.setString(2, "test1");
                pstmt.setInt(3, 2);
                pstmt.setInt(4, 13);
                pstmt.setInt(5, 2);
                pstmt.setInt(6, 1);
                pstmt.addBatch();

                pstmt.setInt(1, 1447097458);
                pstmt.setString(2, "test1");
                pstmt.setInt(3, 4);
                pstmt.setInt(4, 13);
                pstmt.setInt(5, 2);
                pstmt.setInt(6, 1);
                pstmt.addBatch();

                pstmt.setInt(1, 77173832);
                pstmt.setString(2, "test2");
                pstmt.setInt(3, 5);
                pstmt.setInt(4, 15);
                pstmt.setInt(5, 2);
                pstmt.setInt(6, 2);
                pstmt.addBatch();

                pstmt.setInt(1, 1676197067);
                pstmt.setString(2, "test3");
                pstmt.setInt(3, 1);
                pstmt.setInt(4, 15);
                pstmt.setInt(5, 4);
                pstmt.setInt(6, 2);
                pstmt.addBatch();

                pstmt.executeBatch();
            }
        }
        catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
}
