package model.order;

import model.crypto.TypeCrypto;

import java.math.BigDecimal;

public class SellOrder extends Order{

    public SellOrder(String idOrder, String idUser, TypeCrypto typeCrypto, BigDecimal amountTraded, BigDecimal price) {
        super(idOrder, idUser, typeCrypto, amountTraded, price);
    }
}
