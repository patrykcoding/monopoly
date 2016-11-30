
package tests;

import junit.framework.TestCase;
import monopoly.MainController;
import monopoly.cells.PropertyCell;
import tests.gameboards.SimpleGameBoard;
import tests.mocks.MockGUI;

public class PropertyCellTest extends TestCase {

    private MainController mainController;
	
    @Override
    protected void setUp() {
        mainController = new MainController();
        mainController.setGameBoard(new SimpleGameBoard());
        mainController.setNumberOfPlayers(2);
        mainController.reset();
        mainController.setGUI(new MockGUI());
    }

    public void testPlayerAction() {
        PropertyCell cell = (PropertyCell) mainController.getGameBoard().queryCell("Blue 3");
        int cellIndex = mainController.getGameBoard().queryCellIndex("Blue 3");
        mainController.movePlayer(mainController.getPlayer(0), cellIndex);
        mainController.purchase();
        mainController.switchTurn();
        mainController.movePlayer(mainController.getPlayer(1), cellIndex);
        cell.playAction(mainController);
        assertEquals(1500 - cell.getRent(), mainController.getPlayer(1).getMoney());
        assertEquals(1380 + cell.getRent(), mainController.getPlayer(0).getMoney());
    }
}
