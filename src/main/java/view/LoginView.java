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

    public String getUserNameInput(){
        System.out.print("Enter your name: ");
        return scanner.next();
    }

    public String getUserEmailInput(){
        System.out.print("Enter your email: ");
        return scanner.next();
    }

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
