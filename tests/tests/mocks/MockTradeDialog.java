package tests.mocks;

import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;
import monopoly.TradeDeal;
import monopoly.TradeDialog;

public class MockTradeDialog implements TradeDialog {

    @Override
    public TradeDeal getTradeDeal(MainController mainController) {
        Cell property = mainController.getGameBoard().getCell(1);
        Player buyer = mainController.getPlayer(1);
        int dealAmount = 200;
        
        TradeDeal deal = new TradeDeal(property, buyer, dealAmount);
        return deal;
    }
}
