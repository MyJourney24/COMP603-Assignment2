/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

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
    

    @Override
    public boolean savesUser(String username, String password) { // writer 1
        
        if(usernameExists(username))
        {
            System.out.println("please use another username");
            return false;
        }
        
        try (FileWriter fwU = new FileWriter(userFile, true)) {
        String usernameWithNewLine = username + "," + password;
        fwU.write(usernameWithNewLine);
        
        fwU.close();
        System.out.println("=====================================================\n");               
        System.out.println("Welcome Back " + username + "\n");
        System.out.println("=====================================================\n");
            
        return true; // Return true if signup/save was successful
        
        } 
        catch (IOException e) {
            System.out.println("Error: Could not save user data. " + e.getMessage());
            return false;
        }        
    }

    @Override
    public String authenticateUser(String username, String password) { // reader 1
        
        try
        {
            FileReader frU = new FileReader(userFile); // reads the txt file
            BufferedReader brU = new BufferedReader(frU);
            
            String line;
            
            
            while ((line = brU.readLine()) != null) // store next line to theLine, carries on past the loop when theirs nothing in the txt file
            {
                String[] parts = line.split(",");
                if(username.equals(parts[0]) && password.equals(parts[1])) // ps even numbers in array are username while odd is password
                {
                    return username; // returns true if combination is found
                }

            }
              
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean savesBooking(String bookedName, String bookedTime) { //writer 2
        
        try 
        {
            FileReader fr = new FileReader("Booking.txt"); // reads the txt file
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            boolean noOverlap = true;
            
            String[] newParts = bookedTime.split(",");
            int newday = Integer.parseInt(newParts[0]); 
            int newhour = Integer.parseInt(newParts[1]);
            int newtime = Integer.parseInt(newParts[3]);
            
            
            while ((line = br.readLine()) != null)
            {
                String[] parts = line.split(",");
                
                String name = parts[0];
                int day = Integer.parseInt(parts[1]); 
                int hour = Integer.parseInt(parts[2]);
                int time = Integer.parseInt(parts[4]);
                
                if (newday == day && name.equals(bookedName))
                {
                    //new hours present
                    ArrayList<Integer> newHoursPresent = new ArrayList<Integer>();
                    
                    for (int i = 0; i<newtime; i++)
                    {
                        newHoursPresent.add(newhour+i);
                    }
                    
                    //old hours present
                    
                    ArrayList<Integer> oldHoursPresent = new ArrayList<Integer>();
                    
                    for (int i = 0; i<time; i++)
                    {
                        oldHoursPresent.add(hour+i);
                    }
                    
                    for (Integer hours1 : newHoursPresent)
                    {
                        for (Integer hours2 : oldHoursPresent)
                        {
                            if (hours1 == hours2)
                            {
                                System.out.println("Sorry, this booking clashes with your previous booking");
                                return false;
                            }
                        }
                    }
                    
                }
            }
            
        }
        catch (IOException e) {
            System.out.println("Error saving order details: " + e.getMessage());
            return false;
        }

        try (BufferedWriter bookingWriter = new BufferedWriter(new FileWriter(bookingFile, true))) {
            bookingWriter.write(bookedName+ "," + bookedTime);
            bookingWriter.newLine();
            
        } catch (IOException e) {
            System.out.println("Error saving order details: " + e.getMessage());
            return false;
        }
        
        System.out.println("Saved");
        return true;
    }

    @Override
    public HashMap<Integer, HashMap<Integer, Integer>> readBookings() { // reader 2
        HashMap<Integer, HashMap<Integer, Integer>> bookedTime = new HashMap<>(); // new hashmap that stores day, hour, people
        
        try 
        {
            FileReader fr = new FileReader("Booking.txt"); // reads the txt file
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            ArrayList<String> unique = new ArrayList<>();
            
            //AI assistance start
            while ((line = br.readLine()) != null) // store next line to theLine, carries on past the loop when theirs nothing in the txt file
            {
                String[] parts = line.split(","); // splits the line at "," and stores it seperately
                
                // converts string from reader to int and stores them
                String name = parts[0];
                int day = Integer.parseInt(parts[1]); 
                int hour = Integer.parseInt(parts[2]);
                int people = Integer.parseInt(parts[3]);
                int time = Integer.parseInt(parts[4]);
            //AI assistance end
            
                String currentCode = name + day + hour + people + time;
                boolean codeNotSeen = true;
            
                //checks if this is a duplicate
                for (String code : unique)
                    {
                        if (code.equals(currentCode))
                        {
                            codeNotSeen = false;
                        }

                    }
                
                if (codeNotSeen == true)
                {
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
                    unique.add(currentCode);
                }   
            }
            return bookedTime;
        }
        catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
        
        return new HashMap<>();
    }
    
    @Override
    public String readUserBookings(String username)
    {
       try
        {
            String yourBooking = "";
            int bookingCount = 0;
            FileReader fr = new FileReader("Booking.txt"); // reads the txt file
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            ArrayList<Integer> unique = new ArrayList<>();
            
            while ((line = br.readLine()) != null) // store next line to theLine, carries on past the loop when theirs nothing in the txt file
            {
                String[] parts = line.split(","); // splits the line at "," and stores it seperately
                
                // converts string from reader to int and stores them
                String name = parts[0];
                int day = Integer.parseInt(parts[1]); 
                int hour = Integer.parseInt(parts[2]);
                int people = Integer.parseInt(parts[3]);
                int time = Integer.parseInt(parts[4]);
                
                if (username.equals(name)) // checks to see if the the booking is the user
                {
                    Integer currentCode = day + hour + people + time;
                    boolean codeNotSeen = true;

                    for (Integer code : unique)
                    {
                        if (code.equals(currentCode))
                        {
                            codeNotSeen = false;
                        }

                    }

                    if (codeNotSeen == true)
                    {
                        bookingCount ++;
                        yourBooking += bookingCount + ") day: " + day + " at " + hour + ":00 o'clock" + " people: " + people + " time: " + time + "\n";
                        unique.add(currentCode);
                    }    
                }    
            }
            System.out.print(yourBooking);
        }
        catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
            
        return null;
    }
    
    @Override
    public String cancelBooking(String username, int bookingNumber)
    {
        // used AI research an realised creating a whole seperate file is easier to delete in this case
        // had to modify the path thing to work with AI assissted replace existing thing below
        Path pathBookingTemp = Path.of("Booking_temp.txt");
        Path pathOrderTemp = Path.of("Order_temp.txt");
        
        Path pathBooking = Path.of(bookingFile);
        Path pathOrder = Path.of(orderFile);
        
        String yourBooking = "";
        String bookingTarget = null;
        
       
        // just coppied the read user booking file earlier
        try (BufferedReader bookingReader = Files.newBufferedReader(pathBooking);
         BufferedReader orderReader = Files.newBufferedReader(pathOrder))
        {
            
            
            // the line currently read
            String bLine;
            int bookingCount = 0;
            
            //stores and prevents duplicate reads
            ArrayList<String> unique = new ArrayList<>();
            
            // reads currennt order and booking
            while ((bLine = bookingReader.readLine()) != null && 
                    (orderReader.readLine()) != null) // store next line to theLine, carries on past the loop when theirs nothing in the txt file
            {
                
                // splits the line at "," and stores it seperately
                String[] parts = bLine.split(","); 
                
                // converts string from reader to int and stores them
                String name = parts[0];
                int day = Integer.parseInt(parts[1]); 
                int hour = Integer.parseInt(parts[2]);
                int people = Integer.parseInt(parts[3]);
                int time = Integer.parseInt(parts[4]);
                
                
                if (username.equals(name)) // checks to see if the the booking is the user
                {
                    
                    boolean codeNotSeen = true;

                    for (String code : unique)
                    {
                        if (code.equals(bLine))
                        {
                            codeNotSeen = false;
                        }

                    }

                    if (codeNotSeen == true)
                    {
                        bookingCount ++;
                        
                        if (bookingCount == bookingNumber)
                        {
                            yourBooking += bookingCount + ") day: " + day + " at " + hour + ":00 o'clock" + " people: " + people + " time: " + time + "\n";
                            bookingTarget = bLine;
                            break;
                        }
                        
                        unique.add(bLine);
                        
                        
                    }    
                }    
            }   
        }
        catch (IOException e) {
            System.out.println(e);
        }
        
        if (bookingTarget == null)
        {
            return "Booking not found";
        }
        
        // starts writing other orders that aren't being canceled.
        try (BufferedReader bookingReader = Files.newBufferedReader(pathBooking); 
                BufferedReader orderReader = Files.newBufferedReader(pathOrder); 
                BufferedWriter bookingWriter = Files.newBufferedWriter(pathBookingTemp); 
                BufferedWriter orderWriter = Files.newBufferedWriter(pathOrderTemp)) {

            String orderLine;
            String bookingLine;
            while ((bookingLine = bookingReader.readLine()) != null
                    && (orderLine = orderReader.readLine()) != null) {

                if (bookingLine.equals(bookingTarget)) {

                } else {
                    bookingWriter.write(bookingLine + "\n");
                    orderWriter.write(orderLine + "\n");
                }

            }
        } catch (IOException e) {
            System.out.println(e);
        }
        
        // AI assistance
        try
        {
            System.gc();
            
            Files.move(pathBookingTemp, pathBooking, StandardCopyOption.REPLACE_EXISTING);
            Files.move(pathOrderTemp, pathOrder, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return yourBooking + "has been deleated";
    }
    
    //Sign in functions
    public static boolean usernameExists(String newusername)
    {
        try
        {
            FileReader frU = new FileReader(userFile); // reads the txt file
            BufferedReader brU = new BufferedReader(frU);
 
           String username;
            
            while ((username = brU.readLine()) != null) // store next line to theLine, carries on past the loop when theirs nothing in the txt file
            {
                if(newusername.equals(username)) // ps even numbers in array are username while odd is password
                {
                    return true; // returns true if combination is found
                }
            }
              
        }
        catch (IOException e) {
            System.out.println(e);
        }
        
        return false; 
    }

    @Override
    public boolean savesOrder(Map<Food, Integer> preOrder) 
    {
        try (BufferedWriter orderWriter = new BufferedWriter(new FileWriter(orderFile, true))) {
                for (Map.Entry<Food, Integer> entry : preOrder.entrySet()) {
                    orderWriter.write(entry.getKey().getFoodName() +","+ entry.getValue());
                    orderWriter.newLine();
                }
                if (preOrder.isEmpty())
                {
                    orderWriter.write("-");
                    orderWriter.newLine();
                }
                return true;
            } catch (IOException e) {
                System.out.println("Error saving order details: " + e.getMessage());
                return false;
                
            }
        
    }
    
    @Override
    public String readsUserOrder(String username, int bookingNumber)
    {
        Path pathBookingTemp = Path.of("Booking_temp.txt");
        Path pathOrderTemp = Path.of("Order_temp.txt");

        Path pathBooking = Path.of(bookingFile);
        Path pathOrder = Path.of(orderFile);

        String yourBooking = "============================================================\n";
        String bookingTarget = null;
        
        // locates target order
        try (BufferedReader bookingReader = Files.newBufferedReader(pathBooking); BufferedReader orderReader = Files.newBufferedReader(pathOrder)) {

            // the line currently read
            String bLine;
            int bookingCount = 0;

            //stores and prevents duplicate reads
            ArrayList<String> unique = new ArrayList<>();

            // reads currennt order and booking
            while ((bLine = bookingReader.readLine()) != null
                    && (orderReader.readLine()) != null) // store next line to theLine, carries on past the loop when theirs nothing in the txt file
            {

                // splits the line at "," and stores it seperately
                String[] parts = bLine.split(",");

                // converts string from reader to int and stores them
                String name = parts[0];

                if (username.equals(name)) // checks to see if the the booking is the user
                {

                    boolean codeNotSeen = true;

                    for (String code : unique) {
                        if (code.equals(bLine)) {
                            codeNotSeen = false;
                        }

                    }

                    if (codeNotSeen == true) {
                        bookingCount++;

                        if (bookingCount == bookingNumber) {
                            bookingTarget = bLine;
                            break;
                        }

                        unique.add(bLine);

                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        
        // if target order can't be found
        if (bookingTarget == null) {
            return "Booking not found";
        }
        
        // if target order is found
        try (BufferedReader bookingReader = Files.newBufferedReader(pathBooking); BufferedReader orderReader = Files.newBufferedReader(pathOrder); BufferedWriter bookingWriter = Files.newBufferedWriter(pathBookingTemp); BufferedWriter orderWriter = Files.newBufferedWriter(pathOrderTemp)) {

            String orderLine;
            String bookingLine;
            
            double totalOrderCost = 0;
            
            while ((bookingLine = bookingReader.readLine()) != null
                    && (orderLine = orderReader.readLine()) != null) {
                
                if (bookingLine.equals(bookingTarget)) {
                    String[] orderDetails = orderLine.split(",");
                    
                    double total = -1;
                    for (Food item: fullMenu)
                    {
                        if(item.getFoodName().equalsIgnoreCase(orderDetails[0]))
                        {
                            double price = item.getPrice();
                            double amount = Double.parseDouble(orderDetails[1]);
                            total = price*amount;
                            
                            totalOrderCost += total;
                        }
                    }
                    if (total > 0)
                    {
                        yourBooking += "\n" + orderDetails[0] + " x " + orderDetails[1] + "\n" + total + "\n";
                    }
                    else
                    {
                        yourBooking += "you have yet to order anything";
                    }
                }
            }
            
            yourBooking += """
                           
                           
                           Total Cost: """ + totalOrderCost;
            
        } catch (IOException e) {
            System.out.println(e);
        }
        
        return yourBooking;
        
    }
    
}
