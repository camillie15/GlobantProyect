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
import service.transaction.TransactionService;
import service.user.UserService;
import service.wallet.WalletService;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExchangeSystemService implements ExchangeSystemPort {
    private final Map<String, Crypto> cryptosExchange = new HashMap<>();
    private final UserService userService;

    private final BuyOrdersService buyOrdersService;
    private final SellOrdersService sellOrdersService;

    ScheduledExecutorService priceFluctuate;

    private static ExchangeSystemService exchangeInstance;

    private ExchangeSystemService(UserService userService) {
        this.userService = userService;
        cryptosExchange.put("Bitcoin", new Crypto(TypeCrypto.BITCOIN, new BigDecimal("100"), new BigDecimal("50000.00")));
        cryptosExchange.put("Ethereum", new Crypto(TypeCrypto.ETHEREUM, new BigDecimal("500"), new BigDecimal("3000.00")));

        this.buyOrdersService = BuyOrdersService.getBuyOrdersInstance();
        this.sellOrdersService = SellOrdersService.getSellOrdersInstance();

        ExchangeSystem exchangeSystem = new ExchangeSystem(getCryptosExchange(), buyOrdersService.getBuyOrders(), sellOrdersService.getSellOrders());

        priceFluctuate = Executors.newScheduledThreadPool(1);
        priceFluctuate.scheduleAtFixedRate(fluctuateMarket, 0, 10000, TimeUnit.MILLISECONDS);
    }

    /**
     * This method returns an instance of ExchangeSystemService where if the instance is null it creates one,
     * otherwise it only returns the instance already created.
     * @param userService userService contains methods that create users and add them to the list of users in the system.
     * @return an instance of ExchangeSystemService
     */
    public static ExchangeSystemService getExchangeInstance(UserService userService){
        if (exchangeInstance == null){
            exchangeInstance = new ExchangeSystemService(userService);
        }
        return exchangeInstance;
    }

    /**
     * This method returns the list of sell orders registered in SellOrdersService.
     * @return list of sell orders
     */
    public List<SellOrder> getSellOrders() {
        if(!sellOrdersService.getSellOrders().isEmpty()){
            return sellOrdersService.getSellOrders();
        }else {
            throw new EmptyListException();
        }
    }

    /**
     * This method returns the list of buy orders registered in BuyOrdersService.
     * @return list of buy orders
     */
    public List<BuyOrder> getBuyOrders() {
        if(!buyOrdersService.getBuyOrders().isEmpty()){
            return buyOrdersService.getBuyOrders();
        }else {
            throw new EmptyListException();
        }
    }

    /**
     * This method returns the list of cryptos of the exchange.
     * @return list of cryptos
     */
    @Override
    public List<Crypto> getCryptosExchange() {
        return new ArrayList<>(cryptosExchange.values());
    }

    /**
     *This method process the buy to the exchange, validates that the value to pay is in the wallet together with the value
     * to pay for other registered buy orders, once this is validated, the buy order is created, the value to pay
     * is discounted from the wallet, the transaction is created with the buy action, and the cryptos are discounted from the exchange.
     * @param typeCrypto type crypto to buy
     * @param amountTraded amount of crypto to buy
     * @param idUser id of the user who is going to buy from the exchange
     * @param walletService service that process and manipulate the wallet
     */
    @Override
    public void buyToExchange(TypeCrypto typeCrypto, BigDecimal amountTraded, String idUser, WalletService walletService) {
        Crypto crypto = cryptosExchange.get(typeCrypto.getNameCrypto());
        BigDecimal buyOrdersFiat = getFiatInBuyOrders(idUser);
        BigDecimal totalPay = crypto.getPriceCrypto().multiply(amountTraded);
        if (crypto.getAmountCrypto().compareTo(amountTraded) >= 0){
            if (walletService.getWallet().getBalanceCash().compareTo(buyOrdersFiat.add(totalPay)) >= 0) {
                BuyOrder buyOrder = buyOrdersService.createBuyOrder(idUser, crypto.getTypeCrypto(), amountTraded, totalPay);
                walletService.buyCrypto(buyOrder);
                buyOrder.orderProcessed();
                TransactionService transactionService = new TransactionService(userService.getUserById(buyOrder.getIdUser()));
                transactionService.createTransaction(buyOrder, "Buy");

                crypto.sellCrypto(amountTraded);
            } else {
                throw new InsufficientFiatMoney();
            }
        } else {
            throw new InsufficientCryptoException();
        }
    }

    /**
     * This method returns the maximum to pay for the user's registered buy orders, validates that the buy order has not been
     * process and adds the maximum to pay for that order to the cryptosOrder variable.
     * @param idUser id of the user from whom all buy orders are to be taken
     * @return the total of fiat money to pay for registered buy orders that have not been processed
     */
    public BigDecimal getFiatInBuyOrders(String idUser){
        BigDecimal cryptosOrder = new BigDecimal("0");
        for(BuyOrder buyOrder : buyOrdersService.getBuyOrders()){
            if(buyOrder.getIdUser().equals(idUser)){
                if(!buyOrder.isProcessedOrder()){
                    cryptosOrder = cryptosOrder.add(buyOrder.getPrice());
                }
            }
        }
        return cryptosOrder;
    }

    /**
     * This method reviews each registered sell order, if it coincides in the total crypto,the value to pay and the type of crypto,
     * the user who generated the buy order is discounted from the fiat money the value to be accepted for the sell order and
     * the total of cryptos is added to the wallet and the user who generated the sell order is discounted the total of cryptos
     * and the value to be accepted for his order is added to the fiat money.
     * @param buyOrder buy order to process
     */
    @Override
    public void processBuyOrder(BuyOrder buyOrder) {
        for(SellOrder sellOrder : getSellOrders()){
            if(!sellOrder.isProcessedOrder() && !sellOrder.getIdUser().equals(buyOrder.getIdUser())){
                if(sellOrder.getTypeCrypto() == buyOrder.getTypeCrypto() && sellOrder.getAmountTraded().compareTo(buyOrder.getAmountTraded()) == 0 && sellOrder.getPrice().compareTo(buyOrder.getPrice()) <= 0){

                    WalletService userBuyWallet = new WalletService(userService.getUserById(buyOrder.getIdUser()));
                    userBuyWallet.buyCrypto(sellOrder);
                    buyOrder.orderProcessed();
                    TransactionService transactionServiceBuy = new TransactionService(userService.getUserById(buyOrder.getIdUser()));
                    transactionServiceBuy.createTransaction(sellOrder, "Buy");

                    WalletService userSellWallet = new WalletService(userService.getUserById(sellOrder.getIdUser()));
                    userSellWallet.sellCrypto(sellOrder);
                    sellOrder.orderProcessed();
                    TransactionService transactionServiceSell = new TransactionService(userService.getUserById(sellOrder.getIdUser()));
                    transactionServiceSell.createTransaction(sellOrder, "Sell");

                    System.out.println("\u001B[32m\t>> Buy Order successfully processed <<\nCrypto and fiat money in your wallet has been updated\u001B[0m");

                    break;
                }
            }
        }
        if(!buyOrder.isProcessedOrder()){
            System.out.println("\u001B[34mNo sell orders to match, your order will be process later\u001B[0m");
        }
    }

    /**
     * This method reviews each registered buy order, if it coincides in the total cryptocurrency, the value to pay and the type of
     * cryptocurrency, the user who generated the sell order is discounted the total cryptocurrency of the order and the maximum value to pay
     * for the buy order is added to the fiat money and the user who generated the buy order is added the total cryptocurrency
     * and the maximum value to pay for his order is discounted from the fiat money.
     * @param sellOrder sell order to process
     */
    @Override
    public void processSellOrder(SellOrder sellOrder) {
        for(BuyOrder buyOrder : getBuyOrders()){
            if (!buyOrder.isProcessedOrder() && !buyOrder.getIdUser().equals(sellOrder.getIdUser())){
                if(buyOrder.getTypeCrypto() == sellOrder.getTypeCrypto() && buyOrder.getAmountTraded().compareTo(sellOrder.getAmountTraded()) == 0 && buyOrder.getPrice().compareTo(sellOrder.getPrice()) >= 0){

                    WalletService userSellWallet = new WalletService(userService.getUserById(sellOrder.getIdUser()));
                    userSellWallet.sellCrypto(buyOrder);
                    sellOrder.orderProcessed();
                    TransactionService transactionServiceSell = new TransactionService(userService.getUserById(sellOrder.getIdUser()));
                    transactionServiceSell.createTransaction(buyOrder, "Sell");

                    WalletService userBuyWallet = new WalletService(userService.getUserById(buyOrder.getIdUser()));
                    userBuyWallet.buyCrypto(buyOrder);
                    buyOrder.orderProcessed();
                    TransactionService transactionServiceBuy = new TransactionService(userService.getUserById(buyOrder.getIdUser()));
                    transactionServiceBuy.createTransaction(buyOrder, "Buy");

                    System.out.println("\u001B[32m\t>> Sell Order successfully processed <<\nCrypto and fiat money in your wallet has been updated\u001B[0m");

                    break;
                }
            }
        }
        if(!sellOrder.isProcessedOrder()){
            System.out.println("\u001B[34mNo buy orders to match, your order will be process later\u001B[0m");
        }
    }

    /**
     * This method returns the total cryptos of the sell orders registered by the user, validates that the sell order
     * has not been processed and adds the amount of cryptos of that order to the cryptosOrder variable.
     * @param idUser id of the user from whom all sell orders are to be taken
     * @param crypto type of crypto of the order to be taken
     * @return the total of cryptos for registered sell orders that have not been processed
     */
    public BigDecimal getCryptosInSellOrders(String idUser, TypeCrypto crypto){
        BigDecimal cryptosOrder = new BigDecimal("0");
        for (SellOrder sellOrder : sellOrdersService.getSellOrders()){
            if(sellOrder.getIdUser().equals(idUser) && sellOrder.getTypeCrypto().equals(crypto)){
                if(!sellOrder.isProcessedOrder()){
                    cryptosOrder = cryptosOrder.add(sellOrder.getAmountTraded());
                }
            }
        }
        return cryptosOrder;
    }

    /**
     * Implements Runnable interface
     */
    Runnable fluctuateMarket =() -> {
        fluctuatePrice(cryptosExchange.get("Bitcoin"), generateRandom(new BigDecimal("0.00"), new BigDecimal("1.00")));
        fluctuatePrice(cryptosExchange.get("Ethereum"), generateRandom(new BigDecimal("0.00"), new BigDecimal("1.00")));
    };

    /**
     * This method generates a BigDecimal random number.
     * @param min range minimum
     * @param max range maximum
     * @return random number
     */
    public static BigDecimal generateRandom(BigDecimal min, BigDecimal max) {
        return min.add(BigDecimal.valueOf(Math.random()).multiply(max.subtract(min)));
    }

    /**
     * This method selects an action from the list and depending on this the value of the crypto increases or decreases.
     * @param crypto crypto whose value will vary
     * @param variation value to be increased or decreased
     */
    public void fluctuatePrice(Crypto crypto, BigDecimal variation){
        List<String> listAction = new ArrayList<>();
        listAction.add("add");
        listAction.add("subtract");

        String action = listAction.get(new Random().nextInt(listAction.size()));
        if (action.equals("add")){
            crypto.addPrice(variation);
        }else if (action.equals("subtract")){
            crypto.subtractPrice(variation);
        }
    }
}
