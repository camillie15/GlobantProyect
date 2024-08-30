package service.transaction;

import model.order.Order;
import model.transaction.Transaction;
import model.user.User;

import java.util.HashMap;
import java.util.Map;

public class TransactionService {
    private Map<String, Transaction> transactions = new HashMap<>();
    private User user;

    public TransactionService(User user) {
        this.user = user;
    }

    public void createTransaction(Order order, String action){
        Transaction transaction = new Transaction(order.getTypeCrypto(), order.getAmountTraded(), order.getPrice(), action);
        if(transaction != null){
            addTransaction(transaction);
        }
    }

    public void addTransaction(Transaction transaction){
        String idTransaction = "B" + (getTotalTransactions() + 1);
        transactions.put(idTransaction, transaction);
        user.getTransactions().add(transaction);
    }

    public int getTotalTransactions (){
        return transactions.size();
    }

}
