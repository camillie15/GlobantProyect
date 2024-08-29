package controller.user;

import model.exceptions.EmailExistsException;
import model.user.User;
import service.user.UserService;
import view.LoginView;

public class RegisterController {
    private final LoginView loginView;
    private final UserService userService;

    public RegisterController(LoginView loginView, UserService userService) {
        this.loginView = loginView;
        this.userService = userService;
    }

    public void execute(){
        String nameUser = loginView.getUserNameInput();
        String email = loginView.getUserEmailInput();
        String password = loginView.getPasswordInput();
        try{
            User userCreate = userService.createUser(nameUser, email, password);
            userService.saveUser(userCreate);
            if(userService.checkIdUser(userCreate.getIdUser())){
                System.out.println("\u001B[32m\t>> Successful register <<\u001B[0m");
            }else{
                System.out.println("\u001B[31m>> Unsuccessful register <<\u001B[0m" );
            }

        } catch (EmailExistsException e){
            System.out.printf("\u001B[31mUser with email %s already exits.\n\u001B[0m", email );
        }
    }
}
