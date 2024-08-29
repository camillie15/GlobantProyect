package controller;

import service.exchange.ExchangeSystemService;
import view.ExchangeSystemView;

public class ExchangeSystemController {
    private final ExchangeSystemView exchangeSystemView;

    public ExchangeSystemController(ExchangeSystemView exchangeSystemView, ExchangeSystemService exchangeSystemService) {
        this.exchangeSystemView = exchangeSystemView;
    }

    public void run(){
        boolean login = true;
        while (login){
            int userExchangeChoice = exchangeSystemView.getUserSystemChoice();
            switch (userExchangeChoice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    login = false;
                    System.out.println("\u001B[32m\t\t>> Logout <<\u001B[0m");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}
