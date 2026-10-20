/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package fantasitcRestaurant;


import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class ModelTest {
    private Model instance;
    private ArrayList<Food> filtered;
    private ModelListener listener;
    
    public ModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // test variables
        filtered = new ArrayList<>();
        
        //
        filtered.add(new MainMenue("Beef Pho", 19.50,2,false, false, true));
        filtered.add(new MainMenue("Chicken Pho", 18.50,1,false ,false , true));  
        
        //for error test
        
    }
    
    @After
    public void tearDown() {
    }
    
    
        
    @Test // test1 it was successfull and the order was added
    public void addOrderTest()
    {
        FileManagerdbs fileManager = new FileManagerdbs();
        View viewStart = new View();
        instance = new Model(viewStart, fileManager);
        instance.addListener(viewStart);
        // check order added with valid input
        // item choice beef pho 2 servings
        instance.addOrder(filtered, 1, 2);
        
        // checks food 
        Food beefPho = filtered.get(0);
        
        // checks correct number of beef pho is saved
        assertEquals(Integer.valueOf(2), instance.getOrderItems().get(beefPho));
    }
    
    
    // test for errors
    
    int capturedCode = -1;
    String capturedMessage = "";
        
    @Test // test ordernumber invalid
    public void addOrderTestErrorNum1()
    {
        //initialisation
        FileManagerdbs fileManager = new FileManagerdbs();
        View viewStart = new View();
        instance = new Model(viewStart, fileManager);
        
                
        instance.addListener(new ModelListener() {
            @Override
            public void changetxtDisplay(int code, String txt) {
                capturedCode = code;
                capturedMessage = txt;
            }

            @Override
            public void switchTo(int num) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        instance.addOrder(filtered, 0, 2);
        
        assertEquals(2,capturedCode);
        assertEquals("Sorry that isn't an option please input a valid number", capturedMessage);
    }
    
    @Test //test3 the other way around
    public void addOrderTestErrorNum2()
    {
        //initialisation
        FileManagerdbs fileManager = new FileManagerdbs();
        View viewStart = new View();
        instance = new Model(viewStart, fileManager);
        
                
        instance.addListener(new ModelListener() {
            @Override
            public void changetxtDisplay(int code, String txt) {
                capturedCode = code;
                capturedMessage = txt;
            }

            @Override
            public void switchTo(int num) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        instance.addOrder(filtered, 2, 0);
        
        assertEquals(2,capturedCode);
        assertEquals("Sorry that isn't an option please input a valid number", capturedMessage);
    }
    
    @Test //test5 the order number is too bug
    public void addOrderTestErrorNum3()
    {
        //initialisation
        FileManagerdbs fileManager = new FileManagerdbs();
        View viewStart = new View();
        instance = new Model(viewStart, fileManager);
        
                
        instance.addListener(new ModelListener() {
            @Override
            public void changetxtDisplay(int code, String txt) {
                capturedCode = code;
                capturedMessage = txt;
            }

            @Override
            public void switchTo(int num) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
            
        // checks for error message
        instance.addOrder(filtered, 3, 1);
        
        assertEquals(2,capturedCode);
        assertEquals("Sorry that isn't an option please input a valid number", capturedMessage);
    }
    
    
    //is available
    
    private HashMap<Integer, HashMap<Integer, Integer>> bookedTime = new HashMap<>();
    
    @Test // invalid amount of people (to many people)
    public void checkAvailabilityTest()
    {
        FileManagerdbs fileManager = new FileManagerdbs();
        View viewStart = new View();
        instance = new Model(viewStart, fileManager);
        bookedTime = fileManager.readBookings();
        
        String result = instance.isAvailable(bookedTime,2,13,11,1);
        String expected = "unavailable";
        
        assertEquals(expected, result);
    }
    
    @Test // invalid amount of people (no people)
    public void checkAvailabilityTest2()
    {
        FileManagerdbs fileManager = new FileManagerdbs();
        View viewStart = new View();
        instance = new Model(viewStart, fileManager);
        bookedTime = fileManager.readBookings();
        
        String result = instance.isAvailable(bookedTime,2,13,0,1);
        String expected = "unavailable";
        
        assertEquals(expected, result);
    }
    
    @Test // everything valid
    public void checkAvailabilityTest3()
    {
        FileManagerdbs fileManager = new FileManagerdbs();
        View viewStart = new View();
        instance = new Model(viewStart, fileManager);
        
        bookedTime.put(1, new HashMap<>());
        HashMap<Integer, Integer> timeMap = bookedTime.get(1);
        timeMap.put(1, 1);
        
        String result = instance.isAvailable(bookedTime,2,13,0,1);
        String expected = "unavailable";
        
        assertEquals(expected, result);
    }
    
    
    @Test // check show order is working 
    public void showOrder()
    {
        FileManagerdbs fileManager = new FileManagerdbs();
        View viewStart = new View();
        instance = new Model(viewStart, fileManager);
        
        instance.bookingDay = 1;
        instance.bookingHour = 13;
        instance.bookingPeople = 2;
        instance.bookingTime = 1;
        instance.currentUser = "test1";
        
        instance.addListener(new ModelListener() {
            @Override
            public void changetxtDisplay(int code, String txt) {
                capturedCode = code;
                capturedMessage = txt;
            }

            @Override
            public void switchTo(int num) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        
        instance.showOrderDetailsToBeConfirmed();
        
        assertEquals(10,capturedCode);
        assertEquals("<html>Username: test1<br><html> booking available <br> Day 1<br> Hour 13<br> People 2<br> Time 1<html><br> <html> Your Order Summary<br> No items ordered <html>", capturedMessage);
       
    }
}
