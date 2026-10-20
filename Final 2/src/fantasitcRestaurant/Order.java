/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

import static fantasitcRestaurant.Model.tempSaveFiltered;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
        this.orderItems = new HashMap<>();
    }
    
    public ArrayList<Food> filteredMenu(String vegan, String vegitarian, String glutenFree, String alcoholFree)
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
    
    public String displayFilteredMenue(ArrayList<Food> thefilteredList)
    {
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
    
    public String orderSummary(HashMap<Food, Integer> yourOrder)
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
    
    
}
