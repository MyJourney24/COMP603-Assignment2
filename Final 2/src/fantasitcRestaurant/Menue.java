/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

import java.util.ArrayList;

/**
 *
 * @author mered
 */
// the chef can add new menue items or change current menue items right here without the need to change anything else as long as this structure is followed
public class Menue {
    
    private ArrayList<Food> fullMenu;
    
    public Menue() {
        
        this.fullMenu = new ArrayList<Food>();
        
        //main menue
        // to add more dishes write new MainMenue(dish name_price_spice leve_is vegan_is vegitarian _is gluten free)
        fullMenu.add(new MainMenue("Beef Pho", 19.50,2,false, false, true));
        fullMenu.add(new MainMenue("Chicken Pho", 18.50,1,false ,false , true));
        fullMenu.add(new MainMenue("Spicy Vegan Tofu", 17.00,3, true, true, true));
        fullMenu.add(new MainMenue("Vegetable Dumplings", 14.50,0, true, true, false));
        fullMenu.add(new MainMenue("Chicken Laksa", 19.00, 1, false, false, true));
        fullMenu.add(new MainMenue("Mushroom Risotto", 22.00, 0, false, true, true));
        
        //Drinks
        // to add more dishes write new DrinksMenue(dish name_price_sweetness_alcohol percentage _is vegan)
        fullMenu.add(new DrinksMenue("House Red Wine", 12.00, 0 ,15, true));
        fullMenu.add(new DrinksMenue("Craft Beer", 9.50, 0, 6, false));
        fullMenu.add(new DrinksMenue("Matcha Green Tea", 6.50,2, 0, true));
        fullMenu.add(new DrinksMenue("Fresh Orange Juice", 5.50, 5, 0, true));
        fullMenu.add(new DrinksMenue("Tequila Sunrise", 11.00, 4, 11, true));
        
        //Desserts
        // to add more dishes write new DessertMenue(dish name_price_sweetness_is vegan_is gluten free)
        fullMenu.add(new DessertMenue("Mango Sticky Rice", 11.00, 3, true, true));
        fullMenu.add(new DessertMenue("Vegan Chocolate Cake", 13.50, 5, true, false));
        fullMenu.add(new DessertMenue("Classic Vanilla Ice Cream", 7.50, 5, false, true));
        fullMenu.add(new DessertMenue("Gluten-Free Fruit Tart", 10.50,3, false, true));
        fullMenu.add(new DessertMenue("Berry Pudding", 8.50, 5, false, true));
    }
    
    public ArrayList<Food> getFullMenu() {
        return fullMenu;
    }
    
    
    
//    private ArrayList<Food> theMenue()
//    {
//        ArrayList<Food> fullMenu = new ArrayList<>();
//        
//        //main menue
//        // to add more dishes write new MainMenue(dish name_price_spice leve_is vegan_is vegitarian _is gluten free)
//        fullMenu.add(new MainMenue("Beef Pho", 19.50,2,false, false, true));
//        fullMenu.add(new MainMenue("Chicken Pho", 18.50,1,false ,false , true));
//        fullMenu.add(new MainMenue("Spicy Vegan Tofu", 17.00,3, true, true, true));
//        fullMenu.add(new MainMenue("Vegetable Dumplings", 14.50,0, true, true, false));
//        fullMenu.add(new MainMenue("Chicken Laksa", 19.00, 1, false, false, true));
//        fullMenu.add(new MainMenue("Mushroom Risotto", 22.00, 0, false, true, true));
//        
//        //Drinks
//        // to add more dishes write new DrinksMenue(dish name_price_sweetness_alcohol percentage _is vegan)
//        fullMenu.add(new DrinksMenue("House Red Wine", 12.00, 0 ,15, true));
//        fullMenu.add(new DrinksMenue("Craft Beer", 9.50, 0, 6, false));
//        fullMenu.add(new DrinksMenue("Matcha Green Tea", 6.50,2, 0, true));
//        fullMenu.add(new DrinksMenue("Fresh Orange Juice", 5.50, 5, 0, true));
//        fullMenu.add(new DrinksMenue("Virgin Mojito - non alcoholic", 8.00, 1, 0, true));
//        fullMenu.add(new DrinksMenue("Tequila Sunrise", 11.00, 4, 11, true));
//        
//        //Desserts
//        // to add more dishes write new DessertMenue(dish name_price_sweetness_is vegan_is gluten free)
//        fullMenu.add(new DessertMenue("Mango Sticky Rice", 11.00, 3, true, true));
//        fullMenu.add(new DessertMenue("Vegan Chocolate Cake", 13.50, 5, true, false));
//        fullMenu.add(new DessertMenue("Classic Vanilla Ice Cream", 7.50, 5, false, true));
//        fullMenu.add(new DessertMenue("Gluten-Free Fruit Tart", 10.50,3, false, true));
//        fullMenu.add(new DessertMenue("Berry Pudding", 8.50, 5, false, true));
//        
//        return fullMenu;
//    }

    

    
    
}
