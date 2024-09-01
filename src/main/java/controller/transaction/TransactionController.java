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

    /**
     * This method calls a view method that print the user's transactions.
     */
    public void execute(){
        exchangeSystemView.viewTransactionHistory(user.getTransactions());
    }
}
