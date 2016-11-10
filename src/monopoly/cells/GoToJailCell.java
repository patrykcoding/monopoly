package monopoly.cells;

import monopoly.Cell;
import monopoly.GameMaster;
import monopoly.Player;

public class GoToJailCell extends Cell {
	
    public GoToJailCell() {
        super.setName("Go to Jail");
    }

    @Override
    public void playAction(GameMaster master) {
        Player currentPlayer = master.getCurrentPlayer();
        master.sendToJail(currentPlayer);
    }
}