package service.order;

import model.crypto.TypeCrypto;
import model.order.BuyOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BuyOrdersService implements BuyOrderPort {
    private final LinkedHashMap<String, BuyOrder> buyOrders= new LinkedHashMap<>();

    private  static BuyOrdersService buyOrdersInstance;

    public static BuyOrdersService getBuyOrdersInstance(){
        if(buyOrdersInstance == null){
            buyOrdersInstance = new BuyOrdersService();
        }
        return buyOrdersInstance;
    }

    public int getTotalOrders(){
        return buyOrders.size();
    }

    @Override
    public BuyOrder createBuyOrder(String idUser, TypeCrypto typeCrypto, BigDecimal amountTraded, BigDecimal price) {
        BuyOrder buyOrder = null;
        String idOrder = "B" + (getTotalOrders() + 1);
        buyOrder = new BuyOrder(idOrder, idUser, typeCrypto, amountTraded, price);
        return buyOrder;
    }

    @Override
    public void addToBuyOrderBook(BuyOrder buyOrder) {
        buyOrders.put(buyOrder.getIdOrder(), buyOrder);
    }

    @Override
    public List<BuyOrder> getBuyOrders() {
        return new ArrayList<>(buyOrders.values());
    }
}
