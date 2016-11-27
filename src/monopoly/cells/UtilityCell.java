package monopoly.cells;

import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;

public class UtilityCell extends Cell {
    private static int PRICE;

    public static void setPrice(int price) {
        UtilityCell.PRICE = price;
    }

    public int getRent(int diceRoll) {
        if (player.numberOfUtilities() == 1)
                return diceRoll * 4;
        else if (player.numberOfUtilities() >= 2)
                return diceRoll * 10;
        return 0;
    }

    @Override
    public int getPrice() {
        return UtilityCell.PRICE;
    }
    
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
