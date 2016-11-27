package monopoly;

import monopoly.enums.CardType;

public abstract class Card {

    public abstract void applyAction(MainController mainController);
    public abstract CardType getCardType();
}
