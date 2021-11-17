package monopoly.cells;

import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;

/**
 *GoToJailCell class (subclass) inherits the attributes and methods from the Cell class (superclass)
 * @author owner
 */
public class GoToJailCell extends Cell {
	
    public GoToJailCell() {
        super.setName("Go to Jail");
    }

    /**
     *Send the current player to jail.
     * @param mainController
     */
    @Override
    public void playAction(MainController mainController) {
        Player currentPlayer = mainController.getCurrentPlayer();
        mainController.sendToJail(currentPlayer);
    }
}
