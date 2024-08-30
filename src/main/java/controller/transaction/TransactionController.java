package controller.transaction;

import model.user.User;
import view.ExchangeSystemView;

public class TransactionController {
    private final ExchangeSystemView exchangeSystemView;
    private final User user;

    public TransactionController(ExchangeSystemView exchangeSystemView, User user) {
        this.exchangeSystemView = exchangeSystemView;
        this.user = user;
    }

    public void execute(){
        exchangeSystemView.viewTransactionHistory(user.getTransactions());
    }
}
