/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;
import java.util.Scanner;

/**
 *
 * @author mered
 */
//Only gathers input to sign up
public class UserSignUp {
    
    private final FileManager fileManager;
    
    public UserSignUp(FileManager fileManager)
    {
        this.fileManager = fileManager;
    }
    
    public String RunSignUp(Scanner userInput, FileManager fileManager)
    {

        System.out.println("""
                           =====================================================
                           
                           Create a Unique Username
                           Type 0 to quit
                                                 
                           =====================================================
                           """);
        String usernameInput = userInput.next();

        if (usernameInput.equals("0"))
        {
            return null;
            
        }

        System.out.println("""
                           =====================================================
                           
                           Create a password
                           Type 0 to quit
                                                 
                           =====================================================
                           """);
        String passwordInput = userInput.next();
         
        if (passwordInput.equals("0"))
        {
            return null;
        }
        
        
        if (usernameInput.contains(",") == true)
        {
            System.out.println("don't include , ");
            return null;
        }

        boolean success = saveUserDetails(usernameInput, passwordInput);
        
        if (success)
        {
            return usernameInput;
        }
        else 
        {
            return null;
        }    
    }
    
    public boolean saveUserDetails(String usernameInput, String passwordInput)
    {
        return fileManager.savesUser(usernameInput, passwordInput);
    }
}
