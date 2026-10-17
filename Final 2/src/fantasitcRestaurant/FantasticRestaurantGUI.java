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
        FileManagertxt fileManager = new FileManagertxt();
        
        View viewStart = new View();
        Model startModel = new Model(viewStart, fileManager);
        Controller startController = new Controller(startModel, viewStart);
        
        startModel.addListener(viewStart);
        viewStart.setVisible(true);
//        
//        
//        MathView mView = new MathView();
//        MathModel mModel = new MathModel(mView);
//        MathController mController = new MathController(mModel, mView);
//        mView.setVisible(true);
//        mModel.dbsetup();
    }
    
}
