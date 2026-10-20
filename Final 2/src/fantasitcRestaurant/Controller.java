/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 *
 * @author mered
 */
public class Controller{
    private Model fantasticModel;
    private View fantasticView;
    
    public Controller(Model fantasticModel, View fantasticView)
    {
        this.fantasticModel = fantasticModel;
        this.fantasticView = fantasticView;
        
        //main menue buttons
        this.fantasticView.addOrderBookingActionListeners(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //switch to order and booking
                System.out.println("Order and booking \n");
                fantasticModel.switchToOrderBookingChoice();
            }
        });
        
        this.fantasticView.addViewCancleButtonActionListeners(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //switch to view cancel bookings (if logged in otherwise login)
                System.out.println("View cancel \n");
                fantasticModel.showBookings();
                fantasticModel.switchToViewCancel();
            }
        });
        
        //orderOrBooking buttons
        
        this.fantasticView.addOrderBookingBackButtonActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // returns to main menue
                System.out.println("back\n");
                fantasticModel.switchToMainMenue();
            }
        });
        
        this.fantasticView.addOrderBookingOrderButtonActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("order\n");
                fantasticModel.switchToDiet();
            }
        });
        
        this.fantasticView.addOrderBookingBookingButtonActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("booking \n");
                fantasticModel.switchToBooking();
            }
        });
        
        
        //diet menue buttons
        
        this.fantasticView.addVeganItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.vegan = " [x]";
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    System.out.println("Checkbox unchecked!");
                    fantasticModel.vegan = "";
                }
            }
        });
        
        this.fantasticView.addVegitarianItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.vegitarian = " [x]";
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    System.out.println("Checkbox unchecked!");
                    fantasticModel.vegitarian = "";
                }
            }
        });
        this.fantasticView.addGlutenFreeItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.glutenFree = " [x]";
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    System.out.println("Checkbox unchecked!");
                    fantasticModel.glutenFree = "";
                }
            }
        });
        this.fantasticView.addAlcoholFreeItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.alcoholFree = " [x]";
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    System.out.println("Checkbox unchecked!");
                    fantasticModel.alcoholFree = "";
                }
            }
        });
        
        
        
        this.fantasticView.addDietNextButtonActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("move to ordering \n");
                
                ArrayList<Food> yourfilteredOrder = fantasticModel.filteredMenu(fantasticModel.vegan, fantasticModel.vegitarian, fantasticModel.glutenFree, fantasticModel.alcoholFree);
                String yourfilteredOrdertxt = fantasticModel.displayFilteredMenu(yourfilteredOrder);
                
                fantasticModel.changeYourOrderDisplay(yourfilteredOrdertxt);
                
                fantasticModel.switchToOrder();
            }
        });
        
        this.fantasticView.addDietBackButtonActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("back to booking or ordering\n");
                fantasticModel.switchToOrderBookingChoice();
            }
        });
        
        // order menue buttons
        this.fantasticView.addMakeOrderAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("adds order\n");
                       
                int orderNum = fantasticView.getOrderNumber();
                int servingNum = fantasticView.getServingNumber();
                
                fantasticModel.addOrder(fantasticModel.tempSaveFiltered, orderNum, servingNum);
                
            }
        });
        this.fantasticView.addMakeOrderNextActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("finish ordering\n");
                fantasticModel.showOrder();
                fantasticModel.switchToSaveOrderChoice();
            }
        });
        this.fantasticView.addMakeOrderBackActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("back to diet filter\n");
                fantasticModel.switchToDiet();
            }
        });
        
        // save order choice buttons
        this.fantasticView.addSaveOrderBackSaveButtonActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("confirms order and moves to booking\n");
                fantasticModel.switchToBooking();
            }
        });
        
        this.fantasticView.addSaveOrderBackBackButtonActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("go bacck to ordering\n");
                fantasticModel.switchToOrder();
            }
        });
        
        // availability booking choice menue
        
        this.fantasticView.addAvailabilitiesBookingChoiceAvailabilitiesButtonActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the availabilities button\n");
                fantasticModel.switchToAvailabilitieView();
            }
        });
        
        this.fantasticView.addAvailabilitiesBookingChoiceBookingButtonActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the booking button\n");
                fantasticModel.switchToDaySelection();
            }
        });
        
        this.fantasticView.addAvailabilitiesBookingChoiceQuitButtonActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the back button \n");
                fantasticModel.switchToOrderBookingChoice();
            }
        });
        
        // availabilities buttons
        
        this.fantasticView.addAvailabilityReportNextButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Select day \n");
                fantasticModel.switchToDaySelection();
            }
        });
        
        this.fantasticView.addAvailabilityReportBackButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the back button \n");
                fantasticModel.switchToBooking();
            }
        });
        
        // day selection buttons
       
        
        this.fantasticView.addMondayItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingDay = 1;
                } 
            }
        });
        
        this.fantasticView.addTusedayItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingDay = 2;
                }
            }
        });
        
        this.fantasticView.addWednesdayItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingDay = 3;
                }
            }
        });
        
        this.fantasticView.addThursdayItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingDay = 4;
                }
            }
        });
        
        this.fantasticView.addFridayItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fantasticModel.bookingDay = 5;
                }
            }
        });
        
        this.fantasticView.addSaturdayItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingDay = 6;
                }
            }
        });
        
        
        
        this.fantasticView.addSelectDayNextButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Moving to select our \n");
                
                if (fantasticModel.bookingDay == 0)
                {
                    fantasticModel.missingDayError("please select a Day");
                }
                else
                {
                    fantasticModel.switchToHour();
                }
                
            }
        });
        
        this.fantasticView.addSelectDayBackButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the back button\n");
                fantasticModel.switchToBooking();
            }
        });
        
        // hour selection buttons
       
        
        this.fantasticView.addhour11ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingHour = 11;
                }
            }
        });
        
        this.fantasticView.addhour12ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingHour = 12;
                }
            }
        });
        
        this.fantasticView.addhour13ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingHour = 13;
                }
            }
        });
        
        this.fantasticView.addhour14ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingHour = 14;
                }
            }
        });
        
        this.fantasticView.addhour15ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingHour = 15;
                }
            }
        });
        
        this.fantasticView.addhour16ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingHour = 16;
                }
            }
        });
        
        this.fantasticView.addhour17ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingHour = 17;
                }
            }
        });
        
        this.fantasticView.addhour18ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingHour = 18;
                }
            }
        });
        
        this.fantasticView.addhour19ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingHour = 19;
                }
            }
        });
        
        this.fantasticView.addhour20ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingHour = 20;
                }
            }
        });
        
        this.fantasticView.addhour21ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingHour = 21;
                }
            }
        });
        
        this.fantasticView.addhour22ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingHour = 22;
                }
            }
        });
        
        this.fantasticView.addSelectHourNextButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Moving to select people\n");
                if (fantasticModel.bookingHour == 0)
                {
                    fantasticModel.missingDayError("please select a Hour");
                }
                else
                {
                    fantasticModel.switchToPeopleSelection();
                }
            }
        });
        
        this.fantasticView.addSelectHourBackButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the back button\n");
                fantasticModel.switchToDaySelection();
            }
        });
        
        // people selection buttons
        
        this.fantasticView.addSelectPeopleNextButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Moving to select duration \n");
                int peoplePresent = fantasticView.getPeopleCount();
                
                if (peoplePresent < 1)
                {
                    fantasticModel.missingPeopleError("Please input number of people");
                }
                else
                {
                    fantasticModel.bookingPeople = peoplePresent;
                    fantasticModel.switchToDurationSelection();
                }
            }
        });
        
        this.fantasticView.addSelectPeopleBackButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the back button");
                fantasticModel.switchToHour();
            }
        });
        
        // duration selection buttons
        
        this.fantasticView.addDuration1ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingTime = 1;
                }
            }
        });
        
        this.fantasticView.addDuration2ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingTime = 2;
                }
            }
        });
        
        this.fantasticView.addDuration3ItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox checked!");
                    fantasticModel.bookingTime = 3;
                }
            }
        });
        
        
        this.fantasticView.addSelectDurationNextButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Move to check availability\n");
                if (fantasticModel.bookingTime == 0)
                {
                    fantasticModel.missingPeopleError("Please input duration of stay");
                }
                else
                {
                    fantasticModel.showAvailability();
                    fantasticModel.switchToAvailableOrNot();
                }
            }
        });
        
        this.fantasticView.addSelectDurationBackButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the back button");
                fantasticModel.switchToPeopleSelection();
            }
        });
        
        // available or not button
        
        this.fantasticView.addavailableOrNotNextButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Switch to confirm order\n");
                 
                if (fantasticModel.yourBookingAvailability == "unavailable")
                {
                    fantasticModel.switchToBooking();
                }
                else
                {
                    if (fantasticModel.currentUser == null)
                    {
                        fantasticModel.switchToLoginSignup();
                    }
                    else
                    {
                        fantasticModel.switchToConfirmOrder();
                    }
                }
            }
        });
        
        // login or signup buttons
        
        this.fantasticView.addSignupOrLoginLoginButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("move to login\n");
                 
                fantasticModel.switchToLogin();
                
            }
        });
        
        this.fantasticView.addSignupOrLoginSignUpButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("move to signup \n");
                 
                fantasticModel.switchToSignup();
            }
        });
        
        this.fantasticView.addSignupOrLoginBackButton(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the back button\n");
                 
                fantasticModel.switchToDurationSelection();
            }
        });
        
        
        
        // login buttons
        this.fantasticView.addLoginButtonListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("login confirmed\n");
                 
                
                String authenticateUsername = fantasticView.getLoginUnText();
                String authenticatePassword = fantasticView.getLoginPwText();

                boolean loginSuccess = fantasticModel.authenticateUser(authenticateUsername, authenticatePassword);
                if (loginSuccess) {
                    fantasticModel.showOrderDetailsToBeConfirmed();
                    if (fantasticModel.fromView == false)
                    {
                        fantasticModel.switchToConfirmOrder();
                    }
                    else 
                    {
                        //to view
                        fantasticModel.showBookings();
                        fantasticModel.switchToViewBooking();
                    }
                }
                
            }
        });
        
        this.fantasticView.addLoginBackButtonListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("back to main menue\n");
                 
                fantasticModel.switchToMainMenue();
            }
        });
        
    
        // sign up buttons
        this.fantasticView.addSignupButtonListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sign in\n");
                 
                
                String newUsername = fantasticView.getLoginUnText();
                String newPassword = fantasticView.getLoginPwText();

                boolean signUpSuccess = fantasticModel.saveNewUser(newUsername, newPassword);
                if (signUpSuccess) {
                    fantasticModel.switchToConfirmOrder();
                }
                
                
            }
        });
        
        this.fantasticView.addSignupBackButtonListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the back button");
                fantasticModel.switchToMainMenue();
            }
        });
        
        // save order buttons
        
        this.fantasticView.addconfirmDetailsYesListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("order Saved \n");
                 
                fantasticModel.saveOrder();
                fantasticModel.switchToMainMenue();
            }
        });
        
        this.fantasticView.addconfirmDetailsNoListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the back button\n");
                 
                fantasticModel.switchToDurationSelection();
            }
        });
        
        // view and cancel
        
        this.fantasticView.addviewBookingViewOrderListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int orderNumber = fantasticView.getviewBookingViewTF();
                
                System.out.println("view order" + orderNumber + "\n");
                
                if (orderNumber > fantasticModel.totalBookings )
                {
                    fantasticModel.viewCancelError("Please input a number that corresponds to a booking above");
                }
                else if (orderNumber <= 0)
                {
                    fantasticModel.viewCancelError("Please input a number greater than 0");
                }
                else
                {
                    fantasticModel.showUserOrder(orderNumber);
                    fantasticModel.switchToViewOrderDisplay();
                }
            }
        });
        
        this.fantasticView.addviewBookingCancelBookingistener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int orderNumber = fantasticView.getviewBookingCancelTF();
                
                System.out.println("Cancel order " + orderNumber + "\n");
                
                if (orderNumber > fantasticModel.totalBookings )
                {
                    fantasticModel.viewCancelError("Please input a number that corresponds to a booking above");
                }
                else if (orderNumber <= 0)
                {
                    fantasticModel.viewCancelError("Please input a number greater than 0");
                }
                else
                {
                    fantasticModel.cancelBooking(orderNumber);
                }
            }
        });
        
        this.fantasticView.addviewBookingBackListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the back button\n");
                fantasticModel.switchToMainMenue();
            }
        });
        
        //view order
        
        this.fantasticView.addviewOrderBackListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the back button\n");
                fantasticModel.switchToViewBooking();
            }
        });
    }
}
