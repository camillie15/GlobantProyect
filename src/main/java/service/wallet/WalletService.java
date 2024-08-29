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
}
