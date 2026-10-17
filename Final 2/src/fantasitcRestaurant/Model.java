/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

import static fantasitcRestaurant.Order.displayFilteredMenu;
import static fantasitcRestaurant.Order.orderSummary;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author mered
 */
public class Model {
    
    private FileManagertxt fileManager;
    private ModelListener listener;
    private View view;
    
    
    public Model(View view, FileManagertxt fileManager) {
        // initialisation
        this.fileManager = fileManager;
        this.orderItems = new HashMap<>();
        this.bookedTime = fileManager.readBookings();
        
        System.out.println("Model()");
    }
    
    public void addListener(ModelListener listener) {
        this.listener = listener;
    }
    
    //switch to
    
    public void switchToMainMenue()
    {
        listener.switchTo(0);
    }
    
    public void switchToOrderBookingChoice()
    {
        fromView = false;
        listener.switchTo(1);
    }
    
    public void switchToViewCancel()
    {
        fromView = true;
        if (currentUser == null)
        {
            listener.switchTo(13);
        }
        else
        {
            listener.switchTo(2);
        }
        
    }
    
    public void switchToDiet()
    {
        listener.switchTo(3);
    }
    
    public void switchToBooking()
    {
        listener.switchTo(4);
    }
    
    public void switchToOrder()
    {
        listener.switchTo(5);
    }
    
    public void switchToSaveOrderChoice()
    {
        listener.switchTo(6);
    }
    
    public void switchToDaySelection()
    {
        listener.switchTo(7);
    }
    
    public void switchToAvailabilitieView()
    {
        showAvailabilities();
        listener.switchTo(8);
    }
    
    public void switchToHour()
    {
        listener.switchTo(9);
    }
    
    public void switchToPeopleSelection()
    {
        listener.switchTo(10);
    }
    
    public void switchToDurationSelection()
    {
        listener.switchTo(11);
    }
    
    public void switchToAvailableOrNot()
    {
        listener.switchTo(15);
    }
    
    public void switchToLoginSignup()
    {
        listener.switchTo(12);
    }
    
    public void switchToLogin()
    {
        listener.switchTo(13);
    }
    
    public void switchToSignup()
    {
        listener.switchTo(14);
    }
    
    public void switchToConfirmOrder()
    {
        listener.switchTo(16);
    }
    
    public void switchToViewBooking()
    {
        listener.switchTo(17);
    }
    
    public void switchToViewOrderDisplay()
    {
        listener.switchTo(18);
    }
    //change display 
    
    public void changeYourOrderDisplay(String txt)
    {
        listener.changetxtDisplay(1, txt);
    }
    
    public void orderDetailsChangingDisplay(String txt)
    {
        listener.changetxtDisplay(2, txt);
    }
    
    public void changeOrderSummaryDisplay(String txt)
    {
        listener.changetxtDisplay(3, txt);
    }
    
    public void changeAvailabilitySummrayDisplay(String txt)
    {
        listener.changetxtDisplay(4, txt);
    }
    
    public void missingDayError(String txt)
    {
        listener.changetxtDisplay(5, txt);
    }
    
    public void missingHourError(String txt)
    {
        listener.changetxtDisplay(6, txt);
    }
    
    public void missingPeopleError(String txt)
    {
        listener.changetxtDisplay(7, txt);
    }
    
    public void missingDurationError(String txt)
    {
        listener.changetxtDisplay(8, txt);
    }
    
    public void changeAvailabilityDisplay(String txt)
    {
        listener.changetxtDisplay(9, txt);
    }
    
    public void changeConfirmDetailsDetailsDisplay(String txt)
    {
        listener.changetxtDisplay(10, txt);
    }
    
    public void changeViewBookingDetails(String txt)
    {
        listener.changetxtDisplay(11, txt);
    }
    
    public void viewCancelError(String txt)
    {
        listener.changetxtDisplay(12, txt);
    }
    
    public void changeviewOrderDetails(String txt)
    {
        listener.changetxtDisplay(13,txt);
    }
            
    //functional model
    
    public boolean fromView = false;
    
    //filter diet requirements
    public String vegan = "";
    public String vegitarian = "";
    public String glutenFree = "";
    public String alcoholFree = "";
    
    public static ArrayList<Food> tempSaveFiltered;
    
    
    public static ArrayList<Food> filteredMenu(String vegan, String vegitarian, String glutenFree, String alcoholFree)
    {
        Menue m1 = new Menue();
        ArrayList<Food> fullMenu = m1.getFullMenu();
        ArrayList<Food> filtered = new ArrayList<>();
        
        for (Food theFood : fullMenu)
        {
            // if they have a dietary condition and the food is unsuitable 
            if (
                    (!"".equals(vegan) && !theFood.isVegan()) || 
                    (!"".equals(vegitarian) && !theFood.isVegitarian()) ||
                    (!"".equals(glutenFree) && !theFood.isGlutenFree()) ||
                    (!"".equals(alcoholFree) && theFood.alcoholLevel() != 0)
                ) 
            {
                //do nothing
            }
            else
            {
               filtered.add(theFood);
            }
        }
        System.out.print("filter successfull");
        
        tempSaveFiltered = filtered;
        return filtered;
    }
    
    public static String displayFilteredMenu(ArrayList<Food> thefilteredList) 
    {
        // this is default menue
        if (thefilteredList.isEmpty())
        {
            return "sorry nothing matches your requirements please go back";
        }
        
        String yourOrdertxt = "<html>Your order ";
        
        
        for (int i = 0; i < thefilteredList.size(); i++) {
            Food item = thefilteredList.get(i);
            yourOrdertxt += "<br>" + (i + 1) + ") " + item.getFoodName() + " " + item.getPrice();
        }
        
        yourOrdertxt += "<br><html>";
        
        return yourOrdertxt;
    }
    
    // order
    
    private HashMap<Food, Integer> orderItems;
    
    public void addOrder(ArrayList<Food> thefilteredList, int itemChoice, int servingNumber)
    {
        if(itemChoice < 1 || itemChoice > thefilteredList.size() || servingNumber < 0)
                {
                    System.out.println("Sorry that isn't an option");
                    orderDetailsChangingDisplay("Sorry that isn't an option please input a positive number");
                }
        else
        {
            Food selectedFood = thefilteredList.get(itemChoice - 1);
            orderItems.put(selectedFood, orderItems.getOrDefault(selectedFood, 0) + servingNumber);
            orderDetailsChangingDisplay("Order added");
        }
    }
    
    public void showOrder()
    {
        String orderString = orderSummary(orderItems);
        changeOrderSummaryDisplay(orderString);
    }
    
    
    public static String orderSummary(HashMap<Food, Integer> yourOrder)
    {
        String yourOrderSummaryString = "<html> Your Order Summary";
        
        double totalCost = 0.0;
        
        if (yourOrder.isEmpty())
        {
            yourOrderSummaryString += "<br> No items ordered <html>";
            return yourOrderSummaryString;
        }
        else
        {
            for (Map.Entry<Food, Integer> entry : yourOrder.entrySet())
            {
                Food food = entry.getKey();
                int servings = entry.getValue();
                double itemTotalCost = food.getPrice() * servings;
                totalCost += itemTotalCost;
                
                yourOrderSummaryString += "<br>" + servings + " " + food.getFoodName() + " " + itemTotalCost;
            }
        }
        
        yourOrderSummaryString += "<br><br> TOTAL: $" + totalCost;
        return yourOrderSummaryString;
    }
    
    
    
    int maxCapacity = 10;
    private final HashMap<Integer, HashMap<Integer, Integer>> bookedTime;
    
    public void showAvailabilities()
    {
        String availabilitySummaryTxt = checkAvailability(bookedTime);
        listener.changetxtDisplay(4, availabilitySummaryTxt);
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
    
    int bookingDay = 0;
    int bookingHour = 0;
    int bookingPeople = 0;
    int bookingTime = 0;
    
    public static String fullBooking = "";
    public static String yourBookingAvailability;
    
    public void showAvailability()
    {
        yourBookingAvailability = isAvailable(bookedTime, bookingDay, bookingHour, bookingPeople, bookingTime);
        
        changeAvailabilityDisplay(yourBookingAvailability);
    }
    
    public static String isAvailable(HashMap<Integer, HashMap<Integer,Integer>> bookedTime, int bookingDay, int bookingHour, int bookingPeople, int bookingTime) // confirms that their booking is available
    {

        String onDisplay = "";
            HashMap<Integer, Integer> theTimeMap = bookedTime.getOrDefault(bookingDay, new HashMap<>());

            for (int i = 0; i < bookingTime; i++)
            {
                int peopleCount = theTimeMap.getOrDefault(bookingHour + i, 0);
                if ((peopleCount + bookingPeople) > 10)
                {
                    fullBooking = null;
                    onDisplay += "unavailable";
                    return onDisplay;
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
        
        onDisplay += "<html> booking available <br> Day " + bookingDay + "<br> Hour " + bookingHour + "<br> People " + bookingPeople + "<br> Time " + bookingTime + "<html>";
        
        fullBooking = bookingDay + ","+ bookingHour + "," + bookingPeople + "," + bookingTime;
        return onDisplay;
    }
    
    
    
    public static String currentUser;
    
    // login 
    public boolean authenticateUser(String newUsername, String newPassword)
    {
        
        String loginName = fileManager.authenticateUser(newUsername, newPassword);
        
        if(!loginName.equals(null))
        {
            currentUser = loginName;
            return true;
        }
        
        return false;
    }
    
    //sign in
    
    public boolean saveNewUser(String newUsername, String newPassword)
    {
        
        String signUpName = fileManager.savesUser(newUsername, newPassword);
        
        if(!signUpName.equals(null))
        {
            currentUser = signUpName;
            return true;
        }
        
        return false;
    }
    
    // show confrim details show
    
    public void showOrderDetailsToBeConfirmed()
    {
        String totalDisplay = "";
        
        totalDisplay += "<html>Username: " + currentUser;
        totalDisplay += "<br>" + isAvailable(bookedTime, bookingDay, bookingHour, bookingPeople, bookingTime);
        totalDisplay += "<br> " + orderSummary(orderItems);
        
        changeConfirmDetailsDetailsDisplay(totalDisplay);
    }
    
    // save details
    
    public void saveOrder()
    {
        System.out.println("1");
        String bookingString = bookingDay + "," + bookingHour + "," + bookingPeople + "," + bookingTime;
        System.out.println(bookingString);
        Integer bookingID = fileManager.savesBooking(currentUser, bookingString);
        System.out.println("3");
        fileManager.savesOrder(orderItems, bookingID);
        System.out.println("4");
        System.out.println(currentUser + " " + bookingString + " " + orderItems + " " + bookingID);
    }
    
    // view cancel functions
    int totalBookings;
    
    public void showBookings()
    {
        String currentUserBookings = fileManager.readUserBookings(currentUser);
        totalBookings = fileManager.countUserBookings(currentUser);
        
        System.out.println(currentUserBookings);
        changeViewBookingDetails(currentUserBookings);
    }
    
    public void showUserOrder(int bookingNum)
    {
        String currentUserOrder = fileManager.readsUserOrder(currentUser, bookingNum);
        
        System.out.println(currentUserOrder);
        changeviewOrderDetails(currentUserOrder);
    }
    
    public void cancelBooking(int bookingNum)
    {
        String cancledBooking = fileManager.cancelBooking(currentUser, bookingNum);
        System.out.print(cancledBooking);
        
        String currentUserBookings = fileManager.readUserBookings(currentUser);
        System.out.println(currentUserBookings);
        
        changeViewBookingDetails(currentUserBookings);
    }
}
