package view;

import model.exceptions.InvalidPasswordException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginView {
    private final Scanner scanner;

    public LoginView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    /**
     * This method print the menu to start in the system and asks the user to select an option.
     * @return the option that the user selected
     */
    public int getUserLoginChoice(){
        System.out.println("Welcome to the Crypto Exchange System");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");
        System.out.print("Choose one option to start: ");
        try{
            return scanner.nextInt();
        } catch (InputMismatchException e){
            scanner.nextLine();
            return -1;
        }
    }

    /**
     * This method asks the user for his name
     * @return user's name
     */
    public String getUserNameInput(){
        System.out.print("Enter your name: ");
        return scanner.next();
    }

    /**
     * This method asks the user for his email
     * @return user's email
     */
    public String getUserEmailInput(){
        System.out.print("Enter your email: ");
        return scanner.next();
    }

    /**
     * This method asks the user for his password and verifies that the password must be 8 characters or more
     * @return user's password
     */
    public String getPasswordInput(){
        try {
            System.out.print("Enter your password: ");
            String password = scanner.next();
            if(password.length() < 8){
                throw new InvalidPasswordException();
            }
            else {
                return password;
            }
        }catch (InvalidPasswordException e){
            System.out.println("\u001B[31mPassword must be 8 or more characters\u001B[0m");
            return getPasswordInput();
        }
    }
}
