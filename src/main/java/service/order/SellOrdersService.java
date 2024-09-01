package service.order;

import model.crypto.TypeCrypto;
import model.order.SellOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SellOrdersService implements SellOrderPort{
    private final LinkedHashMap<String, SellOrder> sellOrders= new LinkedHashMap<>();

    private  static  SellOrdersService sellOrdersInstance;

    /**
     * This method returns an instance of SellOrdersService where if the instance is null it creates one,
     * otherwise it only returns the instance already created.
     * @return an instance of SellOrdersService
     */
    public static SellOrdersService getSellOrdersInstance(){
        if(sellOrdersInstance == null){
            sellOrdersInstance = new SellOrdersService();
        }
        return sellOrdersInstance;
    }

    /**
     * This method creates an id order and an order with the received parameters.
     * @param idUser id of the user who generates the order
     * @param typeCrypto type of crypto that the user wants to sell
     * @param amountTraded amount that the user wants to sell
     * @param price price that the user is willing to accept for his buy order
     * @return sell order created
     */
    @Override
    public SellOrder createSellOrder(String idUser, TypeCrypto typeCrypto, BigDecimal amountTraded, BigDecimal price) {
        SellOrder sellOrder;
        String idOrder = "S" + (sellOrders.size() + 1);
        sellOrder = new SellOrder(idOrder, idUser, typeCrypto, amountTraded, price);
        return sellOrder;
    }

    /**
     * This method add the sell order to the sell order book (LinkedHashMap to maintain the order in which they are added).
     * @param sellOrder sell order that will be added to the book
     */
    @Override
    public void addToSellOrderBook(SellOrder sellOrder) {
        sellOrders.put(sellOrder.getIdOrder(), sellOrder);
    }

    /**
     * This method returns the list of sell orders.
     * @return list of sell orders
     */
    @Override
    public List<SellOrder> getSellOrders() {
        return new ArrayList<>(sellOrders.values());
    }
}

