/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

/**
 *
 * @author mered
 */
public class FantasticRestaurantGUI {
    
    public static void main(String[] args) {
        FileManagerdbs fileManager = new FileManagerdbs();
        
        View viewStart = new View();
        Model startModel = new Model(viewStart, fileManager);
        Controller startController = new Controller(startModel, viewStart);
        
        startModel.addListener(viewStart);
        viewStart.setVisible(true);
    }
    
}
