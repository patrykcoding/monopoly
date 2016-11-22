package monopoly.cards;

import monopoly.Card;
import monopoly.enums.CardType;
import monopoly.MainController;
import monopoly.Player;

public class MoneyCard extends Card {
    private final int amount;
    private final CardType cardType;
    
    private final String label;
    
    public MoneyCard(String label, int amount, CardType cardType){
        this.label = label;
        this.amount = amount;
        this.cardType = cardType;
    }

    @Override
    public void applyAction(MainController mainCtl) {
        Player currentPlayer = mainCtl.getCurrentPlayer();
        currentPlayer.setMoney(currentPlayer.getMoney() + amount);
    }

    @Override
    public CardType getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return label;
    }
}
