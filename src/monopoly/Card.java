package monopoly;

import monopoly.enums.CardType;

public abstract class Card {

    /**
     * 
     * @param mainCtl 
     */
    public abstract void applyAction(MainController mainCtl);
    
    /**
     * 
     * @return 
     */
    public abstract CardType getCardType();
}
