package controller;

import controller.user.LoginController;
import controller.user.RegisterController;
import service.user.UserService;
import view.LoginView;

public class RootController {
    private final LoginView loginView;
    private final RegisterController registerController;
    private final LoginController loginController;

    public RootController(UserService userService, LoginView loginView) {
        this.loginView = loginView;
        this.registerController = new RegisterController(loginView, userService);
        this.loginController = new LoginController(loginView, userService);
    }
    /**
     * This method calls a series of methods that request the option selected by the user and
     * executes a method according to this option.
     */
    public void run(){
        while (true){
            int userLoginChoice = loginView.getUserLoginChoice();
            switch (userLoginChoice){
                case 1:
                    registerController.execute();
                    break;
                case 2:
                    loginController.execute();
                    break;
                case 0:
                    System.out.println("\u001B[34mClosing Crypto Exchange Sistem...\u001B[0m");
                    System.exit(0);
                default:
                    System.out.println("\u001B[31mInvalid option\u001B[0m");
                    break;
            }
        }
    }
}
