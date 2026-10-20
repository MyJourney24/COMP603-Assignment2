/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;
import java.util.HashMap;
import java.util.Map;

//only manges booking
/**
 *
 * @author mered
 */
public class Booking {
    
    // adds filemanager to readbooking
    private final FileManagerdbs fileManager;
    // the max capacit per hour
    private final int maxCapacity = 10;
    // hashmap that stores the day, the hour, and amount of people present that hour
    private final HashMap<Integer, HashMap<Integer, Integer>> bookedTime;

    
    public Booking(FileManagerdbs fileManager) {
        this.fileManager = fileManager;
        // reads the file and stores the bookings in this hashmap
        this.bookedTime = fileManager.readBookings();
    }
    
    public String checkAvailability(HashMap<Integer, HashMap<Integer,Integer>> bookedTime) //allows the user to check current availabilities
    {
        String availabilitySummary = "<html>";
        // Shows availability
            //loops the map to see all days
            for (Map.Entry<Integer, HashMap<Integer, Integer>> dayBooking : bookedTime.entrySet())
            {
                int dayNumber = dayBooking.getKey();
                HashMap<Integer, Integer> dayMap = dayBooking.getValue();
                
                availabilitySummary += "<br>Day " + dayNumber + " Availability: ";
                // loops hours to see how many availabilities are left in the hour
                for (int i = 11; i <= 22; i++)
                {
                    int peoplePresent = dayMap.getOrDefault(i, 0);
                    int spotsLeft = maxCapacity - peoplePresent;
                    if (peoplePresent != 0)
                    {
                        if (spotsLeft > 0)
                        {
                            availabilitySummary += "<br>" + i + " o'clock" + ": Spots left " + spotsLeft;

                        }
                        else
                        {
                            availabilitySummary += "<br>Fully Booked ";
                        }
                    }
                    
                        
                }
            }
            
        availabilitySummary += "<html>";
        return availabilitySummary;
    }
    
}
