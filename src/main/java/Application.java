import model.system.ScannerSingleton;
import view.LoginView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = ScannerSingleton.getScannerInstance().getScanner();

        LoginView userView = new LoginView(scanner);

        scanner.close();
    }
}
