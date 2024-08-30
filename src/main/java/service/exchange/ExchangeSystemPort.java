package service.exchange;

import model.crypto.Crypto;
import model.crypto.TypeCrypto;
import model.order.BuyOrder;
import service.wallet.WalletService;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeSystemPort {
    void buyToExchange(TypeCrypto typeCrypto, BigDecimal amountTraded, String idUser, WalletService walletService);
    List<Crypto> getCryptosExchange();
    void processBuyOrder(BuyOrder buyOrder);
    }
