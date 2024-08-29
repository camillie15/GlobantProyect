package service.exchange;

import model.crypto.Crypto;
import model.crypto.TypeCrypto;
import model.exceptions.InsufficientCryptoException;
import model.exceptions.InsufficientFiatMoney;
import model.order.BuyOrder;
import model.system.ExchangeSystem;
import service.order.BuyOrdersService;
import service.user.UserService;
import service.wallet.WalletService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeSystemService implements ExchangeSystemPort{
    private final Map<String, Crypto> cryptosExchange = new HashMap<>();
    private final UserService userService;

    private final BuyOrdersService buyOrdersService;

    private static ExchangeSystemService exchangeInstance;

    private ExchangeSystemService(UserService userService) {
        this.userService = userService;
        cryptosExchange.put("Bitcoin", new Crypto(TypeCrypto.BITCOIN, new BigDecimal("100"), new BigDecimal("50000.00")));
        cryptosExchange.put("Ethereum", new Crypto(TypeCrypto.ETHEREUM, new BigDecimal("500"), new BigDecimal("3000.00")));

        this.buyOrdersService = BuyOrdersService.getBuyOrdersInstance();

        ExchangeSystem exchange = new ExchangeSystem(getCryptosExchange(), new ArrayList<>(), new ArrayList<>());
    }

    public static ExchangeSystemService getExchangeInstance(UserService userService){
        if (exchangeInstance == null){
            exchangeInstance = new ExchangeSystemService(userService);
        }
        return exchangeInstance;
    }

    @Override
    public void buyToExchange(TypeCrypto typeCrypto, BigDecimal amountTraded, String idUser, WalletService walletService) {
        Crypto crypto = cryptosExchange.get(typeCrypto.getNameCrypto());
        BigDecimal totalPay = crypto.getPriceCrypto().multiply(amountTraded);
        if (crypto.getAmountCrypto().compareTo(amountTraded) >= 0){
            if (walletService.getWallet().getBalanceCash().compareTo(totalPay) >= 0) {
                BuyOrder buyOrder = buyOrdersService.createBuyOrder(idUser, crypto.getTypeCrypto(), amountTraded, totalPay);
                walletService.buyCrypto(buyOrder);

                crypto.sellCrypto(amountTraded);
            } else {
                throw new InsufficientFiatMoney();
            }
        } else {
            throw new InsufficientCryptoException();
        }
    }

    @Override
    public List<Crypto> getCryptosExchange() {
        return new ArrayList<>(cryptosExchange.values());
    }
}