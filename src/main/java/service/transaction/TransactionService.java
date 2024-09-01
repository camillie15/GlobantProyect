package service.transaction;

import model.order.Order;
import model.transaction.Transaction;
import model.user.User;

public class TransactionService implements TransactionPort{
    private final User user;

    public TransactionService(User user) {
        this.user = user;
    }

    /**
     * This method creates a transaction based on an order already created with his action.
     * @param order order that once processed generates a transaction.
     * @param action action of the order can be buy or sell
     */
    @Override
    public void createTransaction(Order order, String action) {
        Transaction transaction = new Transaction(order.getTypeCrypto(), order.getAmountTraded(), order.getPrice(), action);
        saveTransaction(transaction);
    }

    /**
     * This method save the transaction in the user's transaction list.
     * @param transaction transaction that will be saved
     */
    @Override
    public void saveTransaction(Transaction transaction) {
        user.getTransactions().add(transaction);
    }
}
