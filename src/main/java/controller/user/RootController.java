package controller.user;

import service.user.UserService;
import view.LoginView;

public class RootController {
    private final LoginView loginView;
    private final RegisterController registerController;

    public RootController(UserService userService, LoginView loginView) {
        this.loginView = loginView;
        this.registerController = new RegisterController(loginView, userService);
    }

    public void run(){
        while (true){
            int userLoginChoice = loginView.getUserLoginChoice();
            switch (userLoginChoice){
                case 1:
                    registerController.execute();
                    break;
                default:
                    System.out.println("\u001B[31mInvalid option\u001B[0m");
                    break;
            }
        }
    }
}
