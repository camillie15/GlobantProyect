package model.user;

import model.transaction.Transaction;
import model.wallet.Wallet;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String idUser;
    private String name;
    private String email;
    private String password;
    private Wallet wallet;
    private List<Transaction> transactions;

    public User(String name, String email, String password, String idUser) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.idUser = idUser;
        this.wallet = new Wallet();
        this.transactions = new ArrayList<>();
    }

    public User(String name, String email, String password, String idUser, Wallet wallet, List<Transaction> transaction) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.idUser = idUser;
        this.wallet = wallet;
        this.transactions = transaction;
    }

    /**
     * This method returns the user's name
     * @return user's name
     */
    public String getName(){
        return name;
    }

    /**
     * This method returns the user's email
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method returns the user's password
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method returns the user's id
     * @return user's id
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * This method returns the user's wallet that contains the fiat money and the list of cryptos
     * @return user's wallet
     */
    public Wallet getWallet() {
        return wallet;
    }

    /**
     * This method returns the user's transactions list
     * @return user's transactions list
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }
}
