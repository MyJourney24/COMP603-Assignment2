/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author mered
 */
public class View extends JFrame{
    
    //initialisation
    public View() {
        System.out.println("mainMenueView()");
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        loginView();
    }
    
    public void layoutConfiguration(JPanel panel)
    {
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
    }
    
    // main menue view
    private JPanel mainMenue = new JPanel();
    private JLabel nameLabel = new JLabel("Welcome to Fantastic Resturaunt");
    private JButton orderBookingButton = new JButton("Order & Make a Booking");
    private JButton viewCancleButton = new JButton("View Order or Cancel Booking");
    private Dimension standardButtonSize = new Dimension(250, 40);
    
    public void mainMenueView()
    {
        layoutConfiguration(mainMenue);
        
        nameLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        orderBookingButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        viewCancleButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        orderBookingButton.setPreferredSize(standardButtonSize);
        viewCancleButton.setPreferredSize(standardButtonSize);
        orderBookingButton.setMaximumSize(standardButtonSize);
        viewCancleButton.setMaximumSize(standardButtonSize);
        
        mainMenue.add(javax.swing.Box.createVerticalStrut(20));
        mainMenue.add(nameLabel);
        mainMenue.add(javax.swing.Box.createVerticalStrut(20));
        mainMenue.add(orderBookingButton);
        mainMenue.add(javax.swing.Box.createVerticalStrut(10));
        mainMenue.add(viewCancleButton);
        
        this.add(mainMenue); 
    }
    
    public void addOrderBookingActionListeners(ActionListener listener)
    {
        orderBookingButton.addActionListener(listener);
    }
    
    public void addViewCancleButtonActionListeners(ActionListener listener)
    {
        viewCancleButton.addActionListener(listener);
    }
    
    //dietary panel
    private JPanel dietMenue = new JPanel();
    private JPanel checkBoxPanel = new JPanel();
    
    
    private JLabel dietDiscription  = new JLabel("Dietary Requirements Please select your dietary requirements");
    private JLabel dietinstrctions  = new JLabel("Please select your dietary requirements");
    private JCheckBox isVegan = new JCheckBox("Vegan");
    private JCheckBox isVegitarian = new JCheckBox("Vegitarian");
    private JCheckBox isGlutenFree = new JCheckBox("Gluten Free");
    private JCheckBox isAlcoholFree = new JCheckBox("Alcohol Free");
    private JButton dietNextButton = new JButton("Next");
    
    public void dietMenueView()
    {
        layoutConfiguration(dietMenue);
        layoutConfiguration(checkBoxPanel);
        
        checkBoxPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        checkBoxPanel.setMaximumSize(new Dimension(150, 140)); 
        checkBoxPanel.setBackground(new Color(218, 232, 252));
        
        isVegan.setOpaque(false);
        isVegitarian.setOpaque(false);    
        isGlutenFree.setOpaque(false);
        isAlcoholFree.setOpaque(false);
        dietNextButton.setOpaque(false);
        
        dietDiscription.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        dietinstrctions.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        checkBoxPanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        isVegan.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        isVegitarian.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);        
        isGlutenFree.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        isAlcoholFree.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);       
        dietNextButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        dietMenue.add(javax.swing.Box.createVerticalStrut(20));
        dietMenue.add(dietDiscription);
        dietMenue.add(javax.swing.Box.createVerticalStrut(5));
        dietMenue.add(dietinstrctions);
        dietMenue.add(javax.swing.Box.createVerticalStrut(10));
        dietMenue.add(checkBoxPanel);
            checkBoxPanel.add(javax.swing.Box.createVerticalStrut(10));
            checkBoxPanel.add(isVegan);
            checkBoxPanel.add(javax.swing.Box.createVerticalStrut(5));
            checkBoxPanel.add(isVegitarian);
            checkBoxPanel.add(javax.swing.Box.createVerticalStrut(5));
            checkBoxPanel.add(isGlutenFree);
            checkBoxPanel.add(javax.swing.Box.createVerticalStrut(5));
            checkBoxPanel.add(isAlcoholFree);
        dietMenue.add(javax.swing.Box.createVerticalStrut(10));
        dietMenue.add(dietNextButton);
        this.add(dietMenue);
    }
    
    public void addVeganItemListener(ItemListener listener)
    {
        isVegan.addItemListener(listener);
    }
    
    public void addVegitarianItemListener(ItemListener listener)
    {
        isVegitarian.addItemListener(listener);
    }
    
    public void addGlutenFreeItemListener(ItemListener listener)
    {
        isGlutenFree.addItemListener(listener);
    }
    
    public void addAlcoholFreeItemListener(ItemListener listener)
    {
        isAlcoholFree.addItemListener(listener);
    }
    
    public void addDietNextButtonActionListener(ActionListener listener)
    {
        dietNextButton.addActionListener(listener);
    }
    
    //order or book panel
    private JPanel orderBookingChoice = new JPanel();
    private JLabel orderBookingChoiceLabel = new JLabel("Would you like to pre-order or order on site and skip to booking");
    private JButton orderBookingBackButton = new JButton("Back");
    private JButton orderBookingOrderButton = new JButton("Pre-Order");
    private JButton orderBookingBookingButton = new JButton("Go to Booking");
    
    public void orderBookingChoiceMenueView()
    {
        layoutConfiguration(orderBookingChoice);
        
        orderBookingChoiceLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        orderBookingBackButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        orderBookingOrderButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        orderBookingBookingButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        orderBookingBackButton.setPreferredSize(standardButtonSize);
        orderBookingOrderButton.setPreferredSize(standardButtonSize);
        orderBookingBookingButton.setPreferredSize(standardButtonSize);
        
        orderBookingBackButton.setMaximumSize(standardButtonSize);
        orderBookingOrderButton.setMaximumSize(standardButtonSize);
        orderBookingBookingButton.setMaximumSize(standardButtonSize);
        
        orderBookingChoice.add(javax.swing.Box.createVerticalStrut(20));
        orderBookingChoice.add(orderBookingChoiceLabel);
        orderBookingChoice.add(javax.swing.Box.createVerticalStrut(20));
        orderBookingChoice.add(orderBookingBackButton);
        orderBookingChoice.add(javax.swing.Box.createVerticalStrut(10));
        orderBookingChoice.add(orderBookingOrderButton);
        orderBookingChoice.add(javax.swing.Box.createVerticalStrut(10));
        orderBookingChoice.add(orderBookingBookingButton);
        
        this.add(orderBookingChoice); 
    }
    
    public void addOrderBookingBackButtonActionListener(ActionListener listener)
    {
        orderBookingBackButton.addActionListener(listener);
    }
    
    public void addOrderBookingOrderButtonActionListener(ActionListener listener)
    {
        orderBookingOrderButton.addActionListener(listener);
    }
    
    public void addOrderBookingBookingButtonActionListener(ActionListener listener)
    {
        orderBookingBookingButton.addActionListener(listener);
    }
    
    // save order or back
    private JPanel saveOrderBack = new JPanel();
    private JPanel yourOrderSummary = new JPanel();
    
    private JLabel yourOrderSummaryDescription = new JLabel("your order");
    private JLabel saveOrderBackLable = new JLabel("Would you like to save this order or go back");
    private JButton saveOrderBackSaveButton = new JButton("Save Order");
    private JButton saveOrderBackBackButton = new JButton("Go Back");
    
    public void saveOrderBackMenueView()
    {
        layoutConfiguration(saveOrderBack);
        
        yourOrderSummary.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        saveOrderBackLable.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        saveOrderBackSaveButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        saveOrderBackBackButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        saveOrderBackSaveButton.setPreferredSize(standardButtonSize);
        saveOrderBackSaveButton.setMaximumSize(standardButtonSize);
                
        saveOrderBackBackButton.setPreferredSize(standardButtonSize);
        saveOrderBackBackButton.setMaximumSize(standardButtonSize);  
        
        saveOrderBack.add(javax.swing.Box.createVerticalStrut(20));
        saveOrderBack.add(yourOrderSummary);
            yourOrderSummary.add(javax.swing.Box.createVerticalStrut(5));
            yourOrderSummary.add(yourOrderSummaryDescription);
            yourOrderSummary.add(javax.swing.Box.createVerticalStrut(5));
        saveOrderBack.add(saveOrderBackLable);
        saveOrderBack.add(javax.swing.Box.createVerticalStrut(20));
        saveOrderBack.add(saveOrderBackSaveButton);
        saveOrderBack.add(javax.swing.Box.createVerticalStrut(10));
        saveOrderBack.add(saveOrderBackBackButton);
        
        yourOrderSummary.setMaximumSize(new java.awt.Dimension(400, yourOrderSummary.getPreferredSize().height));
        
        this.add(saveOrderBack);
    }
    
    public void addSaveOrderBackSaveButtonActionListener(ActionListener listener)
    {
        saveOrderBackSaveButton.addActionListener(listener);
    }
    
    public void addSaveOrderBackBackButtonActionListener(ActionListener listener)
    {
        saveOrderBackBackButton.addActionListener(listener);
    }
    
    //make order
    private JPanel makeOrder = new JPanel();
    private JPanel filteredMenue = new JPanel();
    private JPanel selectionPanel = new JPanel();
    
    private JLabel filterendMenuDisplay = new JLabel("test");
    private JTextField orderNumber = new JTextField(2);
    private JTextField servingNumber = new JTextField(2);
    private JButton makeOrderAdd = new JButton("add to Order");
    private JButton makeOrderNext = new JButton("Next");
    private JButton makeOrderBack = new JButton("Back");
    
    public void makeOrderMenueView()
    {
        layoutConfiguration(makeOrder);
        
        
        selectionPanel.setMaximumSize(new java.awt.Dimension(400, 60));
        
        filteredMenue.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        filterendMenuDisplay.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        selectionPanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        makeOrderNext.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        makeOrderBack.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        makeOrderNext.setPreferredSize(standardButtonSize);
        makeOrderNext.setMaximumSize(standardButtonSize);
                
        makeOrderBack.setPreferredSize(standardButtonSize);
        makeOrderBack.setMaximumSize(standardButtonSize);  
        
        makeOrder.add(javax.swing.Box.createVerticalStrut(20));
        makeOrder.add(filteredMenue);
            filteredMenue.add(javax.swing.Box.createVerticalStrut(5));
            filteredMenue.add(filterendMenuDisplay);
            filteredMenue.add(javax.swing.Box.createVerticalStrut(5));
        makeOrder.add(selectionPanel);
            selectionPanel.add(orderNumber);
            selectionPanel.add(servingNumber);
            selectionPanel.add(makeOrderAdd);
        makeOrder.add(javax.swing.Box.createVerticalStrut(5));
        makeOrder.add(makeOrderNext);
        makeOrder.add(javax.swing.Box.createVerticalStrut(5));
        makeOrder.add(makeOrderBack);  
        
        filteredMenue.setMaximumSize(new java.awt.Dimension(400, filteredMenue.getPreferredSize().height));
        
        this.add(makeOrder);
    }
    
    public void addMakeOrderAddActionListener(ActionListener listener)
    {
        makeOrderAdd.addActionListener(listener);
    }
    
    public void addMakeOrderNextActionListener(ActionListener listener)
    {
        makeOrderNext.addActionListener(listener);
    }
    
    public void addMakeOrderBackActionListener(ActionListener listener)
    {
        makeOrderBack.addActionListener(listener);
    }
    
    public String getOrderNumber()
    {
        return orderNumber.getText();
    }
    
    public String getServingNumber()
    {
        return servingNumber.getText();
    }
    
    //make booking
    private JPanel availabilitiesBookingChoice = new JPanel();
    
    private JLabel availabilitiesBookingChoiceDiscription = new JLabel("(Warning quiting will restart the ordering process)");
    private JButton availabilitiesBookingChoiceAvailabilitiesButton = new JButton("Check Availabilities");
    private JButton availabilitiesBookingChoiceBookingButton = new JButton("Make Booking");
    private JButton availabilitiesBookingChoiceQuitButton = new JButton("Quit");
    
    public void availabilitiesBookingChoiceMenueView()
    {
        layoutConfiguration(availabilitiesBookingChoice);
        
        availabilitiesBookingChoiceDiscription.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        availabilitiesBookingChoiceAvailabilitiesButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        availabilitiesBookingChoiceBookingButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        availabilitiesBookingChoiceQuitButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        availabilitiesBookingChoiceAvailabilitiesButton.setPreferredSize(standardButtonSize);
        availabilitiesBookingChoiceAvailabilitiesButton.setMaximumSize(standardButtonSize);
        
        availabilitiesBookingChoiceBookingButton.setPreferredSize(standardButtonSize);
        availabilitiesBookingChoiceBookingButton.setMaximumSize(standardButtonSize);
        
        availabilitiesBookingChoiceQuitButton.setPreferredSize(standardButtonSize);
        availabilitiesBookingChoiceQuitButton.setMaximumSize(standardButtonSize);
        
        availabilitiesBookingChoice.add(javax.swing.Box.createVerticalStrut(20));
        availabilitiesBookingChoice.add(availabilitiesBookingChoiceDiscription);
        availabilitiesBookingChoice.add(javax.swing.Box.createVerticalStrut(20));
        availabilitiesBookingChoice.add(availabilitiesBookingChoiceAvailabilitiesButton);
        availabilitiesBookingChoice.add(javax.swing.Box.createVerticalStrut(10));
        availabilitiesBookingChoice.add(availabilitiesBookingChoiceBookingButton);
        availabilitiesBookingChoice.add(javax.swing.Box.createVerticalStrut(10));
        availabilitiesBookingChoice.add(availabilitiesBookingChoiceQuitButton);
        
        this.add(availabilitiesBookingChoice); 
    }
    
    public void addAvailabilitiesBookingChoiceAvailabilitiesButtonActionListener(ActionListener listener)
    {
        availabilitiesBookingChoiceAvailabilitiesButton.addActionListener(listener);
    }
    
    public void addAvailabilitiesBookingChoiceBookingButtonActionListener(ActionListener listener)
    {
        availabilitiesBookingChoiceBookingButton.addActionListener(listener);
    }
    
    public void addAvailabilitiesBookingChoiceQuitButtonActionListener(ActionListener listener)
    {
        availabilitiesBookingChoiceQuitButton.addActionListener(listener);
    }
    
    // check availabilities
    
    private JPanel availabilityReport = new JPanel();
    private JLabel availabilityReportLabel = new JLabel("Test");
    private JButton availabilityReportNextButton = new JButton("Next");
    
    
    public void availabilityReportMenueView()
    {
        layoutConfiguration(availabilityReport);
        
        availabilityReportLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        availabilityReportNextButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        availabilityReportNextButton.setPreferredSize(standardButtonSize);
        availabilityReportNextButton.setMaximumSize(standardButtonSize);
        
        availabilityReport.add(javax.swing.Box.createVerticalStrut(20));
        availabilityReport.add(availabilityReportLabel);
        availabilityReport.add(javax.swing.Box.createVerticalStrut(20));
        availabilityReport.add(availabilityReportNextButton);
        
        this.add(availabilityReport); 
    }
    
    //make booking day menue view
    private JPanel selectDay = new JPanel();
    
    private JLabel selectDayLabel = new JLabel("Select the day");
    private JButton selectDayNextButton = new JButton("Next");
    private JButton selectDayBackButton = new JButton("Back");
    
    //day button group
    private ButtonGroup groupDay = new ButtonGroup();
    
    private JCheckBox MondayButton = new JCheckBox("Monday");
    private JCheckBox TusedayButton = new JCheckBox("Tuseday");
    private JCheckBox WednesdayButton = new JCheckBox("Wednesday");
    private JCheckBox ThursdayButton = new JCheckBox("Thursday");
    private JCheckBox FridayButton = new JCheckBox("Friday");
    private JCheckBox SaturdayButton = new JCheckBox("Saturday");
    
    
    public void selectDayMenueView()
    {
        layoutConfiguration(selectDay);
        selectDayLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        selectDayNextButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        selectDayBackButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        MondayButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        TusedayButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        WednesdayButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        ThursdayButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        FridayButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        SaturdayButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
            groupDay.add(MondayButton);
            groupDay.add(TusedayButton);
            groupDay.add(WednesdayButton);
            groupDay.add(ThursdayButton);
            groupDay.add(FridayButton);
            groupDay.add(SaturdayButton);
        
        
        selectDay.add(selectDayLabel);
            selectDay.add(MondayButton);
            selectDay.add(TusedayButton);
            selectDay.add(WednesdayButton);
            selectDay.add(ThursdayButton);
            selectDay.add(FridayButton);
            selectDay.add(SaturdayButton);
        selectDay.add(selectDayNextButton);
        selectDay.add(selectDayBackButton);
        
        this.add(selectDay); 
    }
    
    //make booking hour menue view
    private JPanel selectHour = new JPanel();
    
    private JLabel selectHourLabel = new JLabel("Select the Hour");
    private JButton selectHourNextButton = new JButton("Next");
    private JButton selectHourBackButton = new JButton("Back");
    
    //Hour button group
    private ButtonGroup groupHour = new ButtonGroup();
    
    private JCheckBox hour11Button = new JCheckBox("11:00");
    private JCheckBox hour12Button = new JCheckBox("12:00");
    private JCheckBox hour13Button = new JCheckBox("1:00");
    private JCheckBox hour14Button = new JCheckBox("2:00");
    private JCheckBox hour15Button = new JCheckBox("3:00");
    private JCheckBox hour16Button = new JCheckBox("4:00");
    private JCheckBox hour17Button = new JCheckBox("5:00");
    private JCheckBox hour18Button = new JCheckBox("6:00");
    private JCheckBox hour19Button = new JCheckBox("7:00");
    private JCheckBox hour20Button = new JCheckBox("8:00");
    private JCheckBox hour21Button = new JCheckBox("9:00");
    private JCheckBox hour22Button = new JCheckBox("10:00");
    
    
    
    public void selectHourMenueView()
    {
        layoutConfiguration(selectHour);
        selectHourLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        selectHourNextButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        selectHourBackButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        hour11Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        hour12Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        hour13Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        hour14Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        hour15Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        hour16Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        hour17Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        hour18Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        hour19Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        hour20Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        hour21Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        hour22Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
            groupHour.add(hour11Button);
            groupHour.add(hour12Button);
            groupHour.add(hour13Button);
            groupHour.add(hour14Button);
            groupHour.add(hour15Button);
            groupHour.add(hour16Button);
            groupHour.add(hour17Button);
            groupHour.add(hour18Button);
            groupHour.add(hour19Button);
            groupHour.add(hour20Button);
            groupHour.add(hour21Button);
            groupHour.add(hour22Button);
            
        
        
        selectHour.add(selectHourLabel);
            selectHour.add(hour11Button);
            selectHour.add(hour12Button);
            selectHour.add(hour13Button);
            selectHour.add(hour14Button);
            selectHour.add(hour15Button);
            selectHour.add(hour16Button);
            selectHour.add(hour17Button);
            selectHour.add(hour18Button);
            selectHour.add(hour19Button);
            selectHour.add(hour20Button);
            selectHour.add(hour21Button);
            selectHour.add(hour22Button);
        selectHour.add(selectHourNextButton);
        selectHour.add(selectHourBackButton);
        
        this.add(selectHour); 
    }
    
    private JPanel selectPeople = new JPanel();
    
    private JLabel selectPeopleLabel = new JLabel("Select the amount of people coming");
    private JButton selectPeopleNextButton = new JButton("Next");
    private JButton selectPeopleBackButton = new JButton("Back");
    
    //Hour button group
    private JTextField peopleCount = new JTextField(2);
    
    public void selectPeopleMenueView()
    {
        layoutConfiguration(selectPeople);
        selectPeopleLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        selectPeopleNextButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        selectPeopleBackButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        peopleCount.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        peopleCount.setPreferredSize(standardButtonSize);
        peopleCount.setMaximumSize(standardButtonSize);
        
        selectPeople.add(selectPeopleLabel);
        selectPeople.add(peopleCount);
        selectPeople.add(selectPeopleNextButton);
        selectPeople.add(selectPeopleBackButton);
        
        this.add(selectPeople); 
    }
    
    //make booking hour menue view
    private JPanel selectDuration = new JPanel();
    
    private JLabel selectDurationLabel = new JLabel("Select the Hour");
    private JButton selectDurationNextButton = new JButton("Next");
    private JButton selectDurationBackButton = new JButton("Back");
    
    //Hour button group
    private ButtonGroup groupDuration = new ButtonGroup();
    
    private JCheckBox duration1Button = new JCheckBox("1 hour long");
    private JCheckBox duration2Button = new JCheckBox("2 hour long");
    private JCheckBox duration3Button = new JCheckBox("3 hour long");
    
    
    
    public void selectDurationMenueView()
    {
        layoutConfiguration(selectDuration);
        selectDurationLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        selectDurationNextButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        selectDurationBackButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        duration1Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        duration2Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        duration3Button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
            groupDuration.add(duration1Button);
            groupDuration.add(duration2Button);
            groupDuration.add(duration3Button);
            
        
        
        selectDuration.add(selectDurationLabel);
            selectDuration.add(duration1Button);
            selectDuration.add(duration2Button);
            selectDuration.add(duration3Button);
        selectDuration.add(selectDurationNextButton);
        selectDuration.add(selectDurationBackButton);
        
        this.add(selectDuration); 
    }
    
    
    // sign up or login in 
    private JPanel signupOrLogin = new JPanel();
    private JLabel signupOrLoginLabel = new JLabel("Signup or Login");
    
    private JButton singupOrLoginLoginButton = new JButton("Login");
    private JButton signupOrLoginSignUpButton = new JButton("Sign Up");
    // login
    //login View start
    private JPanel userPanelLogin = new JPanel();
    private JLabel uNameLogin = new JLabel("Username: ");
    private JLabel pWordLogin = new JLabel("Password: ");
    private JTextField unInputLogin = new JTextField(10);
    private JTextField pwInputLogin = new JTextField(10);
    private JButton loginButton = new JButton("Log in");
    
    
    public void loginView()
    {
        userPanelLogin.add(uNameLogin);
        userPanelLogin.add(unInputLogin);
        userPanelLogin.add(pWordLogin);
        userPanelLogin.add(pwInputLogin);
        userPanelLogin.add(loginButton);
        this.add(userPanelLogin); 
    }
    
    public String getUnText() 
    { return unInputLogin.getText(); }
    
    
    public String getPwText() 
    { return pwInputLogin.getText(); }
    
    public void addLoginButtonListener(ActionListener listener) {
    loginButton.addActionListener(listener);
    }
    
    //Signup View start
    private JPanel userPanelSignup = new JPanel();
    private JLabel uNameSignup = new JLabel("Username: ");
    private JLabel pWordSignup = new JLabel("Password: ");
    private JTextField unInputSignup = new JTextField(10);
    private JTextField pwInputSignup = new JTextField(10);
    private JButton SignupButton = new JButton("Log in");
    
    
    public void SignupView()
    {
        userPanelSignup.add(uNameSignup);
        userPanelSignup.add(unInputSignup);
        userPanelSignup.add(pWordSignup);
        userPanelSignup.add(pwInputSignup);
        userPanelSignup.add(SignupButton);
        this.add(userPanelSignup); 
    }
    
    public String getSignupUnText() 
    { return unInputSignup.getText(); }
    
    
    public String getSignupPwText() 
    { return pwInputSignup.getText(); }
    
    public void addSignupButtonListener(ActionListener listener) {
    SignupButton.addActionListener(listener);
    }
}
