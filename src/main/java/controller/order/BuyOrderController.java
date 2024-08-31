package controller.order;

import model.crypto.TypeCrypto;
import model.exceptions.EmptyListException;
import model.exceptions.InsufficientFiatMoney;
import model.order.BuyOrder;
import model.user.User;
import service.exchange.ExchangeSystemService;
import service.order.BuyOrdersService;
import service.wallet.WalletService;
import view.ExchangeSystemView;

import java.math.BigDecimal;

public class BuyOrderController {
    private final ExchangeSystemView exchangeSystemView;
    private final User user;
    private final WalletService walletService;
    private final BuyOrdersService buyOrdersService;
    private final ExchangeSystemService exchangeSystemService;

    public BuyOrderController(ExchangeSystemView exchangeSystemView, User user, ExchangeSystemService exchangeSystemService, WalletService walletService) {
        this.exchangeSystemView = exchangeSystemView;
        this.walletService = walletService;
        this.user = user;
        this.buyOrdersService = BuyOrdersService.getBuyOrdersInstance();
        this.exchangeSystemService = exchangeSystemService;
    }

    public void execute(){
        String typeCrypto = exchangeSystemView.getTypeCryptoInput("Type the cryptocurrency you want to buy: ").toUpperCase();
        BigDecimal amountCrypto = exchangeSystemView.getAmountCryptoInput("Type the amount of cryptocurrencies you want to buy (Use ',' for decimals): ");
        BigDecimal priceOffered = exchangeSystemView.getPriceCryptoInput("Type the maximum $$ to pay (Use ',' for decimals): ");
        try{
            BigDecimal buyOrdersFiat = exchangeSystemService.getFiatInBuyOrders(user.getIdUser());
            if(walletService.getWallet().getBalanceCash().compareTo(buyOrdersFiat.add(priceOffered)) >= 0){
                BuyOrder buyOrder = buyOrdersService.createBuyOrder(user.getIdUser(), TypeCrypto.valueOf(typeCrypto), amountCrypto, priceOffered);
                buyOrdersService.addToBuyOrderBook(buyOrder);
                exchangeSystemService.processBuyOrder(buyOrder);
            }else {
                throw new InsufficientFiatMoney();
            }
        }catch (InsufficientFiatMoney e){
            System.out.println("\u001B[31mInsufficient fiat money in your wallet, you place others buy orders\u001B[0m");
        }catch (EmptyListException e){
            System.out.println("\u001B[34mNo sell orders registered, your order will be process later\u001B[0m");
        }
    }

}
