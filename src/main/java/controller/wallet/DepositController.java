package controller.wallet;

import model.exceptions.InvalidAmountDepositException;
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

    public void execute(){
        BigDecimal amount = exchangeSystemView.getAmountDepositInput();
        try{
            walletService.depositCash(amount);
            System.out.printf("\u001B[32m\t>> Successful deposit <<\n  Actual fiat in ypur wallet: %s\n\u001B[0m",
                    new DecimalFormat("#,#00.00").format(walletService.getWallet().getBalanceCash()));
        } catch (InvalidAmountDepositException e){
            System.out.println("\u001B[31m" + "Invalid amount. Amount must be greater than 0" + "\u001B[0m");
        }
    }
}
