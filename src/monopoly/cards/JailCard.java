package monopoly.cards;

import monopoly.Card;
import monopoly.MainController;
import monopoly.Player;
import monopoly.enums.CardType;

/**
 *JailCard class (subclass) inherits the attributes and methods from the Card class (superclass)
 * @author owner
 */
public class JailCard extends Card {

    private final CardType type;
    
    /**
     *
     * @param cardType
     */
    public JailCard(CardType cardType) {
        type = cardType;
    }

    /**
     *Send the current player to jail.
     * @param mainController
     */
    @Override
    public void applyAction(MainController mainController) {
        Player currentPlayer = mainController.getCurrentPlayer();
        mainController.sendToJail(currentPlayer);
    }

    /**
     *
     * @return Type of card.
     */
    @Override
    public CardType getCardType() {
        return type;
    }

    /**
     *
     * @return Message to player
     */
    @Override
    public String toString() {
        return "Go directly to Jail without collecting $200 when passing GO";
    }
}
