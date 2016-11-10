package monopoly.cards;

import monopoly.Card;
import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;


public class MovePlayerCard extends Card {
    
    private final String destination;
    private final int type;

    public MovePlayerCard(String destination, int cardType) {
        this.destination = destination;
        this.type = cardType;
    }

    @Override
    public void applyAction(MainController mainCtl) {
        Player currentPlayer = mainCtl.getCurrentPlayer();
        Cell currentPosition = currentPlayer.getPosition();
        int newCell = mainCtl.getGameBoard().queryCellIndex(destination);
        int currentCell = mainCtl.getGameBoard().queryCellIndex(currentPosition.getName());
        int diceValue = 0;
        if (currentCell > newCell) {
            diceValue = (mainCtl.getGameBoard().getCellSize() + 
                         (newCell - currentCell));
        } else if (currentCell <= newCell) {
            diceValue = newCell - currentCell;
        }
        mainCtl.movePlayer(currentPlayer, diceValue);
    }

    @Override
    public int getCardType() {
        return type;
    }

    @Override
    public String toString() {
        return "Go to " + destination;
    }
}
