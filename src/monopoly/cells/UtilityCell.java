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
        if (player.numberOfUtil() == 1)
                return diceRoll * 4;
        else if (player.numberOfUtil() >= 2)
                return diceRoll * 10;
        return 0;
    }

    @Override
    public int getPrice() {
        return UtilityCell.PRICE;
    }
    
    @Override
    public void playAction(MainController mainCtl) {
        Player currentPlayer;
        if (isAvailable())
            return;
        currentPlayer = mainCtl.getCurrentPlayer();
        if (player != currentPlayer) {
            mainCtl.utilRollDice();
            int diceRoll = mainCtl.getUtilDiceRoll();
            mainCtl.payRentTo(player, getRent(diceRoll));
        }
    }
}
