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

    /**
     * This method returns an instance of BuyOrdersService where if the instance is null it creates one,
     * otherwise it only returns the instance already created.
     * @return an instance of BuyOrdersService
     */
    public static BuyOrdersService getBuyOrdersInstance(){
        if(buyOrdersInstance == null){
            buyOrdersInstance = new BuyOrdersService();
        }
        return buyOrdersInstance;
    }

    /**
     * This method creates an id order and an order with the received parameters.
     * @param idUser id of the user who generates the order
     * @param typeCrypto type of crypto that the user wants to buy
     * @param amountTraded amount that the user wants to buy
     * @param price price that the user is willing to pay for his buy order
     * @return buy order created
     */
    @Override
    public BuyOrder createBuyOrder(String idUser, TypeCrypto typeCrypto, BigDecimal amountTraded, BigDecimal price) {
        BuyOrder buyOrder;
        String idOrder = "B" + (buyOrders.size() + 1);
        buyOrder = new BuyOrder(idOrder, idUser, typeCrypto, amountTraded, price);
        return buyOrder;
    }

    /**
     * This method add the buy order to the buy order book (LinkedHashMap to maintain the order in which they are added).
     * @param buyOrder buy order that will be added to the book
     */
    @Override
    public void addToBuyOrderBook(BuyOrder buyOrder) {
        buyOrders.put(buyOrder.getIdOrder(), buyOrder);
    }

    /**
     * This method returns the list of buy orders.
     * @return list of buy orders
     */
    @Override
    public List<BuyOrder> getBuyOrders() {
        return new ArrayList<>(buyOrders.values());
    }
}
