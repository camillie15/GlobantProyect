package model.crypto;

public enum TypeCrypto {
    BITCOIN("001","Bitcoin", "BTC"),
    ETHEREUM("002", "Ethereum", "ETH");

    private final String nameCrypto;
    private final String symbolCrypto;
    private final String idCrypto;

    TypeCrypto(String idCrypto, String nameCrypto, String symbolCrypto) {
        this.nameCrypto = nameCrypto;
        this.symbolCrypto = symbolCrypto;
        this.idCrypto = idCrypto;
    }

    /**
     * This method return the id to the crypto
     * @return id of the crypto
     */
    public String getIdCrypto() {
        return idCrypto;
    }

    /**
     * This method return the name to the crypto
     * @return name of the crypto
     */
    public String getNameCrypto() {
        return nameCrypto;
    }

    /**
     * This method return the symbol to the crypto
     * @return symbol of the crypto
     */
    public String getSymbolCrypto() {
        return symbolCrypto;
    }
}
