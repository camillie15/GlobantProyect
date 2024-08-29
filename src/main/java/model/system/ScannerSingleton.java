package model.system;

import java.util.Scanner;

public class ScannerSingleton {
    private final Scanner scanner;


    private ScannerSingleton(Scanner scanner) {
        this.scanner = scanner;
    }

    private static class ScannerHolder{
        public static ScannerSingleton scannerInstance = new ScannerSingleton(new Scanner(System.in));
    }

    public static ScannerSingleton getScannerInstance(){
        return ScannerHolder.scannerInstance;
    }

    public Scanner getScanner() {
        return scanner;
    }

}
