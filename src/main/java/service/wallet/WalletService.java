package service.wallet;

import model.crypto.Crypto;
import model.order.Order;
import model.user.User;
import model.wallet.Wallet;

import java.math.BigDecimal;

public class WalletService implements WalletPort{
    private final Wallet wallet;

    public WalletService(User user) {
        this.wallet = user.getWallet();
    }

    /**
     * This method call the wallet method to deposit the fiat money.
     * @param amount amount to deposit
     */
    @Override
    public void depositCash(BigDecimal amount) {
        wallet.deposit(amount);
    }

    /**
     * This method returns the wallet.
     * @return a wallet
     */
    @Override
    public Wallet getWallet() {
        return wallet;
    }

    /**
     * This method calls all the methods that handles the buy of cryptos as the discount of the wallet and
     * the increase of the cryptos in the wallet.
     * @param order order that was processed and from which the data will be taken
     */
    @Override
    public void buyCrypto(Order order) {
        for(Crypto crypto : getWallet().getCryptoList()) {
            if (crypto.getTypeCrypto().getNameCrypto().equalsIgnoreCase(order.getTypeCrypto().getNameCrypto())) {
                crypto.buyCrypto(order.getAmountTraded());
                wallet.buyCrypto(order.getPrice());
                break;
            }
        }
    }

    /**
     * This method calls all the methods that handles the sell of cryptos as the increase of the wallet and
     * the discount of the cryptos in the wallet.
     * @param order order that was processed and from which the data will be taken
     */
    @Override
    public void sellCrypto(Order order) {
        for(Crypto crypto : getWallet().getCryptoList()) {
            if (crypto.getTypeCrypto().getNameCrypto().equalsIgnoreCase(order.getTypeCrypto().getNameCrypto())) {
                crypto.sellCrypto(order.getAmountTraded());
                wallet.sellCrypto(order.getPrice());
                break;
            }
        }
    }
}
