/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

/**
 *
 * @author mered
 */
public class MainMenue extends Food{
    
    private int spiceLevel;
    private boolean isVegan;
    private boolean isVegitarian;
    private boolean isGlutenFree;
    
    public MainMenue(String foodName, double price, int spiceLevel, boolean isVegan, boolean isVegitarian, boolean isGlutenFree) {
        super(foodName, price);
        this.isGlutenFree = isGlutenFree;
        this.isVegitarian = isVegitarian;
        this.isVegan = isVegan;
        this.spiceLevel = spiceLevel;
    }

    @Override
    double alcoholLevel()
    {
        return 0;
    }
    
    @Override
    int sweetness()
    {
        return 0;
    }

    @Override
    int spiceLevel() {
        return spiceLevel;
    }

    @Override
    boolean isVegan() {
        return isVegan;
    }

    @Override
    boolean isVegitarian() {
        return isVegitarian;
    }

    @Override
    boolean isGlutenFree() {
        return isGlutenFree;
    }  
}
