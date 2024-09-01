package controller.order;

import model.crypto.TypeCrypto;
import model.exceptions.EmptyListException;
import model.exceptions.InsufficientCryptoException;
import model.order.SellOrder;
import model.user.User;
import service.exchange.ExchangeSystemService;
import service.order.SellOrdersService;
import service.wallet.WalletService;
import view.ExchangeSystemView;

import java.math.BigDecimal;

public class SellOrderController {
    private final ExchangeSystemView exchangeSystemView;
    private final WalletService walletService;
    private final User user;
    private final ExchangeSystemService exchangeSystemService;

    private final SellOrdersService sellOrdersService;

    public SellOrderController(ExchangeSystemView exchangeSystemView, User user, ExchangeSystemService exchangeSystemService, WalletService walletService) {
        this.exchangeSystemView = exchangeSystemView;
        this.walletService = walletService;
        this.user = user;
        this.sellOrdersService = SellOrdersService.getSellOrdersInstance();
        this.exchangeSystemService = exchangeSystemService;
    }

    /**
     * This method executes a series of processes, such as requesting data from the user for the type and amount of crypto
     * and the minimum value to accept for that order, then compares whether the cryptos to sell is in the wallet, and
     * then calls methods that create the order, add it to the sell order book and process the order.
     */
    public void execute(){
        String typeCrypto = exchangeSystemView.getTypeCryptoInput("Type the cryptocurrency you want to sell: ").toUpperCase();
        BigDecimal amountCrypto = exchangeSystemView.getAmountCryptoInput("Type the amount of cryptocurrencies you want to sell (Use ',' for decimals): ");
        BigDecimal priceOffered = exchangeSystemView.getPriceCryptoInput("Type the minimum $$ to accept (Use ',' for decimals): ");
        try{
            BigDecimal sellOrdersCryptos = exchangeSystemService.getCryptosInSellOrders(user.getIdUser(), TypeCrypto.valueOf(typeCrypto));
            if(sellOrdersCryptos.add(amountCrypto).compareTo(walletService.getWallet().getCrypto(typeCrypto).getAmountCrypto()) <= 0){
                SellOrder sellOrder = sellOrdersService.createSellOrder(user.getIdUser(), TypeCrypto.valueOf(typeCrypto), amountCrypto, priceOffered);
                sellOrdersService.addToSellOrderBook(sellOrder);
                exchangeSystemService.processSellOrder(sellOrder);
            }else{
                throw new InsufficientCryptoException();
            }
        }catch (InsufficientCryptoException e){
            System.out.println("\u001B[31mInsufficient cryptos\u001B[0m");
        }catch (EmptyListException e){
            System.out.println("\u001B[34mNo buy orders registered, your order will be process later\u001B[0m");
        }
    }
}
