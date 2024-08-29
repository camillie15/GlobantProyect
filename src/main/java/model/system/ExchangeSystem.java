package model.system;

import model.crypto.Crypto;
import model.order.BuyOrder;
import model.order.SellOrder;

import java.util.List;

public class ExchangeSystem {
    List<Crypto> cryptos;
    List<BuyOrder> buyOrders;
    List<SellOrder> sellOrders;

    public ExchangeSystem(List<Crypto> cryptos, List<BuyOrder> buyOrders, List<SellOrder> sellOrders) {
        this.cryptos = cryptos;
        this.buyOrders = buyOrders;
        this.sellOrders = sellOrders;
    }
}
