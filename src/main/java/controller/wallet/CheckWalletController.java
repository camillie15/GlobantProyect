package controller.wallet;

import model.user.User;
import view.ExchangeSystemView;

public class CheckWalletController {
    private final ExchangeSystemView exchangeSystemView;
    private final User user;

    public CheckWalletController(ExchangeSystemView exchangeSystemView, User user) {
        this.exchangeSystemView = exchangeSystemView;
        this.user = user;
    }

    public void execute(){
        exchangeSystemView.viewWalletBalance(user.getWallet());
    }
}
