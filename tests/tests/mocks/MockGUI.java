package tests.mocks;

import monopoly.Player;
import monopoly.RespondDialog;
import monopoly.TradeDeal;
import monopoly.TradeDialog;
import monopoly.gui.MonopolyGUI;
import monopoly.gui.PlayerPanel;

public class MockGUI implements MonopolyGUI {
    private boolean buttonDrawCardState;
    private boolean buttonEndTurnState;
    private boolean buttonGetOutOfJailState;
    private final boolean[] buttonTradeState = new boolean[2];

    @Override
    public void enableEndTurnButton(int playerIndex) {}

    @Override
    public void enablePlayerTurn(int playerIndex) {}

    @Override
    public void enablePurchaseButton(int playerIndex) {}
    
    @Override
    public boolean isDrawCardButtonEnabled() {
        return buttonDrawCardState;
    }

    @Override
    public boolean isEndTurnButtonEnabled() {
        return buttonEndTurnState;
    }
	
    @Override
    public boolean isGetOutOfJailButtonEnabled() {
        return buttonGetOutOfJailState;
    }

    @Override
    public boolean isTradeButtonEnabled(int i) {
        return buttonTradeState[i];
    }

    @Override
    public void movePlayer(int index, int from, int to) {}

    @Override
    public RespondDialog openRespondDialog(TradeDeal deal) {
        return new MockRespondDialog(deal);
    }

    @Override
    public TradeDialog openTradeDialog() {
        return new MockTradeDialog();
    }

    @Override
    public void removePlayer(int index, int from) {}

    @Override
    public void setBuyHouseEnabled(boolean enabled) {}

    @Override
    public void setDrawCardEnabled(boolean enabled) {
        buttonDrawCardState = enabled;
    }

    @Override
    public void setEndTurnEnabled(boolean enabled) {
        buttonEndTurnState = enabled;
    }

    @Override
    public void setGetOutOfJailEnabled(boolean enabled) {
    	this.buttonGetOutOfJailState = enabled;
    }

    @Override
    public void setPurchasePropertyEnabled(boolean enabled) {}

    @Override
    public void setRollDiceEnabled(boolean enabled) {}

    @Override
    public void setTradeEnabled(int index, boolean enabled) {
        this.buttonTradeState[index] = enabled;
    }

    @Override
    public void showBuyHouseDialog(Player currentPlayer) {}

    @Override
    public void showMessage(String string, PlayerPanel panel) {}

    @Override
    public int showUtilityDiceRoll() {
        return 10;
    }

    @Override
    public void startGame() {}

    @Override
    public void update() {}
}
