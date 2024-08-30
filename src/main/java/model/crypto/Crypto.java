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

    public TypeCrypto getTypeCrypto() {
        return typeCrypto;
    }

    public BigDecimal getAmountCrypto(){
        return amountCrypto;
    }

    public BigDecimal getPriceCrypto() {
        return priceCrypto;
    }

    public void buyCrypto(BigDecimal amountTraded){
        this.amountCrypto = amountCrypto.add(amountTraded);
    }

    public void sellCrypto(BigDecimal amountTraded){
        this.amountCrypto = amountCrypto.subtract(amountTraded);
    }

    public void addPrice(BigDecimal variation){
        this.priceCrypto = priceCrypto.add(variation);
    }

    public void subtractPrice(BigDecimal variation){
        this.priceCrypto = priceCrypto.subtract(variation);
    }

    public String toString(){
        return String.format("%s\t\t|\t  %s %-10s\n", typeCrypto.getNameCrypto(), amountCrypto, typeCrypto.getSymbolCrypto());
    }
}
