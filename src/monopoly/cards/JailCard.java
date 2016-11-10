package monopoly.cards;

import monopoly.Card;
import monopoly.MainController;
import monopoly.Player;

public class JailCard extends Card {
    int type;
    
    public JailCard(int cardType) {
        type = cardType;
    }

    @Override
    public void applyAction(MainController mainCtl) {
        Player currentPlayer = mainCtl.getCurrentPlayer();
        mainCtl.sendToJail(currentPlayer);
    }

    @Override
    public int getCardType() {
        return type;
    }

    @Override
    public String toString() {
        return "Go directly to Jail without collecting $200 when passing GO";
    }
}
