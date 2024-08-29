package controller;

import controller.wallet.DepositController;
import service.exchange.ExchangeSystemService;
import service.wallet.WalletService;
import view.ExchangeSystemView;

public class ExchangeSystemController {
    private final ExchangeSystemView exchangeSystemView;

    private final DepositController depositController;

    public ExchangeSystemController(ExchangeSystemView exchangeSystemView, ExchangeSystemService exchangeSystemService, WalletService walletService) {
        this.exchangeSystemView = exchangeSystemView;
        this.depositController = new DepositController(exchangeSystemView, walletService);
    }

    public void run(){
        boolean login = true;
        while (login){
            int userExchangeChoice = exchangeSystemView.getUserSystemChoice();
            switch (userExchangeChoice){
                case 1:
                    depositController.execute();
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
                    System.out.println("\u001B[31mInvalid option\u001B[0m");
                    break;
            }
        }
    }
}
