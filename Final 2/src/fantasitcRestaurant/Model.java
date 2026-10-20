/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mered
 */
public class Model {
    
    private FileManagerdbs fileManager;
    private Order orderingFunctions;
    private Booking bookingFunctions;
    private ModelListener listener;
    private View view;
    
    
    public Model(View view, FileManagerdbs fileManager) {
        // initialisation
        this.fileManager = fileManager;
        this.orderingFunctions = new Order();
        this.bookingFunctions = new Booking(fileManager);
        
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
    
    public ArrayList<Food> filteredMenu(String vegan, String vegitarian, String glutenFree, String alcoholFree)
    {
        ArrayList<Food> filtered = new ArrayList<>();
        filtered = orderingFunctions.filteredMenu(vegan, vegitarian, glutenFree, alcoholFree);
        return filtered;
    }
    
    public String displayFilteredMenu(ArrayList<Food> thefilteredList) 
    {
        return orderingFunctions.displayFilteredMenue(thefilteredList);
    }
    
    // order
    
    private HashMap<Food, Integer> orderItems;
    
    public HashMap<Food, Integer> getOrderItems()
    {
        return orderItems;
    }
    
    public void addOrder(ArrayList<Food> thefilteredList, int itemChoice, int servingNumber)
    {
        if(itemChoice < 1 || itemChoice > thefilteredList.size() || servingNumber < 1)
                {
                    System.out.println("Sorry that isn't an option");
                    orderDetailsChangingDisplay("Sorry that isn't an option please input a valid number");
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
    
    
    public String orderSummary(HashMap<Food, Integer> yourOrder)
    {
        return orderingFunctions.orderSummary(yourOrder);
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
        return bookingFunctions.checkAvailability(bookedTime);
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
            
            if(bookingPeople <1)
            {
                onDisplay += "unavailable";
                return onDisplay;
            }

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
        onDisplay += "<html> booking available <br> Day " + bookingDay + "<br> Hour " + bookingHour + "<br> People " + bookingPeople + "<br> Time " + bookingTime + "<html>";
        
        fullBooking = bookingDay + ","+ bookingHour + "," + bookingPeople + "," + bookingTime;
        return onDisplay;
    }
    
    
    
    public String currentUser;
    
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
        String bookingString = bookingDay + "," + bookingHour + "," + bookingPeople + "," + bookingTime;
        Integer bookingID = fileManager.savesBooking(currentUser, bookingString);
        fileManager.savesOrder(orderItems, bookingID);
    }
    
    // view cancel functions
    int totalBookings;
    
    public void showBookings()
    {
        String currentUserBookings = fileManager.readUserBookings(currentUser);
        totalBookings = fileManager.countUserBookings(currentUser);
        
        changeViewBookingDetails(currentUserBookings);
    }
    
    public void showUserOrder(int bookingNum)
    {
        String currentUserOrder = fileManager.readsUserOrder(currentUser, bookingNum);
        
        changeviewOrderDetails(currentUserOrder);
    }
    
    public void cancelBooking(int bookingNum)
    {
        String cancledBooking = fileManager.cancelBooking(currentUser, bookingNum);
        System.out.print(cancledBooking);
        
        String currentUserBookings = fileManager.readUserBookings(currentUser);
        
        changeViewBookingDetails(currentUserBookings);
    }
}
