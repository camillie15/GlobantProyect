package model.wallet;

import model.crypto.Crypto;

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

    /**
     * This method returns the cash, the fiat money
     * @return fiat money
     */
    public BigDecimal getBalanceCash() {
        return balanceCash;
    }

    /**
     * This method returns the list of cryptos
     * @return list of cryptos
     */
    public List<Crypto> getCryptoList() {
        return cryptoList;
    }

    /**
     * This method receives the crypto type and searches the crypto list for a crypto matching the parameter
     * @param typeCrypto type of crypto to search
     * @return crypto whose name is equal to the parameter
     */
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

    /**
     * This method add to the fiat money the amount that receives
     * @param amount amount to deposit
     */
    public void deposit(BigDecimal amount){
        this.balanceCash = balanceCash.add(amount);
    }

    /**
     * This method process the buy of a crypto in the fiat money, discounting the price from the fiat money
     * @param price price to discounting
     */
    public void buyCrypto(BigDecimal price) {
        this.balanceCash = balanceCash.subtract(price);
    }

    /**
     * This method process the sell of a crypto in the fiat money, adding the price from the fiat money
     * @param price price to adding
     */
    public void sellCrypto(BigDecimal price) {
        this.balanceCash = balanceCash.add(price);
    }

}
