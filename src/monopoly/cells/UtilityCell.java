package monopoly.cells;

import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;

/**
 * UtilityCell class (subclass) inherits the attributes and methods from the Cell class (superclass)
 * @author owner
 */
public class UtilityCell extends Cell {

    private static int PRICE;

    /**
     *
     * @param price
     */
    public static void setPrice(int price) {
        UtilityCell.PRICE = price;
    }

    /**
     *Based on number of utilities ,the amount of rent will be calculated by dice roll equation.
     * @param diceRoll
     * @return Rent depended on dice roll equation.
     */
    public int getRent(int diceRoll) {
        if (player.numberOfUtilities() == 1)
                return diceRoll * 4;
        else if (player.numberOfUtilities() >= 2)
                return diceRoll * 10;
        return 0;
    }

    /**
     *
     * @return price
     */
    @Override
    public int getPrice() {
        return UtilityCell.PRICE;
    }
    
    /**
     * If the cell is not available and the player is not a current player , The player will pay Rent.
     * @param mainController
     */
    @Override
    public void playAction(MainController mainController) {
        Player currentPlayer;
        if (isAvailable())
            return;
        currentPlayer = mainController.getCurrentPlayer();
        if (player != currentPlayer) {
            mainController.utilityRollDice();
            int diceRoll = mainController.getUtilityDiceRoll();
            mainController.payRentTo(player, getRent(diceRoll));
        }
    }
}
