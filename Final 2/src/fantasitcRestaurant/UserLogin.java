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
public class UserLogin {
    private final FileManager fileManager;

    public UserLogin(FileManager fileManager) {
        this.fileManager = fileManager;
    }
    
    
    public String RunLogin(Scanner userInput, FileManager fileManager)
    {

        System.out.println("""
                            ============================================================

                            What is your Username
                            Type 0 to quit

                            ============================================================
                            """);
        String inputUsername = userInput.next();

        if (inputUsername.equals("0"))
        {
            return null;
            
        }
        
        System.out.println("""
                            ============================================================

                            What is your Password
                            Type 0 to quit

                            ============================================================
                            """);
         String inputPassword = userInput.next();
         
         if (inputPassword.equals("0"))
        {
            return null;
        }

         

        String userActivated = authenticate(inputUsername, inputPassword);

        if(userActivated != null)
        {
            System.out.println("=====================================================\n");
            System.out.println("Welcome Back " + inputUsername + "\n");
            System.out.println("=====================================================\n");
            return inputUsername;
        }
        else
        {
            System.out.println("\n Incorrect Username or Password, please try again \n");
        }  
        return null;
    }
    
    private String authenticate(String username, String password)
    {
        return fileManager.authenticateUser(username, password);       
    }
}
