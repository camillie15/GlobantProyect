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
    @Override
    public void depositCash(BigDecimal amount) {
        wallet.deposit(amount);
    }

    @Override
    public Wallet getWallet() {
        return wallet;
    }

    @Override
    public void buyCrypto(Order order) {
        for(Crypto crypto : getWallet().getCryptoList()) {
            if (crypto.getTypeCrypto().getNameCrypto().equalsIgnoreCase(order.getTypeCrypto().getNameCrypto())) {
                crypto.buyCrypto(order.getAmountTraded());
                wallet.buyCrypto(order.getPrice());
                order.orderProcessed();
                break;
            }
        }
    }

    @Override
    public void sellCrypto(Order order) {
        for(Crypto crypto : getWallet().getCryptoList()) {
            if (crypto.getTypeCrypto().getNameCrypto().equalsIgnoreCase(order.getTypeCrypto().getNameCrypto())) {
                crypto.sellCrypto(order.getAmountTraded());
                wallet.sellCrypto(order.getPrice());
                order.orderProcessed();
                break;
            }
        }
    }
}
