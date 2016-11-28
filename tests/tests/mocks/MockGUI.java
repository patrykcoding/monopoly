package tests.mocks;

import monopoly.gui.MonopolyGUI;
import monopoly.Player;
import monopoly.RespondDialog;
import monopoly.TradeDeal;
import monopoly.TradeDialog;
import monopoly.gui.PlayerPanel;

public class MockGUI implements MonopolyGUI {
    private boolean btnDrawCardState, btnEndTurnState, btnGetOutOfJailState;
    private final boolean[] btnTradeState = new boolean[2];

    @Override
    public void enableEndTurnButton(int playerIndex) {}

    @Override
    public void enablePlayerTurn(int playerIndex) {}

    @Override
    public void enablePurchaseButton(int playerIndex) {}
    
    @Override
    public boolean isDrawCardButtonEnabled() {
        return btnDrawCardState;
    }

    @Override
    public boolean isEndTurnButtonEnabled() {
        return btnEndTurnState;
    }
	
    @Override
    public boolean isGetOutOfJailButtonEnabled() {
        return btnGetOutOfJailState;
    }

    @Override
    public boolean isTradeButtonEnabled(int i) {
        return btnTradeState[i];
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
    public void setBuyHouseEnabled(boolean b) {}

    @Override
    public void setDrawCardEnabled(boolean b) {
        btnDrawCardState = b;
    }

    @Override
    public void setEndTurnEnabled(boolean enabled) {
        btnEndTurnState = enabled;
    }

    @Override
    public void setGetOutOfJailEnabled(boolean b) {
    	this.btnGetOutOfJailState = b;
    }

    @Override
    public void setPurchasePropertyEnabled(boolean enabled) {}

    @Override
    public void setRollDiceEnabled(boolean b) {}

    @Override
    public void setTradeEnabled(int index, boolean b) {
        this.btnTradeState[index] = b;
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
