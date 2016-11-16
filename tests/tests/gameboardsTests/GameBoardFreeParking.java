
package tests.gameboardsTests;

import monopoly.cells.FreeParkingCell;
import monopoly.GameBoard;
import monopoly.cells.GoToJailCell;
import monopoly.cells.JailCell;

public class GameBoardFreeParking extends GameBoard {
    public GameBoardFreeParking() {
        super();
	
        JailCell jail = new JailCell();
        FreeParkingCell freeParking = new FreeParkingCell();
        GoToJailCell goToJail = new GoToJailCell();
        addCell(jail);
        addCell(freeParking);
        addCell(goToJail);
    }
}
