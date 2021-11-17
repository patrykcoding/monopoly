package monopoly;

/**
 *
 * @author owner
 */
public interface TradeDialog {

    /**
     *Get (cards) in an orderly rotation to players for a game.
     * @param mainController
     * @return
     */
    TradeDeal getTradeDeal(MainController mainController);
}
