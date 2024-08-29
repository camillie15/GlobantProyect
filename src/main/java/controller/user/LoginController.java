package controller.user;

import controller.ExchangeSystemController;
import model.exceptions.InvalidPasswordException;
import model.exceptions.UnknownEmailException;
import model.user.User;
import service.exchange.ExchangeSystemService;
import service.user.UserService;
import service.wallet.WalletService;
import view.ExchangeSystemView;
import view.LoginView;

public class LoginController {
    private final LoginView loginView;
    private final UserService userService;

    public LoginController(LoginView loginView, UserService userService) {
        this.loginView = loginView;
        this.userService = userService;
    }

    public void execute(){
        String email = loginView.getUserEmailInput();
        String password = loginView.getPasswordInput();
        try {
            User user = userService.getUserByEmail(email);
            if(user != null){
                if(user.getPassword().equals(password)){
                    System.out.println("\u001B[32m\t>> Successful login <<\u001B[0m");
                    ExchangeSystemView exchangeSystemView = new ExchangeSystemView(loginView.getScanner());
                    ExchangeSystemService exchangeSystemService = ExchangeSystemService.getExchangeInstance(userService);
                    WalletService walletService = new WalletService(user);
                    ExchangeSystemController systemController = new ExchangeSystemController(exchangeSystemView, exchangeSystemService, walletService, user);
                    systemController.run();
                }else{
                    throw new InvalidPasswordException();
                }
            } else{
                throw new UnknownEmailException();
            }
        }catch (UnknownEmailException e){
            System.out.printf("\u001B[31mUser with email %s not registered\n\u001B[0m", email);
        }catch (InvalidPasswordException e){
            System.out.println("\u001B[31mInvalid password\u001B[0m");
        }
    }
}
