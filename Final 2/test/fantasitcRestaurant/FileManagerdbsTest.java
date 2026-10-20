/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package fantasitcRestaurant;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mered
 */
public class FileManagerdbsTest {
    
    FileManagerdbs instance;
    
    public FileManagerdbsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new FileManagerdbs();
    }
    
    @After
    public void tearDown() {
        
    }

    
    @Test // test valid authentication
    public void testAuthenticateUser() {
        String user = "test1";
        String password = "password1";
        
        String result = instance.authenticateUser(user, password);
        String expected = "test1";
        
        assertEquals(expected,result);
    }
    
    @Test // test missing authentication
    public void testAuthenticateUserForNull() {
        String user = "";
        String password = "";
        
        String result = instance.authenticateUser(user, password);
        
        assertNull(result);
    }
    
}
