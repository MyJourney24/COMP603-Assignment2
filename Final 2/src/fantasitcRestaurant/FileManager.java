/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mered
 */
// manages the files and stores information
public interface FileManager {
    
    boolean savesUser(String username, String password); // txt writer 1
    String authenticateUser(String username, String password); // txt reader 1
    
    boolean savesBooking(String bookedName, String bookedTime); // txt writer 2
    
    HashMap<Integer, HashMap<Integer, Integer>> readBookings(); // txt reader 2
    
    String readUserBookings(String username); // txt reader 3
    
    
    String cancelBooking(String username, int bookingNumber);
    
    boolean savesOrder(Map<Food, Integer> preOrder); // txt writer 3
    
    String readsUserOrder(String username, int bookingNumber); // txt reader 4
       
}
