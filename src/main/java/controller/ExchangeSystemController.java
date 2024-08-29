package controller;

import controller.wallet.CheckWalletController;
import controller.wallet.DepositController;
import model.user.User;
import service.exchange.ExchangeSystemService;
import service.wallet.WalletService;
import view.ExchangeSystemView;

public class ExchangeSystemController {
    private final ExchangeSystemView exchangeSystemView;

    private final DepositController depositController;
    private final CheckWalletController checkWalletController;

    public ExchangeSystemController(ExchangeSystemView exchangeSystemView, ExchangeSystemService exchangeSystemService, WalletService walletService, User user) {
        this.exchangeSystemView = exchangeSystemView;
        this.depositController = new DepositController(exchangeSystemView, walletService);
        this.checkWalletController = new CheckWalletController(exchangeSystemView, user);
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
                    checkWalletController.execute();
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
