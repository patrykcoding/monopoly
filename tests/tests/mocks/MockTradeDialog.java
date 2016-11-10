package tests.mocks;

import monopoly.MainController;
import monopoly.TradeDeal;
import monopoly.TradeDialog;

public class MockTradeDialog implements TradeDialog {

    @Override
    public TradeDeal getTradeDeal(MainController mainCtl) {
        TradeDeal deal = new TradeDeal();
        deal.setAmount(200);
        deal.setSeller(mainCtl.getPlayer(0));
        deal.setPropertyName(mainCtl.getGameBoard().getCell(1).toString());
        return deal;
    }
}
