package model.crypto;

public enum TypeCrypto {
    BITCOIN("Bitcoin", "BTC"),
    ETHEREUM("Ethereum", "ETH");

    private final String nameCrypto;
    private final String symbolCrypto;

    TypeCrypto(String nameCrypto, String symbolCrypto) {
        this.nameCrypto = nameCrypto;
        this.symbolCrypto = symbolCrypto;
    }

    public String getNameCrypto() {
        return nameCrypto;
    }

    public String getSymbolCrypto() {
        return symbolCrypto;
    }
}
