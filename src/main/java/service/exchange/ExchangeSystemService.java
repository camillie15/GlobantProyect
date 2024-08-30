package service.exchange;

import model.crypto.Crypto;
import model.crypto.TypeCrypto;
import model.exceptions.EmptyListException;
import model.exceptions.InsufficientCryptoException;
import model.exceptions.InsufficientFiatMoney;
import model.order.BuyOrder;
import model.order.SellOrder;
import model.system.ExchangeSystem;
import service.order.BuyOrdersService;
import service.order.SellOrdersService;
import service.user.UserService;
import service.wallet.WalletService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeSystemService implements ExchangeSystemPort{
    private final Map<String, Crypto> cryptosExchange = new HashMap<>();
    private final UserService userService;

    private final BuyOrdersService buyOrdersService;
    private final SellOrdersService sellOrdersService;

    private static ExchangeSystemService exchangeInstance;

    private ExchangeSystemService(UserService userService) {
        this.userService = userService;
        cryptosExchange.put("Bitcoin", new Crypto(TypeCrypto.BITCOIN, new BigDecimal("100"), new BigDecimal("50000.00")));
        cryptosExchange.put("Ethereum", new Crypto(TypeCrypto.ETHEREUM, new BigDecimal("500"), new BigDecimal("3000.00")));

        this.buyOrdersService = BuyOrdersService.getBuyOrdersInstance();
        this.sellOrdersService = SellOrdersService.getSellOrdersInstance();

        ExchangeSystem exchange = new ExchangeSystem(getCryptosExchange(), buyOrdersService.getBuyOrders(), sellOrdersService.getSellOrders());
    }

    public static ExchangeSystemService getExchangeInstance(UserService userService){
        if (exchangeInstance == null){
            exchangeInstance = new ExchangeSystemService(userService);
        }
        return exchangeInstance;
    }

    public List<SellOrder> getSellOrders() {
        if(!sellOrdersService.getSellOrders().isEmpty()){
            return sellOrdersService.getSellOrders();
        }else {
            throw new EmptyListException();
        }
    }

    public List<BuyOrder> getBuyOrders() {
        if(!buyOrdersService.getBuyOrders().isEmpty()){
            return buyOrdersService.getBuyOrders();
        }else {
            throw new EmptyListException();
        }
    }

    @Override
    public void buyToExchange(TypeCrypto typeCrypto, BigDecimal amountTraded, String idUser, WalletService walletService) {
        Crypto crypto = cryptosExchange.get(typeCrypto.getNameCrypto());
        BigDecimal totalPay = crypto.getPriceCrypto().multiply(amountTraded);
        if (crypto.getAmountCrypto().compareTo(amountTraded) >= 0){
            if (walletService.getWallet().getBalanceCash().compareTo(totalPay) >= 0) {
                BuyOrder buyOrder = buyOrdersService.createBuyOrder(idUser, crypto.getTypeCrypto(), amountTraded, totalPay);
                walletService.buyCrypto(buyOrder);

                crypto.sellCrypto(amountTraded);
            } else {
                throw new InsufficientFiatMoney();
            }
        } else {
            throw new InsufficientCryptoException();
        }
    }

    @Override
    public List<Crypto> getCryptosExchange() {
        return new ArrayList<>(cryptosExchange.values());
    }

    @Override
    public void processBuyOrder(BuyOrder buyOrder) {
        for(SellOrder sellOrder : getSellOrders()){
            if(!sellOrder.isProcessedOrder() && !sellOrder.getIdUser().equals(buyOrder.getIdUser())){
                if(sellOrder.getAmountTraded().compareTo(buyOrder.getAmountTraded()) == 0 && sellOrder.getPrice().compareTo(buyOrder.getPrice()) <= 0){

                    WalletService userBuyWallet = new WalletService(userService.getUserById(buyOrder.getIdUser()));
                    userBuyWallet.buyCrypto(sellOrder);
                    buyOrder.orderProcessed();

                    WalletService userSellWallet = new WalletService(userService.getUserById(sellOrder.getIdUser()));
                    userSellWallet.sellCrypto(sellOrder);
                    sellOrder.orderProcessed();

                    System.out.println("\u001B[32m\t>> Buy Order successfully processed <<\nCrypto and fiat money in your wallet has been updated\u001B[0m");

                    break;
                }
            }
        }
        if(!buyOrder.isProcessedOrder()){
            System.out.println("\u001B[34mNo sell orders to match, your order will be process later\u001B[0m");
        }
    }

    public void processSellOrder(SellOrder sellOrder){
        for(BuyOrder buyOrder : getBuyOrders()){
            if (!buyOrder.isProcessedOrder() && !buyOrder.getIdUser().equals(sellOrder.getIdUser())){
                if(buyOrder.getAmountTraded().compareTo(sellOrder.getAmountTraded()) == 0 && buyOrder.getPrice().compareTo(sellOrder.getPrice()) >= 0){

                    WalletService userSellWallet = new WalletService(userService.getUserById(sellOrder.getIdUser()));
                    userSellWallet.sellCrypto(buyOrder);
                    sellOrder.orderProcessed();

                    WalletService userBuyWallet = new WalletService(userService.getUserById(buyOrder.getIdUser()));
                    userBuyWallet.buyCrypto(buyOrder);
                    buyOrder.orderProcessed();

                    System.out.println("\u001B[32m\t>> Sell Order successfully processed <<\nCrypto and fiat money in your wallet has been updated\u001B[0m");

                    break;
                }
            }
        }
        if(!sellOrder.isProcessedOrder()){
            System.out.println("\u001B[34mNo sell orders to match, your order will be process later\u001B[0m");
        }
    }
}
