package service.order;

import model.crypto.TypeCrypto;
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

    @Override
    public List<SellOrder> getSellOrders() {
        return new ArrayList<>(sellOrders.values());
    }
}

