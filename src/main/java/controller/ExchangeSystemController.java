package controller;

import view.ExchangeSystemView;

public class ExchangeSystemController {
    private final ExchangeSystemView exchangeSystemView;

    public ExchangeSystemController(ExchangeSystemView exchangeSystemView) {
        this.exchangeSystemView = exchangeSystemView;
    }

    public void run(){
        while (true){
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
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}
