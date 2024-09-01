package model.crypto;

import java.math.BigDecimal;

public class Crypto {
    private String idCrypto;
    private TypeCrypto typeCrypto;
    private BigDecimal amountCrypto;
    private BigDecimal priceCrypto;

    public Crypto(TypeCrypto typeCrypto, BigDecimal amountCrypto, BigDecimal priceCrypto) {
        this.idCrypto = typeCrypto.getIdCrypto();
        this.typeCrypto = typeCrypto;
        this.amountCrypto = amountCrypto;
        this.priceCrypto = priceCrypto;
    }

    public Crypto(TypeCrypto typeCrypto, BigDecimal amountCrypto) {
        this.idCrypto = typeCrypto.getIdCrypto();
        this.typeCrypto = typeCrypto;
        this.amountCrypto = amountCrypto;
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
    public BigDecimal getAmountCrypto(){
        return amountCrypto;
    }

    /**
     * This method returns the price of the crypto
     * @return price of crypto
     */
    public BigDecimal getPriceCrypto() {
        return priceCrypto;
    }

    /**
     * This method add an amount to the amount of the crypto
     * @param amountTraded amount to add
     */
    public void buyCrypto(BigDecimal amountTraded){
        this.amountCrypto = amountCrypto.add(amountTraded);
    }

    /**
     * This method subtract an amount to the amount of the crypto
     * @param amountTraded amount to subtract
     */
    public void sellCrypto(BigDecimal amountTraded){
        this.amountCrypto = amountCrypto.subtract(amountTraded);
    }

    /**
     * This method add a variation to the price of the crypto
     * @param variation variation to add to the price
     */
    public void addPrice(BigDecimal variation){
        this.priceCrypto = priceCrypto.add(variation);
    }

    /**
     * This method subtract a variation to the price of the crypto
     * @param variation variation to subtract to the price
     */
    public void subtractPrice(BigDecimal variation){
        this.priceCrypto = priceCrypto.subtract(variation);
    }

    public String toString(){
        return String.format("%s\t\t|\t  %s %-10s\n", typeCrypto.getNameCrypto(), amountCrypto, typeCrypto.getSymbolCrypto());
    }
}
