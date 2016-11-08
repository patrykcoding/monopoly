package monopoly.cards;

import monopoly.Card;
import monopoly.GameMaster;
import monopoly.Player;

public class MoneyCard extends Card {
    private final int amount;
    private final int cardType;
    
    private final String label;
    
    public MoneyCard(String label, int amount, int cardType){
        this.label = label;
        this.amount = amount;
        this.cardType = cardType;
    }

    @Override
    public void applyAction(GameMaster master) {
        Player currentPlayer = master.getCurrentPlayer();
        currentPlayer.setMoney(currentPlayer.getMoney() + amount);
    }

    @Override
    public int getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return label;
    }
}
