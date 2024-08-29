package model.order;

import model.crypto.TypeCrypto;

import java.math.BigDecimal;

public class Order {
    protected String idOrder;
    protected String idUser;
    protected TypeCrypto typeCrypto;
    protected BigDecimal amountTraded;
    protected BigDecimal price;
    protected boolean processedOrder;

    public Order(String idOrder, String idUser, TypeCrypto typeCrypto, BigDecimal amountTraded, BigDecimal price) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.typeCrypto = typeCrypto;
        this.amountTraded = amountTraded;
        this.price = price;
        this.processedOrder = false;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public String getIdUser() {
        return idUser;
    }

    public TypeCrypto getTypeCrypto() {
        return typeCrypto;
    }

    public BigDecimal getAmountTraded() {
        return amountTraded;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isProcessedOrder() {
        return processedOrder;
    }

    public void orderProcessed(){
        this.processedOrder = true;
    }
}
