/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

/**
 *
 * @author mered
 */
public class DrinksMenue extends Food{
    
    private int sweetness;
    private boolean isVegan;
    private double alcoholLevel;

    public DrinksMenue(String foodName, double price, int sweetness,double alcoholLevel, boolean isVegan) {
        super(foodName, price);
        this.sweetness = sweetness;
        this.isVegan = isVegan;
        this.alcoholLevel = alcoholLevel;
    }

    @Override
    int spiceLevel() {
        return 0;
    }

    @Override
    int sweetness() {
        return sweetness;
    }

    @Override
    boolean isVegan() {
        return isVegan;
    }

    @Override
    boolean isVegitarian() {
        return true;
    }

    @Override
    boolean isGlutenFree() {
        return true;
    }

    @Override
    double alcoholLevel() {
        return alcoholLevel;
    }
    
}
