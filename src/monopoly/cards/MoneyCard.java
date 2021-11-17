package monopoly.cards;

import monopoly.Card;
import monopoly.MainController;
import monopoly.Player;
import monopoly.enums.CardType;

/**
 *MoneyCard class (subclass) inherits the attributes and methods from the Card class (superclass)
 * @author owner
 */
public class MoneyCard extends Card {

    
    private final int amount;

    private final CardType cardType;
    
    private final String label;
    
    /**
     *
     * @param label
     * @param amount
     * @param cardType
     */
    public MoneyCard(String label, int amount, CardType cardType){
        this.label = label;
        this.amount = amount;
        this.cardType = cardType;
    }

    /**
     *increment money of current player with the money card amount.
     * @param mainController
     */
    @Override
    public void applyAction(MainController mainController) {
        Player currentPlayer = mainController.getCurrentPlayer();
        currentPlayer.setMoney(currentPlayer.getMoney() + amount);
    }

    /**
     *
     * @return Type of the card.
     */
    @Override
    public CardType getCardType() {
        return cardType;
    }

    /**
     *
     * @return label message
     */
    @Override
    public String toString() {
        return label;
    }
}
