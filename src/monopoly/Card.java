package monopoly;

import monopoly.enums.CardType;

/**
 *
 * @author owner
 */
public abstract class Card {

    /**
     *Abstract function will be implemented by the child classes.
     * @param mainController
     */
    public abstract void applyAction(MainController mainController);

    /**
     *
     * @return Getting type of card that player played.
     */
    public abstract CardType getCardType();
}
