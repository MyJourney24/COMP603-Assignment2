/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fantasitcRestaurant;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author mered
 */
// lets the user chose whether they want to sign up or log in
public class SignUpLogin {
    
    
    
    private final FileManager fileManager;
    private String authenticatedUser;
   
    public SignUpLogin() {
        this.fileManager = new FileManagertxt();
        this.authenticatedUser = null;
    }
    
    
    public String SignUpOrLogin(Scanner signInInput)
    {
        UserLogin userLoginFunction = new UserLogin(fileManager);
        UserSignUp userSignUpFunction = new UserSignUp(fileManager);


        while (authenticatedUser == null)
        {
            try
            {
                System.out.println("Would you like to sign up or login");
                System.out.println("""
                                   =====================================================
                                   (Warning quiting will restart the booking process)
                                   
                                   0) Quit / Cancel 
                                   1) Sign up
                                   2) Login
                                   
                                   =====================================================
                                   """);

                int signOrLog = signInInput.nextInt();
                switch (signOrLog) {
                    case 0 -> {
                        return null; // Quit stage 1
                    }
                    case 1 -> authenticatedUser = userSignUpFunction.RunSignUp(signInInput, this.fileManager); // activate Signup Function
                    case 2 -> authenticatedUser = userLoginFunction.RunLogin(signInInput, this.fileManager);// activates Login Function
                    default -> System.out.println("Invalid input");
                }
            }
            catch (InputMismatchException e){
            System.out.println("Invalid input. Not an integer");
            signInInput.nextLine();
            }
        }

        return authenticatedUser;
    }
   
}
