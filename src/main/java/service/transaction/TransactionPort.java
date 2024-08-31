package service.transaction;

import model.order.Order;
import model.transaction.Transaction;

public interface TransactionPort {
    public void createTransaction(Order order, String action);
    public void saveTransaction(Transaction transaction);
    }
