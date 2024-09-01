package model.order;

import model.crypto.TypeCrypto;

import java.math.BigDecimal;

public abstract class Order {
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

    /**
     * This method returns the id of the order
     * @return id of the order
     */
    public String getIdOrder() {
        return idOrder;
    }

    /**
     * This method returns the id of the user that generate the order
     * @return id of the user
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * This method returns the type of crypto
     * @return type of crypto
     */
    public TypeCrypto getTypeCrypto() {
        return typeCrypto;
    }

    /**
     * This method returns the amount of the crypto
     * @return amount of crypto
     */
    public BigDecimal getAmountTraded() {
        return amountTraded;
    }

    /**
     * This method returns the price of the crypto
     * @return price of crypto
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method returns the boolean data if the order is process or not
     * @return if the order is process or not
     */
    public boolean isProcessedOrder() {
        return processedOrder;
    }

    /**
     * This method change the status of the order to process
     */
    public void orderProcessed(){
        this.processedOrder = true;
    }
}
