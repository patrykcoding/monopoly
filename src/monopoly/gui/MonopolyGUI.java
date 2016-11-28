package monopoly.gui;

import monopoly.Player;
import monopoly.RespondDialog;
import monopoly.TradeDeal;
import monopoly.TradeDialog;

public interface MonopolyGUI {
    public void enableEndTurnButton(int playerIndex);
    public void enablePlayerTurn(int playerIndex);
    public void enablePurchaseButton(int playerIndex);
    public boolean isDrawCardButtonEnabled();
    public boolean isEndTurnButtonEnabled();
    public boolean isGetOutOfJailButtonEnabled();
    public boolean isTradeButtonEnabled(int i);
    public void movePlayer(int index, int from, int to);
    public RespondDialog openRespondDialog(TradeDeal deal);
    public TradeDialog openTradeDialog();
    public void removePlayer(int index, int from);
    public void setBuyHouseEnabled(boolean enabled);
    public void setDrawCardEnabled(boolean enabled);
    public void setEndTurnEnabled(boolean enabled);
    public void setGetOutOfJailEnabled(boolean enabled);
    public void setPurchasePropertyEnabled(boolean enabled);
    public void setRollDiceEnabled(boolean enabled);
    public void setTradeEnabled(int index, boolean enabled);
    public void showBuyHouseDialog(Player currentPlayer);
    public void showMessage(String string, PlayerPanel panel);
    public int showUtilityDiceRoll();
    public void startGame();
    public void update();
}
