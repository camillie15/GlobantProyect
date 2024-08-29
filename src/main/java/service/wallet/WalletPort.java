package service.wallet;

import model.order.Order;
import model.wallet.Wallet;

import java.math.BigDecimal;

public interface WalletPort {
    void depositCash(BigDecimal amount);
    Wallet getWallet();
    void buyCrypto(Order order);
}
