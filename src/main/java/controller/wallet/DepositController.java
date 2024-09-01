package controller.wallet;

import service.wallet.WalletService;
import view.ExchangeSystemView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DepositController {
    private final ExchangeSystemView exchangeSystemView;
    private final WalletService walletService;

    public DepositController(ExchangeSystemView exchangeSystemView, WalletService walletService) {
        this.exchangeSystemView = exchangeSystemView;
        this.walletService = walletService;
    }

    /**
     * This method calls a series of methods that request the amount to deposit and a method
     * from the wallet service that process the deposit to the wallet.
     */
    public void execute(){
        BigDecimal amount = exchangeSystemView.getAmountDepositInput();
        walletService.depositCash(amount);
        System.out.printf("\u001B[32m\t>> Successful deposit <<\nActual fiat in your wallet: %s\n\u001B[0m",
                new DecimalFormat("#,#00.00").format(walletService.getWallet().getBalanceCash()));
    }
}
