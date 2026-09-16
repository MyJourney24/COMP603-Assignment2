/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *
 * @author mered
 */
// only manages the online orders
public class Order {
    
//    private final Menue menuManager;
    private final HashMap<Food, Integer> orderItems;


    private String vegan = "";
    private String vegitarian = "";
    private String glutenFree = "";
    private String alcoholFree = "";

    public Order() {
//        this.menuManager = new Menue();
        this.orderItems = new HashMap<>();
    }
        
    
    public Map<Food, Integer> makeAnOrder(Scanner orderInput)
    {
        
        boolean dietFinish = false;
        boolean dietOrderProcess = false;
        
        while(!dietOrderProcess)
        {
            while(!dietFinish)
            {

                try
                {
                    System.out.println("""

                                        ============================================================

                                        Dietary Requirements
                                        (check the dietary requirments with the corrisponding number
                                         use the corrisponding number again to uncheck a requirement)
                                        (type 5 to move on to ordering)

                                        0) Quit / Cancel

                                        1) Vegan""" + vegan + """

                                        2) Vegitarian""" + vegitarian + """

                                        3) Gluten Free""" + glutenFree + """

                                        4) Alcohol Free""" + alcoholFree + """

                                        5) Nothing Else

                                        ============================================================
                                        """);

                    switch (orderInput.nextInt())
                    {
                        case 0:
                            return null;
                        case 1:
                            if ("".equals(vegan))
                            {
                                vegan = " [x]";
                            }
                            else
                            {
                                vegan = "";
                            }
                            break;
                        case 2: 
                            if ("".equals(vegitarian))
                            {
                                vegitarian = " [x]";
                            }
                            else
                            {
                                vegitarian = "";
                            }
                            break;
                        case 3: 
                            if ("".equals(glutenFree))
                            {
                                glutenFree = " [x]";
                            }
                            else
                            {
                                glutenFree = "";
                            }
                            break;
                        case 4: 
                            if ("".equals(alcoholFree))
                            {
                                alcoholFree = " [x]";
                            }
                            else
                            {
                                alcoholFree = "";
                            }
                            break;
                        case 5: 
                            dietFinish = true;
                        default: 
                            System.out.println("Sorry that isn't an option. please type numbers 1 - 5");
                    }
                }
                catch (InputMismatchException e)
                {
                System.out.println("Invalid input. Not an integer");
                orderInput.nextLine(); // catches the mismatch imput preventing a deathloop
                }
            }


            displayFilteredMenu(filteredMenu(vegan, vegitarian, glutenFree, alcoholFree));
            
            
            boolean orderFinish = false;
            while(!orderFinish)
            {
                try
                {
                    System.out.println("""

                                       ============================================================

                                       Would you like to pre-order or order on site and skip to booking

                                       0) back
                                       1) Pre-Order
                                       2) Go to Booking

                                       ============================================================
                                       """);
                    
                    
                    
                    switch (orderInput.nextInt())
                    {
                        case 0 -> {
                            dietFinish = false;
                            orderFinish =  true;
                        }
                        case 1 -> {
                            System.out.println("Make an order");
                            boolean ordering = true;
                            Map<Food, Integer> currentOrder = preOrder(filteredMenu(vegan, vegitarian, glutenFree, alcoholFree), orderInput);
                            if (currentOrder == null)
                            {
                                dietFinish = false;
                                orderFinish =  false;
                                break;
                            }

                            while (ordering)
                            {
                                System.out.println("""
                                                    ============================================================

                                                    Would you like to save this order or go back

                                                    0) back
                                                    1) save this order

                                                    ============================================================
                                                    """);
                                switch (orderInput.nextInt())
                                {
                                    case 0 -> {
                                        ordering = false;
                                        orderFinish = false;
                                    }
                                    case 1 -> {
                                        return orderItems;
                                    }
                                    default -> System.out.println("Sorry that isn't an option. please type numbers 0 - 1");  
                                }
                            }
                        }
                        case 2 -> {
                            System.out.println("Go to booking");
                            return new HashMap<>();
                        }
                        default -> System.out.println("Sorry that isn't an option. please type numbers 1 - 2");
                    }
                }
                catch (InputMismatchException e)
                {
                System.out.println("Invalid input. Not an integer");
                orderInput.nextLine(); // catches the mismatch imput preventing a deathloop
                }
            }
        }
        

        
        return null;
    }
    
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
        return filtered;
    }
    
    public static void displayFilteredMenu(ArrayList<Food> thefilteredList) 
    {
        // this is default menue

        System.out.println("""
                           
                           
                           ============================================================
                           
                           Your Menue
                           
                           """);
        
        for (int i = 0; i < thefilteredList.size(); i++) {
            Food item = thefilteredList.get(i);
            System.out.printf((i + 1) + ") " + item.getFoodName() + " " + item.getPrice() + "\n");
        }
        
        
        
        System.out.println("\n============================================================\n");
    }
    
    public HashMap<Food, Integer> preOrder(ArrayList<Food> thefilteredList, Scanner userInput)
    {
        boolean ordering = true;
        
        if (thefilteredList.isEmpty())
        {
            System.out.println("Sorry, no items match your dietary requirements.");
            return null;
        }
        
        while(ordering)
        {
            System.out.println("""
                           ============================================================
                           Select an item by typing its number
                           type 0 to finish
                           type -1 to go back
                               
                           ============================================================
                           """);
            
            displayFilteredMenu(thefilteredList);
            
            //AI Assistance
            try {
                System.out.print("\nEnter item number: ");
                int itemChoice = userInput.nextInt();

                // Check if user wants to finish
                if (itemChoice == 0) {
                    break;
                }
                
                // check if its invalid input or exit
                if(itemChoice < 1 || itemChoice > thefilteredList.size())
                {
                    //exit
                    if (itemChoice == -1)
                    {
                        orderItems.clear();
                        return null;
                    }
                    System.out.println("Sorry that isn't an option");
                }
                
                //
                Food selectedFood = thefilteredList.get(itemChoice - 1);
                
                System.out.print("Enter servings for " + selectedFood.getFoodName() + ": ");
                int servings = userInput.nextInt();

                if (servings <= 0) {
                    System.out.println("Servings must be at least 1.");
                }
                else
                {
                    orderItems.put(selectedFood, orderItems.getOrDefault(selectedFood, 0) + servings);
                }
            }
            catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            userInput.nextLine(); // Clear invalid token to prevent infinite loop
            }   
        }
        
        orderSummary(orderItems);
        
        return orderItems;
        
    }
    
    public static void orderSummary(HashMap<Food, Integer> yourOrder)
    {
        double totalCost = 0.0;
        
        System.out.println("""
                       
                       ============================================================
                                             YOUR ORDER SUMMARY
                       ============================================================
                       """);
        
        if (yourOrder.isEmpty())
        {
            System.out.println("No items ordered.");
        }
        else
        {
            for (Map.Entry<Food, Integer> entry : yourOrder.entrySet())
            {
                Food food = entry.getKey();
                int servings = entry.getValue();
                double itemTotalCost = food.getPrice() * servings;
                totalCost += itemTotalCost;
                
                System.out.printf(servings + food.getFoodName() + itemTotalCost);
            }
        }
        System.out.println("------------------------------------------------------------");
        System.out.printf("TOTAL: $" + totalCost);
        
        System.out.println("\n\n============================================================\n\n");
    }
    
}
