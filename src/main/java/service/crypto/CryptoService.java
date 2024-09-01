package service.crypto;

import model.crypto.Crypto;
import model.crypto.TypeCrypto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CryptoService {
    private final Map<String, Crypto> cryptos = new HashMap<>();

    public CryptoService() {
        cryptos.put("BTC", new Crypto(TypeCrypto.BITCOIN, BigDecimal.ZERO));
        cryptos.put("ETH", new Crypto(TypeCrypto.ETHEREUM, BigDecimal.ZERO));
    }

    /**
     * This method returns a list of cryptos from the Map.
     * @return list of cryptos
     */
    public List<Crypto> getCryptos(){
        return new ArrayList<>(cryptos.values());
    }


}
