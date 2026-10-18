/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author mered
 */
public class FileManagertxt implements FileManager{
    
    private static final String userFile = "Username.txt";
    private static final String bookingFile = "Booking.txt";
    private static final String orderFile = "Order.txt";
    private Menue m1 = new Menue();
    private ArrayList<Food> fullMenu = m1.getFullMenu();
    
    private static final String locationDB = "jdbc:derby://localhost:1527/FantasticDatabase";
    private static final String USER = "APP";
    private static final String PASS = "APP";
    
    //test
    private final FRDBManager frdbManager;
    private final Connection conn;
    private Statement statement;
    
    

    public FileManagertxt() {
        frdbManager = new FRDBManager();
        conn = frdbManager.getConnection();
    }

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
    
    @Override
    public void test()
    {
        try
        {
            String getData = "SELECT USERNAME, PASSWORD FROM USERPASSWORD";
            ResultSet rs = frdbManager.statement.executeQuery(getData); 
            while (rs.next())
            {
                String presentUsername = rs.getString("USERNAME");
                String presentPassword = rs.getString("PASSWORD");
                System.out.print(presentUsername + " " + presentPassword + "\n");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    

    @Override
    public boolean savesUser(String username, String password) { // writer 1 - converted and tested
        
        String getData = "SELECT COUNT(*) FROM USERPASSWORD WHERE USERNAME = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(getData))
        {
            pstmt.setString(1, username);
            try
            {
                ResultSet rs = pstmt.executeQuery(); 
                if (rs.next() && rs.getInt(1) > 0)
                {
                    System.out.println("Username already exist");
                    return false;
                }
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
        
        
        
        String insertUser = "INSERT INTO USERPASSWORD (USERNAME, PASSWORD) VALUES (?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertUser))
        {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.execute();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        System.out.println("=====================================================\n");               
        System.out.println("Welcome Back " + username + "\n");
        System.out.println("=====================================================\n");

        return true; // Return true if signup/save was successful
    }

    @Override
    public String authenticateUser(String username, String password) { // reader 1 - converted and tested
        
        String getData = "SELECT USERNAME, PASSWORD FROM USERPASSWORD";
        
        try
        {
            ResultSet rs = frdbManager.statement.executeQuery(getData); 
            while (rs.next())
            {
                String presentUsername = rs.getString("USERNAME");
                String presentPassword = rs.getString("PASSWORD");
                if (presentUsername.equals(username) && presentPassword.equals(password))
                {
                    return username;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
        return null;
        
    }

    @Override
    public Integer savesBooking(String bookedName, String bookedTime) { //writer 2 - converted and tested
        
        String getData = "SELECT BOOKINGID, USERNAME, DAYOFWEEK, TIMEOFDAY, DURATION FROM USERBOOKING WHERE USERNAME = ?";
        boolean noClash = true;
        String[] newParts = bookedTime.split(",");
        int newday = Integer.parseInt(newParts[0]); 
        int newhour = Integer.parseInt(newParts[1]);
        int newPeople = Integer.parseInt(newParts[2]);
        int newtime = Integer.parseInt(newParts[3]);
        
            try (PreparedStatement pstmt = conn.prepareStatement(getData))
            {
                pstmt.setString(1, bookedName);
                ResultSet rs = pstmt.executeQuery(); 
                while (rs.next())
                {
                    int bookingID = rs.getInt("BOOKINGID");
                    int presentDay = rs.getInt("DAYOFWEEK");
                    int presentTime = rs.getInt("TIMEOFDAY");
                    int presentDuration = rs.getInt("DURATION");
                    
                    if (presentDay == newday)
                    {
                       //new hours present
                        ArrayList<Integer> newHoursPresent = new ArrayList<>();

                        for (int i = 0; i<newtime; i++)
                        {
                            newHoursPresent.add(newhour+i);
                        }

                        //old hours present

                        ArrayList<Integer> oldHoursPresent = new ArrayList<>();

                        for (int i = 0; i<presentDuration; i++)
                        {
                            oldHoursPresent.add(presentTime+i);
                        }

                        for (Integer hours1 : newHoursPresent)
                        {
                            for (Integer hours2 : oldHoursPresent)
                            {
                                if (Objects.equals(hours1, hours2))
                                {
                                    System.out.println("Sorry, this booking clashes with your previous booking");
                                    System.out.print(bookingID);
                                    noClash = false;
                                    return 0;
                                }
                            }
                        } 
                    }
                    
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
                return null;
            }
        if (noClash)
        {
           String insertUser = "INSERT INTO USERBOOKING (BOOKINGID, USERNAME, DAYOFWEEK, TIMEOFDAY, PEOPLE, DURATION) VALUES (?,?,?,?,?,?)";
        
            int bookingId = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
            try (PreparedStatement pstmt = conn.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS))
                {

                    pstmt.setInt(1, bookingId);
                    pstmt.setString(2, bookedName);
                    pstmt.setInt(3, newday);
                    pstmt.setInt(4, newhour);
                    pstmt.setInt(5, newPeople);
                    pstmt.setInt(6, newtime);

                    pstmt.execute();

                    return bookingId;
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
        }
        return null;
    }

    @Override
    public HashMap<Integer, HashMap<Integer, Integer>> readBookings() { // reader 2 - converted and tested
        HashMap<Integer, HashMap<Integer, Integer>> bookedTime = new HashMap<>(); // new hashmap that stores day, hour, people
        
        String getData = "SELECT DAYOFWEEK, TIMEOFDAY, PEOPLE, DURATION FROM USERBOOKING";
        

        
        try 
        {
            
            ResultSet bookingList = frdbManager.statement.executeQuery(getData);
            
            
            while (bookingList.next()) // store next line to theLine, carries on past the loop when theirs nothing in the txt file
            {
                // converts string from reader to int and stores them
                int day = bookingList.getInt("DAYOFWEEK");
                int hour = bookingList.getInt("TIMEOFDAY");
                int people = bookingList.getInt("PEOPLE");
                int time = bookingList.getInt("DURATION");
            
                
            // only create new day if the day specified isn't already present
            if (!bookedTime.containsKey(day))
            {
                //create new day
                bookedTime.put(day, new HashMap<>());
            }

            HashMap<Integer, Integer> timeMap = bookedTime.get(day);

            //AI assisted
            if (hour > 10 || hour < 23)
            {
                for (int i = 0; i  <time; i++)
                {
                    int presentH = hour + i;
                    //get the amount of people for hour if their is none default 0
                    int presentC = timeMap.getOrDefault(presentH, 0);

                    // update the amount of poeple in the hour;
                    timeMap.put(presentH, presentC + people);
                }
            }
                   
            }
            return bookedTime;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        return new HashMap<>();
    }
    
    @Override
    public String readUserBookings(String username) // converted an tested
    {
        String getData = "SELECT BOOKINGID, USERNAME, DAYOFWEEK, TIMEOFDAY, PEOPLE, DURATION FROM USERBOOKING WHERE USERNAME = ?";
        int bookingCount = 0;
        
        try (PreparedStatement pstmt = conn.prepareStatement(getData))
            {
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery(); 
                while (rs.next())
                {
                    
                    int presentDay = rs.getInt("DAYOFWEEK");
                    int presentTime = rs.getInt("TIMEOFDAY");
                    int presentPeople = rs.getInt("PEOPLE");
                    int presentDuration = rs.getInt("DURATION");
                    
                    bookingCount++;
                    String yourBooking =  bookingCount + ") day: " + presentDay + " at " + presentTime + ":00 o'clock" + " people: " + presentPeople + " time: " + presentDuration + "\n";
                    System.out.println(yourBooking);
                    
                    
                    
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
                return null;
            }
            return null;
    }
    
    @Override
    public String cancelBooking(String username, int bookingNumber) // converted and checked
    {
        //find booking ID
        String getBookingData = "SELECT BOOKINGID FROM USERBOOKING WHERE USERNAME = ? OFFSET ? ROWS FETCH FIRST 1 ROWS ONLY";
        Integer theBookingId = null;
        
        try (PreparedStatement pstmt = conn.prepareStatement(getBookingData))
            {
                pstmt.setString(1, username);
                pstmt.setInt(2, bookingNumber-1);
                ResultSet rs = pstmt.executeQuery(); 
                
                rs.next();
                theBookingId = rs.getInt("BOOKINGID");
            }
            catch(Exception e)
            {
                System.out.println(e);
                return null;
            }
            
            
        //delete booking with booking id
        String deleteBooking = "DELETE FROM USERBOOKING WHERE BOOKINGID = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(deleteBooking))
            {
                pstmt.setInt(1, theBookingId);
                pstmt.execute();
            }
            catch(Exception e)
            {
                System.out.println(e);
                return null;
            }
        //delete order with booking id
        String deleteOrder = "DELETE FROM USERORDER WHERE BOOKINGID = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(deleteOrder))
            {
                pstmt.setInt(1, theBookingId);
                pstmt.execute();
            }
            catch(Exception e)
            {
                System.out.println(e);
                return null;
            }
            return "your booking has been deleated";
    }

    @Override
    public boolean savesOrder(Map<Food, Integer> preOrder, Integer bookingID) // -- converted and tested
    {
        String insertUser = "INSERT INTO USERORDER (BOOKINGID, DISH, SERVINGS) VALUES (?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertUser))
        {
            if (preOrder.isEmpty())
                {
                    pstmt.setInt(1, bookingID);
                    pstmt.setString(2, "-");
                    pstmt.setInt(3, 0);
                    pstmt.execute();
                }
                
            for (Map.Entry<Food, Integer> entry : preOrder.entrySet()) {

                    pstmt.setInt(1, bookingID);
                    pstmt.setString(2, entry.getKey().getFoodName());
                    pstmt.setInt(3, entry.getValue());
                    pstmt.execute();
                }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
//        try (BufferedWriter orderWriter = new BufferedWriter(new FileWriter(orderFile, true))) {
//                for (Map.Entry<Food, Integer> entry : preOrder.entrySet()) {
//                    orderWriter.write(entry.getKey().getFoodName() +","+ entry.getValue());
//                    orderWriter.newLine();
//                }
//                if (preOrder.isEmpty())
//                {
//                    orderWriter.write("-");
//                    orderWriter.newLine();
//                }
//                return true;
//            } catch (IOException e) {
//                System.out.println("Error saving order details: " + e.getMessage());
//                return false;
//                
//            }
        return true;
    }
    
    @Override
    public String readsUserOrder(String username, int bookingNumber) // converted and tested
    {
        
        //find booking ID
        String getBookingData = "SELECT BOOKINGID FROM USERBOOKING WHERE USERNAME = ? OFFSET ? ROWS FETCH FIRST 1 ROWS ONLY";
        Integer theBookingId = null;
        
        try (PreparedStatement pstmt = conn.prepareStatement(getBookingData))
            {
                pstmt.setString(1, username);
                pstmt.setInt(2, bookingNumber-1);
                ResultSet rs = pstmt.executeQuery(); 
                
                rs.next();
                theBookingId = rs.getInt("BOOKINGID");
            }
            catch(Exception e)
            {
                System.out.println(e);
                return null;
            }
        
        // reads user order
        String getData = "SELECT DISH, SERVINGS FROM USERORDER WHERE BOOKINGID = ?";
        String yourBooking = "============================================================\n";
        double totalOrderCost = 0;
        double total = -1;
        
        try (PreparedStatement pstmt = conn.prepareStatement(getData))
        {
            pstmt.setInt(1, theBookingId);
            ResultSet rs = pstmt.executeQuery(); 
            
            
            while (rs.next())
            {
                String orderDish = rs.getString("DISH");
                int orderAmount = rs.getInt("SERVINGS");
                
                if ("-".equals(orderDish))
                {
                    return "you haven't ordered anything";
                }
                else
                {
                    for (Food item: fullMenu)
                    {
                        if(item.getFoodName().equalsIgnoreCase(orderDish))
                        {
                            double price = item.getPrice();
                            double amount = orderAmount;
                            total = price*amount;
                            
                            totalOrderCost += total;
                        }
                        yourBooking += "\n" + orderDish + " x " + orderAmount + "\n" + total + "\n";
                    }
                }
                    
                
            }
            yourBooking += """
                           
                           
                           Total Cost: """ + totalOrderCost;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return yourBooking;
        
    }
    
}
