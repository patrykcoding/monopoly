package monopoly.cards;

import monopoly.Card;
import monopoly.enums.CardType;
import monopoly.MainController;
import monopoly.Player;

public class JailCard extends Card {
    private final CardType type;
    
    public JailCard(CardType cardType) {
        type = cardType;
    }

    @Override
    public void applyAction(MainController mainController) {
        Player currentPlayer = mainController.getCurrentPlayer();
        mainController.sendToJail(currentPlayer);
    }

    @Override
    public CardType getCardType() {
        return type;
    }

    @Override
    public String toString() {
        return "Go directly to Jail without collecting $200 when passing GO";
    }
}
