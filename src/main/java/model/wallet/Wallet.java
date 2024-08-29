package model.wallet;

import model.crypto.Crypto;
import model.exceptions.InvalidAmountDepositException;
import service.crypto.CryptoService;

import java.math.BigDecimal;
import java.util.List;

public class Wallet {
    private BigDecimal balanceCash;
    private List<Crypto> cryptoList;

    private CryptoService cryptoService = new CryptoService();

    public Wallet() {
        this.balanceCash = BigDecimal.ZERO;
        this.cryptoList = cryptoService.getCryptos();
    }

    public BigDecimal getBalanceCash() {
        return balanceCash;
    }

    public List<Crypto> getCryptoList() {
        return cryptoList;
    }

    public Crypto getCrypto(String typeCrypto){
        Crypto cryptoFind = null;
        for(Crypto crypto : cryptoList){
            if(crypto.getTypeCrypto().getNameCrypto().equalsIgnoreCase(typeCrypto)){
                cryptoFind = crypto;
                break;
            }
        }
        return cryptoFind;
    }

    public void deposit(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) > 0){
            this.balanceCash = balanceCash.add(amount);
        }else {
            throw new InvalidAmountDepositException();
        }
    }

    public void buyCrypto(BigDecimal priceOffered) {
        this.balanceCash = balanceCash.subtract(priceOffered);
    }

    public void sellCrypto(BigDecimal priceOffered) {
        this.balanceCash = balanceCash.add(priceOffered);
    }

}
