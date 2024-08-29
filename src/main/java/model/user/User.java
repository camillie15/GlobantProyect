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

    public String getName(){
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getIdUser() {
        return idUser;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
