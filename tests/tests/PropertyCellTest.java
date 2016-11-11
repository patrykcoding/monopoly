
package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import monopoly.MainController;
import monopoly.cells.PropertyCell;
import tests.gameboards.SimpleGameBoard;

public class PropertyCellTest extends TestCase {

    private MainController mainCtl;
	
    @Override
    protected void setUp() {
        mainCtl = new MainController();
        mainCtl.setGameBoard(new SimpleGameBoard());
        mainCtl.setNumberOfPlayers(2);
        mainCtl.reset();
        mainCtl.setGUI(new MockGUI());
    }

    public void testPlayerAction() {
        PropertyCell cell = (PropertyCell) mainCtl.getGameBoard().queryCell("Blue 3");
        int cellIndex = mainCtl.getGameBoard().queryCellIndex("Blue 3");
        mainCtl.movePlayer(0, cellIndex);
        mainCtl.getPlayer(0).purchase();
        mainCtl.switchTurn();
        mainCtl.movePlayer(1, cellIndex);
        cell.playAction(mainCtl);
        assertEquals(1500 - cell.getRent(mainCtl.getGameBoard()), mainCtl.getPlayer(1).getMoney());
        assertEquals(1380 + cell.getRent(mainCtl.getGameBoard()), mainCtl.getPlayer(0).getMoney());
    }
}
