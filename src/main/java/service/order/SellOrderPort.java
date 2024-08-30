package service.order;

import model.crypto.TypeCrypto;
import model.order.BuyOrder;
import model.order.SellOrder;

import java.math.BigDecimal;
import java.util.List;

public interface SellOrderPort {
    SellOrder createSellOrder(String idUser, TypeCrypto typeCrypto, BigDecimal amountCrypto, BigDecimal priceOffered);
    void addToSellOrderBook(SellOrder sellOrder);
    List<SellOrder> getSellOrders();
}
