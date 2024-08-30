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

    public String getIdCrypto() {
        return idCrypto;
    }

    public String getNameCrypto() {
        return nameCrypto;
    }

    public String getSymbolCrypto() {
        return symbolCrypto;
    }
}
