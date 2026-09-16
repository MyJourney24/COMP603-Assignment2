/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

/**
 *
 * @author mered
 */
public abstract class Food {
    
    private String foodName;
    private double price;

    public Food(String foodName, double price) {
        this.foodName = foodName;
        this.price = price;
    }

    public String getFoodName()
    {
        return foodName;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    abstract int spiceLevel();
    abstract int sweetness();
    abstract boolean isVegan();
    abstract boolean isVegitarian();
    abstract boolean isGlutenFree();
    abstract double alcoholLevel();
    
    
}
