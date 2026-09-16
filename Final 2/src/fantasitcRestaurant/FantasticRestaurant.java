/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fantasitcRestaurant;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author mered
 */
public class FantasticRestaurant {

    public static void main(String[] args) {
        FileManagertxt fileManager = new FileManagertxt();
        Booking bookingFunction = new Booking();
        Order orderFunction = new Order();
        SignUpLogin singUpLoginFunction = new SignUpLogin();
        UserLogin userLoginFunction = new UserLogin(fileManager);
        
        Scanner mainUserInput = new Scanner(System.in);
        
        String activeUser = null; //name
        String bookedTime = null; //time
        Map<Food, Integer> preOrder = null; //order
        
        
        System.out.println("Welcome to Fantastic Restaurant \n");
 
        // main choices 
        boolean looping = true;
        while (looping)
        {
            try
            {
                System.out.println("""
                                   
                                   ============================================================
                                   
                                   0)Quit Application
                                   1)Order & Make a Booking
                                   2)View Order or Cancel Booking
                                   
                                   ============================================================
                                   
                                   To use the program type the corresponding number:
                                   """);

                int mainChoice = mainUserInput.nextInt();
                switch (mainChoice) {
                    case 0:
                        looping = false;
                        break;
                    case 1: 
                        boolean processComplete = false;
                        while (!processComplete)
                        {
                            preOrder = orderFunction.makeAnOrder(mainUserInput);
                            if (preOrder == null)
                            {
                                break;
                            }
                            bookedTime = bookingFunction.makeABooking(mainUserInput); // allows the user to make a booking than save it to bookedTime
                            if (bookedTime == null) // quiting the process will return a null restarting the process
                            {
                                System.out.println("Returning to Ordering process");
                                break;
                            }
                            if (activeUser == null)
                            {
                                activeUser = singUpLoginFunction.SignUpOrLogin(mainUserInput); // forces the user to sign / login to save pre-order or booking
                                break;
                            }
                        }
                        
                        
                        if (activeUser != null && bookedTime != null && preOrder != null)
                        {
                            // allows the booking to be saved even when their isn't a corrosponding order
                            if (!preOrder.isEmpty())
                            {
                                for (int i = 0; i < preOrder.size(); i++)
                                {
                                    fileManager.savesBooking(activeUser, bookedTime); 
                                    
                                }
                            }
                            else
                            {
                                fileManager.savesBooking(activeUser, bookedTime);
                            }
                            
                          
                            fileManager.savesOrder(preOrder);
                            
                            preOrder = null; // after saving these values will return to null to start the process again
                            bookedTime = null; 
                            
                            break;
                        }
                        else
                        {
                            System.out.println("Could not save:");
                            System.out.println(" activeUser: " + activeUser);
                            System.out.println(" bookedTime: " + bookedTime);
                            System.out.println(" preOrder: " + preOrder);
                        }
                        
                        break;
                    case 2:
                        // login
                        if (activeUser == null)
                        {
                            
                            System.out.println("""
                                    ============================================================
                                   
                                    Please login first
                                    Type 1 to begin logging           
                                    Type 0 to quit
                                         
                                    ============================================================
                                    """);
                            
                            int loginOrExit = mainUserInput.nextInt();
                            if (loginOrExit <= 0)
                            {
                                break;
                            }
                            else
                            {
                                activeUser = userLoginFunction.RunLogin(mainUserInput, fileManager); 
                                if (activeUser == null) {
                                    System.out.println("Returning to main menu...\n");
                                    break;
                                }
                            }   
                        }
                        // shows the bookings
                        fileManager.readUserBookings(activeUser);
                        // allows the user to choose to order or cancel booking
                        boolean canceling = true;
                        while (canceling)
                        {
                            System.out.println("""
                                    ============================================================
                                   
                                    1) View an Order
                                    2) Cancel an Order
                                    Type 0 or a negative value to quit
                                         
                                    ============================================================
                                    """);
                            int viewCancelExit = mainUserInput.nextInt();
                             
                            if (viewCancelExit <= 0)
                            {
                                break;
                            }
                            else if (viewCancelExit == 1)
                            {
                                //allows the user to view booking orders
                                System.out.println("""
                                    ============================================================
                                   
                                    If you'd like to view an Order
                                    just type the corresponding number
                                    Type 0 or a negative value to quit
                                         
                                    ============================================================
                                    """);
                                int viewOrExit = mainUserInput.nextInt();

                                if (viewOrExit <= 0) {
                                    break;
                                } else {
                                    System.out.println(fileManager.readsUserOrder(activeUser, viewOrExit));
                                }

                            }
                            else if (viewCancelExit == 2)
                            {
                                //allows user to cancel any booking
                                System.out.println("""
                                    ============================================================
                                   
                                    If you'd like to cancel a booking 
                                    just type the corresponding number
                                    Type 0 or a negative value to quit
                                         
                                    ============================================================
                                    """);
                                // allows the user to quit
                                int cancelOrExit = mainUserInput.nextInt();

                                if (cancelOrExit <= 0) {
                                    break;
                                } else {
                                    // cancels the order
                                    System.out.println(fileManager.cancelBooking(activeUser, cancelOrExit));
                                }
                                break;
                            }
                            break;
                        }
                        break;
                    default: 
                        System.out.println("Invalid input");
                }
            }
            catch (InputMismatchException e){
            System.out.println("Invalid input. Not an integer");
            mainUserInput.nextLine(); // catches the mismatch imput preventing a deathloop
            }
        } 
        System.out.println("We welcome you again next time");
    }  
}
