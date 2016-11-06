package mocks;

import monopoly.GameMaster;
import monopoly.TradeDeal;
import monopoly.TradeDialog;

public class MockTradeDialog implements TradeDialog {

    @Override
    public TradeDeal getTradeDeal(GameMaster master) {
        TradeDeal deal = new TradeDeal();
        deal.setAmount(200);
        deal.setSeller(master.getPlayer(0));
        deal.setPropertyName(master.getGameBoard().getCell(1).toString());
        return deal;
    }
}
