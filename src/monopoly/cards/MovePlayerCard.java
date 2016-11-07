package monopoly.cards;

import monopoly.Card;
import monopoly.Cell;
import monopoly.GameMaster;
import monopoly.Player;


public class MovePlayerCard extends Card {
    
    private final String destination;
    private final int type;

    public MovePlayerCard(String destination, int cardType) {
        this.destination = destination;
        this.type = cardType;
    }

    @Override
    public void applyAction(GameMaster master) {
        Player currentPlayer = master.getCurrentPlayer();
        Cell currentPosition = currentPlayer.getPosition();
        int newCell = master.getGameBoard().queryCellIndex(destination);
        int currentCell = master.getGameBoard().queryCellIndex(currentPosition.getName());
        int diceValue = 0;
        if (currentCell > newCell) {
            diceValue = (master.getGameBoard().getCellNumber() + 
                         (newCell - currentCell));
        } else if (currentCell <= newCell) {
            diceValue = newCell - currentCell;
        }
        master.movePlayer(currentPlayer, diceValue);
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
