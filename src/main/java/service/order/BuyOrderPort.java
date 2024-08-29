package service.order;

import model.crypto.TypeCrypto;
import model.order.BuyOrder;

import java.math.BigDecimal;
import java.util.List;

public interface BuyOrderPort {
    BuyOrder createBuyOrder(String idUser, TypeCrypto typeCrypto, BigDecimal amountCrypto, BigDecimal priceOffered);
    void addToBuyOrderBook(BuyOrder buyOrder);
    List<BuyOrder> getBuyOrders();
}
