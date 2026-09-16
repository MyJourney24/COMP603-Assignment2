/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

/**
 *
 * @author mered
 */
public class DessertMenue extends Food{
    
    int sweetness;
    boolean isVegan;
    boolean isGlutenFree;

    public DessertMenue(String foodName, double price, int sweetness, boolean isVegan, boolean isGlutenFree) {
        super(foodName, price);
        this.sweetness = sweetness;
        this.isVegan = isVegan;
        this.isGlutenFree =isGlutenFree;
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
        return isGlutenFree;
    }

    @Override
    double alcoholLevel() {
        return 0;
    }
    
}
