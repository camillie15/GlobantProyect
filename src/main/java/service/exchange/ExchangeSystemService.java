package service.exchange;

import model.crypto.Crypto;
import model.crypto.TypeCrypto;
import model.system.ExchangeSystem;
import service.user.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeSystemService {
    private final Map<String, Crypto> cryptosExchange = new HashMap<>();
    private final UserService userService;

    private static ExchangeSystemService exchangeInstance;

    private ExchangeSystemService(UserService userService) {
        this.userService = userService;
        cryptosExchange.put("Bitcoin", new Crypto(TypeCrypto.BITCOIN, new BigDecimal("100"), new BigDecimal("50000.00")));
        cryptosExchange.put("Ethereum", new Crypto(TypeCrypto.ETHEREUM, new BigDecimal("500"), new BigDecimal("3000.00")));

        ExchangeSystem exchange = new ExchangeSystem(getCryptosExchange(), new ArrayList<>(), new ArrayList<>());
    }

    public static ExchangeSystemService getExchangeInstance(UserService userService){
        if (exchangeInstance == null){
            exchangeInstance = new ExchangeSystemService(userService);
        }
        return exchangeInstance;
    }

    public List<Crypto> getCryptosExchange() {
        return new ArrayList<>(cryptosExchange.values());
    }
}
