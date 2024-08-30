package service.order;

import model.crypto.TypeCrypto;
import model.order.BuyOrder;
import model.order.SellOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SellOrdersService implements SellOrderPort{
    private final LinkedHashMap<String, SellOrder> sellOrders= new LinkedHashMap<>();


    public SellOrdersService() {}
    private  static  SellOrdersService sellOrdersInstance;

    public static SellOrdersService getSellOrdersInstance(){
        if(sellOrdersInstance == null){
            sellOrdersInstance = new SellOrdersService();
        }
        return sellOrdersInstance;
    }
    public int getTotalOrders(){
        return sellOrders.size();
    }
    @Override
    public SellOrder createSellOrder(String idUser, TypeCrypto typeCrypto, BigDecimal amountTraded, BigDecimal price) {
        SellOrder sellOrder = null;
        String idOrder = "S" + (getTotalOrders() + 1);
        sellOrder = new SellOrder(idOrder, idUser, typeCrypto, amountTraded, price);
        return sellOrder;
    }

    @Override
    public void addToSellOrderBook(SellOrder sellOrder) {
        sellOrders.put(sellOrder.getIdOrder(), sellOrder);
    }

    @Override
    public List<SellOrder> getSellOrders() {
        return new ArrayList<>(sellOrders.values());
    }
}

