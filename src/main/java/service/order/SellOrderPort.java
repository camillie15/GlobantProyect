package service.order;

import model.order.SellOrder;

import java.util.List;

public interface SellOrderPort {
    List<SellOrder> getSellOrders();
}
