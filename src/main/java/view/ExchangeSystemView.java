package view;

import model.crypto.Crypto;
import model.crypto.TypeCrypto;
import model.transaction.Transaction;
import model.wallet.Wallet;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ExchangeSystemView {
    private final Scanner scanner;

    public ExchangeSystemView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getUserSystemChoice(){
        System.out.println("Crypto Exchange System Menu");
        System.out.println("1. Deposit Money");
        System.out.println("2. View Wallet Balance ");
        System.out.println("3. Buy Cryptocurrencies from the Exchange");
        System.out.println("4. Place Buy Order");
        System.out.println("5. Place Sell Order");
        System.out.println("6. View Transaction History");
        System.out.println("7. Log Out");
        System.out.print("Choose one option: ");
        try{
            return scanner.nextInt();
        } catch (InputMismatchException e){
            scanner.nextLine();
            return -1;
        }
    }

    public BigDecimal getAmountDepositInput(){
        try{
            System.out.print("Type fiat money to deposit: ");
            return scanner.nextBigDecimal();
        } catch (InputMismatchException e){
            System.out.println("\u001B[31mInvalid format\u001B[0m");
            scanner.nextLine();
            return getAmountDepositInput();
        }
    }

    public String getTypeCryptoInput(String msg){
        try {
            System.out.print(msg);
            TypeCrypto typeCrypto = TypeCrypto.valueOf(scanner.next().toUpperCase());
            return typeCrypto.getNameCrypto();
        }catch (IllegalArgumentException e){
            System.out.println("\u001B[31mInvalid cryptocurrency type. Cryptocurrency available: Bitcoin and Ethereum\u001B[0m");
            return getTypeCryptoInput(msg);
        }
    }

    public BigDecimal getAmountCryptoInput(String msg){
        try{
            System.out.print(msg);
            return scanner.nextBigDecimal();
        } catch (InputMismatchException e){
            System.out.println("\u001B[31mInvalid format\u001B[0m");
            scanner.nextLine();
            return getAmountCryptoInput(msg);
        }
    }

    public BigDecimal getPriceCryptoInput(String msg){
        try{
            System.out.print(msg);
            return scanner.nextBigDecimal();
        } catch (InputMismatchException e){
            System.out.println("\u001B[31mInvalid format\u001B[0m");
            scanner.nextLine();
            return getPriceCryptoInput(msg);
        }
    }

    public void viewWalletBalance(Wallet wallet){
        System.out.println("""
                ---------------------------------------
                \t\tView Wallet Balance
                ---------------------------------------""");
    }

    public void viewExchangeCrypto(List<Crypto> exchangeListCryptos){
        System.out.println("""
                -----------------------------------------------------
                \t\t\tExchange Cryptos Available
                -----------------------------------------------------""");
        System.out.println("\tCryptocurrency\t|\t Amount\t  | \t Price");
    }

    public void viewTransactionHistory(List<Transaction> transactions){
        System.out.println("""
                -----------------------------------------------------------------------
                \t\t\t\t\t\tTransaction History
                -----------------------------------------------------------------------""");
        System.out.println("   Cryptocurrency\t|\t Amount \t|\t   Price \t\t|\t Action");
    }
}
