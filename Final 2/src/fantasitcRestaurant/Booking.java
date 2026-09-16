/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//only manges booking
/**
 *
 * @author mered
 */
public class Booking {
    
    // adds filemanager to readbooking
    private final FileManagertxt fileManager;
    // the max capacit per hour
    private final int maxCapacity = 10;
    // hashmap that stores the day, the hour, and amount of people present that hour
    private final HashMap<Integer, HashMap<Integer, Integer>> bookedTime;

    public Booking() {
        this.fileManager = new FileManagertxt();
        // reads the file and stores the bookings in this hashmap
        this.bookedTime = fileManager.readBookings();
    }
    
    
    public String makeABooking(Scanner msc) // main function for the user to make a booking
    {   
        // allows user to check availabilities and or make a booking
        boolean booking1 = true;
        while (booking1)
        {
            
           System.out.println("""
                           =====================================================
                           (Warning quiting will restart the ordering process)  
                           
                           0) Quit
                           1) check availabilities
                           2) make a booking
                              
                           =====================================================
                              """);
            switch (msc.nextInt())
            {
                case 0:
                    return null;
                case 1:
                    //runs checkAvailability method
                    checkAvailability(bookedTime);
                    break;
                case 2:
                    //runs isAvailable to make a available booking
                    String currentSelection = isAvailable(bookedTime, msc);
                    // if booking isn't availble returns null and alows the user to start again
                    if (currentSelection == null)
                    {
                        break;
                    }
                    
                    System.out.println("""
                           =====================================================            

                           would you like to
                           0) Go back
                           1) confirm booking
                           2) make another choice

                           =====================================================
                            """);
                    switch (msc.nextInt())
                    {
                        case 0:
                            break;
                        case 1:
                            // returns current booking to main screen to be saved later
                            return currentSelection;
                        case 2:
                            break;
                        default:
                            System.out.print("That was not an option");
                            break;
                    }

                    break;
                default:
                    System.out.print("That was not an option");
                    break;
            }
        }
    return null;
    }
    
    public void checkAvailability(HashMap<Integer, HashMap<Integer,Integer>> bookedTime) //allows the user to check current availabilities
    {
        // Shows availability
            //loops the map to see all days
            for (Map.Entry<Integer, HashMap<Integer, Integer>> dayBooking : bookedTime.entrySet())
            {
                int dayNumber = dayBooking.getKey();
                HashMap<Integer, Integer> dayMap = dayBooking.getValue();
                
                System.out.println("\nDay " + dayNumber + " Availability: ");
                
                // loops hours to see how many availabilities are left in the hour
                for (int i = 11; i <= 22; i++)
                {
                    int peoplePresent = dayMap.getOrDefault(i, 0);
                    int spotsLeft = maxCapacity - peoplePresent;
                    
                    if (spotsLeft > 0)
                    {
                        System.out.println(i+ " o'clock" + ": Spots left " + spotsLeft);
 
                    }
                    else
                    {
                        System.out.println("Fully Booked");
                    }
                        
                }
            }
    }
    
    public static String isAvailable(HashMap<Integer, HashMap<Integer,Integer>> bookedTime, Scanner msc) // confirms that their booking is available
    {

        // gets userInput on their booking
            System.out.println("\nBooking Day (1)Monday (6) Saturday");
            System.out.println("""
                           =====================================================            
                           Input day
                           
                           0) Go back
                           1) Monday
                           2) Tuesday
                           3) Wednesday
                           4) Thursday
                           5) Friday
                           6) Saturday    

                           =====================================================
                            """);
            boolean daySelected = false;
            int bookingDay = -2;
            while (!daySelected)
            {
                bookingDay = msc.nextInt();
                if (bookingDay > 6 || bookingDay < 1)
                {
                    if (bookingDay == 0 )
                    {
                        return null;
                    }
                    System.out.println("invalid date");
                }
                else
                {
                    daySelected = true;
                }
            }
            
            System.out.println("""
                           =====================================================            
                           Booking Hour (use 24 hour clock)     
                           Book between 11:00 and 22:00
                           Type 0 to quit
                           =====================================================
                            """);
            
            boolean hourSelected = false;
            int bookingHour = -2;
            while (!hourSelected)
            {
                bookingHour = msc.nextInt();
                if (bookingHour > 22 || bookingHour < 11)
                {
                    if (bookingHour == 0)
                    {
                        return null;
                    }
                    System.out.println("invalid hour");
                }
                else
                {
                    hourSelected = true;
                }
            }
            System.out.println("""
                           =====================================================            
                           For how many People
                           Type 0 to quit
                           =====================================================
                            """);
            
            boolean peopleSelected = false;
            int bookingPeople = -2;
            while (!peopleSelected)
            {
                bookingPeople = msc.nextInt();
                if (bookingPeople > 10 || bookingPeople < 1)
                {
                    if (bookingPeople == 0)
                    {
                        return null;
                    }
                    System.out.println("invalid people");
                }
                else
                {
                    peopleSelected = true;
                }
            }
            
            System.out.println("""
                           =====================================================            
                           For how long
                           Type 0 to quit
                           =====================================================
                            """);
            boolean timeSelected = false;
            int bookingTime = -2;
            while (!timeSelected)
            {
                bookingTime = msc.nextInt();
                if (bookingTime > 11 || bookingTime < 1)
                {
                    if (bookingTime == 0)
                    {
                        return null;
                    }
                    System.out.println("invalid time");
                }
                else
                {
                    timeSelected = true;
                }
            }
            
            //checks if their booking is available
            HashMap<Integer, Integer> theTimeMap = bookedTime.getOrDefault(bookingDay, new HashMap<>());

            for (int i = 0; i < bookingTime; i++)
            {
                int peopleCount = theTimeMap.getOrDefault(bookingHour + i, 0);
                if ((peopleCount + bookingPeople) > 10)
                {
                    System.out.println("unavailable");
                    return null;
                }
            }
            
        System.out.println("""
                           =====================================================
                           
                           Booking available
                           Day: """+ bookingDay +"""
                                                 
                           Hour: """+ bookingHour +"""
                                                   
                           People: """+ bookingPeople +"""
                                                       
                           Time: """+ bookingTime +"""
                                                 
                           =====================================================
                           """);
        return bookingDay + ","+ bookingHour + "," + bookingPeople + "," + bookingTime;
    }
}
