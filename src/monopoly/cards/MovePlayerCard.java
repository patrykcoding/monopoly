package monopoly.cards;

import monopoly.Card;
import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;
import monopoly.enums.CardType;

/**
 *MovePlayerCard (subclass) inherits the attributes and methods from the Card class (superclass)
 * @author owner
 */
public class MovePlayerCard extends Card {
    
    
    private final String destination;

    private final CardType type;

    /**
     *
     * @param destination
     * @param cardType
     */
    public MovePlayerCard(String destination, CardType cardType) {
        this.destination = destination;
        this.type = cardType;
    }

    /**
     *The action takes to should player move
     * @param mainController
     */
    @Override
    public void applyAction(MainController mainController) {
        Player currentPlayer = mainController.getCurrentPlayer();
        Cell currentPosition = currentPlayer.getPosition();
        int newCell = mainController.getGameBoard().queryCellIndex(destination);
        int currentCell = mainController.getGameBoard().queryCellIndex(currentPosition.getName());
        int diceValue = 0;
        if (currentCell > newCell) {
            diceValue = (mainController.getGameBoard().getCellSize() + 
                         (newCell - currentCell));
        } else if (currentCell <= newCell) {
            diceValue = newCell - currentCell;
        }
        mainController.movePlayer(currentPlayer, diceValue);
    }

    /**
     *
     * @return type of card
     */
    @Override
    public CardType getCardType() {
        return type;
    }

    /**
     *
     * @return Message for destination to player 
     */
    @Override
    public String toString() {
        return "Go to " + destination;
    }
}
