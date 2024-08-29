package controller.exchange;

import model.crypto.TypeCrypto;
import model.exceptions.InsufficientCryptoException;
import model.exceptions.InsufficientFiatMoney;
import model.user.User;
import service.exchange.ExchangeSystemService;
import service.wallet.WalletService;
import view.ExchangeSystemView;

import java.math.BigDecimal;

public class BuyToExchangeController {
    private final ExchangeSystemView exchangeSystemView;
    private final ExchangeSystemService exchangeSystemService;
    private final User user;
    private final WalletService walletService;

    public BuyToExchangeController(ExchangeSystemView exchangeSystemView, ExchangeSystemService exchangeSystemService, User user, WalletService walletService) {
        this.exchangeSystemView = exchangeSystemView;
        this.exchangeSystemService = exchangeSystemService;
        this.user = user;
        this.walletService = walletService;
    }

    public void execute(){
        exchangeSystemView.viewExchangeCrypto(exchangeSystemService.getCryptosExchange());
        String typeCrypto = exchangeSystemView.getTypeCryptoInput("Type the cryptocurrency you want to buy: ").toUpperCase();
        BigDecimal amountCrypto = exchangeSystemView.getAmountCryptoInput("Type the amount of cryptocurrencies you want to buy: ");
        try{
            exchangeSystemService.buyToExchange(TypeCrypto.valueOf(typeCrypto), amountCrypto, user.getIdUser(), walletService);
            System.out.println("\u001B[32m\t>> Successful buy <<\u001B[0m");

        } catch (InsufficientFiatMoney e){
            System.out.println("\u001B[31mInsufficient fiat money in your wallet\u001B[0m");
        } catch (InsufficientCryptoException e){
            System.out.println("\u001B[31mInsufficient cryptos in the Crypto Exchange System\u001B[0m");
        }
    }
}


