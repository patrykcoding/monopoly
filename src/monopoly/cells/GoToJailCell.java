package monopoly.cells;

import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;

public class GoToJailCell extends Cell {
	
    public GoToJailCell() {
        super.setName("Go to Jail");
    }

    @Override
    public void playAction(MainController mainCtl) {
        Player currentPlayer = mainCtl.getCurrentPlayer();
        mainCtl.sendToJail(currentPlayer);
    }
}
