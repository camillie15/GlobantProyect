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

    /**
     * This method print the system menu and asks the user to select an option.
     * @return the option that the user selected
     */
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

    /**
     * This method asks the user for an amount to deposit and verifies that it is greater than 0.
     * @return the amount to deposit
     */
    public BigDecimal getAmountDepositInput(){
        try{
            System.out.print("Type fiat money to deposit: ");
            BigDecimal amount = scanner.nextBigDecimal();
            if(amount.compareTo(BigDecimal.ZERO) > 0){
                return amount;
            }else{
                throw new IllegalArgumentException();
            }
        } catch (InputMismatchException e){
            System.out.println("\u001B[31mInvalid format. Use ',' for decimals\u001B[0m");
            scanner.nextLine();
            return getAmountDepositInput();
        } catch (IllegalArgumentException e) {
            System.out.println("\u001B[31mInvalid amount to deposit. Amount must be greater than 0\u001B[0m");
            scanner.nextLine();
            return getAmountDepositInput();
        }
    }

    /**
     * This method asks the user for the type of crypto and verifies that it is bitcoin or ethereum
     * @param msg message to print
     * @return type of crypto
     */
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

    /**
     * This method asks the user for an amount of crypto and verifies that it is greater than 0.
     * @param msg message to print
     * @return amount of crypto
     */
    public BigDecimal getAmountCryptoInput(String msg){
        try{
            System.out.print(msg);
            BigDecimal amount = scanner.nextBigDecimal();
            if(amount.compareTo(BigDecimal.ZERO) > 0){
                return amount;
            }else{
                throw new IllegalArgumentException();
            }
        } catch (InputMismatchException e){
            System.out.println("\u001B[31mInvalid format\u001B[0m");
            scanner.nextLine();
            return getAmountCryptoInput(msg);
        } catch (IllegalArgumentException e){
            System.out.println("\u001B[31mInvalid amount. Amount must be greater than 0\u001B[0m");
            scanner.nextLine();
            return getAmountCryptoInput(msg);
        }
    }

    /**
     * This method asks the user for a price of crypto and verifies that it is greater than 0.
     * @param msg message to print
     * @return price of crypto
     */
    public BigDecimal getPriceCryptoInput(String msg){
        try{
            System.out.print(msg);
            BigDecimal price = scanner.nextBigDecimal();
            if(price.compareTo(BigDecimal.ZERO) > 0){
                return price;
            }else{
                throw new IllegalArgumentException();
            }
        } catch (InputMismatchException e){
            System.out.println("\u001B[31mInvalid format. Use ',' for decimals\u001B[0m");
            scanner.nextLine();
            return getPriceCryptoInput(msg);
        } catch (IllegalArgumentException e){
            System.out.println("\u001B[31mInvalid price. Price must be greater than 0\u001B[0m");
            scanner.nextLine();
            return getAmountCryptoInput(msg);
        }
    }

    /**
     * This method print the details of the user's wallet.
     * @param wallet wallet to print details
     */
    public void viewWalletBalance(Wallet wallet){
        System.out.println("""
                \u001B[36m---------------------------------------
                \t\tView Wallet Balance
                ---------------------------------------""");
        System.out.printf("   Fiat Money: \t\t   $%s\n---------------------------------------\n",
                new DecimalFormat("#,#00.00").format(wallet.getBalanceCash()));
        List<Crypto> listCryptos = wallet.getCryptoList();
        StringBuilder cryptoData = new StringBuilder();
        for(Crypto crypto : listCryptos){
            cryptoData.append("   ∙ ").append(crypto.toString());
        }
        System.out.println("   Cryptocurrency\t|\t Amount");
        System.out.println(cryptoData + "\u001B[0m");
    }

    /**
     * This method print the list of cryptos in the exchange.
     * @param exchangeListCryptos list of cryptos to print
     */
    public void viewExchangeCrypto(List<Crypto> exchangeListCryptos){
        System.out.println("""
                \u001B[36m-----------------------------------------------------
                \t\t\tExchange Cryptos Available
                -----------------------------------------------------""");
        System.out.println("\tCryptocurrency\t|\t Amount\t  | \t Price");
        for(Crypto crypto : exchangeListCryptos){
            String cryptoData = crypto.getAmountCrypto() + " " + crypto.getTypeCrypto().getSymbolCrypto();
            System.out.printf("   ∙ %s\t\t|\t%-10s| \t%-10s\n", crypto.getTypeCrypto().getNameCrypto(), cryptoData,
                    new DecimalFormat("#,#00.00").format(crypto.getPriceCrypto()));
        }
        System.out.print("\u001B[0m\n");
    }

    /**
     * This method print the list of a user's transactions.
     * @param transactions list of transaction to print
     */
    public void viewTransactionHistory(List<Transaction> transactions){
        System.out.println("""
                \u001B[36m-----------------------------------------------------------------------
                \t\t\t\t\t\tTransaction History
                -----------------------------------------------------------------------""");
        System.out.println("   Cryptocurrency\t|\t Amount \t|\t   Price \t\t|\t Action");
        StringBuilder cryptoExchangeData = new StringBuilder();
        for(Transaction transaction : transactions){
            cryptoExchangeData.append("   ∙ ").append(transaction.toString());
        }
        System.out.println(cryptoExchangeData + "\u001B[0m");
    }
}
