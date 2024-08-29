package model.transaction;

import model.crypto.TypeCrypto;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Transaction {
    private TypeCrypto typeCrypto;
    private BigDecimal amountTraded;
    private BigDecimal price;
    private String action;

    public Transaction(TypeCrypto typeCrypto, BigDecimal amountTraded, BigDecimal price, String action) {
        this.typeCrypto = typeCrypto;
        this.amountTraded = amountTraded;
        this.price = price;
        this.action = action;
    }

    public String toString(){
        String cryptoData = amountTraded + " " + typeCrypto.getSymbolCrypto();
        return String.format("%s\t\t|\t  %-10s| \t  %-14s| \t  %-10s\n", typeCrypto.getNameCrypto(), cryptoData, new DecimalFormat("#,#00.00").format(price), action);
    }
}
