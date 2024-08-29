import controller.RootController;
import model.system.ScannerSingleton;
import service.user.UserService;
import view.LoginView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = ScannerSingleton.getScannerInstance().getScanner();

        UserService userService = new UserService();
        LoginView loginView = new LoginView(scanner);

        RootController rootController = new RootController(userService, loginView);
        rootController.run();

        scanner.close();
    }
}
