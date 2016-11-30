package tests;

import junit.framework.TestCase;
import monopoly.MainController;
import monopoly.cells.UtilityCell;
import tests.gameboards.GameBoardUtility;
import tests.mocks.MockGUI;

public class UtilityCellTest extends TestCase {
    private MainController mainController;

    @Override
    protected void setUp() {
        mainController = new MainController();
        mainController.setGameBoard(new GameBoardUtility());
        mainController.setNumberOfPlayers(2);
        mainController.reset();
        mainController.setGUI(new MockGUI());
    }
    
    public void testMonopoly() {
        int utility1CellIndex = mainController.getGameBoard().queryCellIndex("Utility 1");
        mainController.movePlayer(mainController.getPlayer(0), utility1CellIndex);
        mainController.purchase();
        int utility2CellIndex = mainController.getGameBoard().queryCellIndex("Utility 2");
        mainController.movePlayer(mainController.getPlayer(0), utility2CellIndex - utility1CellIndex);
        mainController.purchase();
        assertFalse(mainController.canBuyHouse());
    }
	
    public void testPlayerAction() {
            UtilityCell cell =
                    (UtilityCell) mainController.getGameBoard().queryCell("Utility 1");
            int cellIndex = mainController.getGameBoard().queryCellIndex("Utility 1");
            mainController.movePlayer(mainController.getPlayer(0), cellIndex);
            mainController.purchase();
            mainController.switchTurn();
            mainController.movePlayer(mainController.getPlayer(1), cellIndex);
            cell.playAction(mainController);
            int diceRoll = mainController.getUtilityDiceRoll();
            assertEquals(1500 - cell.getRent(diceRoll), 
                    mainController.getPlayer(1).getMoney()
            );
            assertEquals(1350 + cell.getRent(diceRoll), 
                    mainController.getPlayer(0).getMoney()
            );
    }

    public void testPurchaseUtility() {
        assertEquals(0, mainController.getPlayer(0).numberOfUtilities());
        int cellIndex = mainController.getGameBoard().queryCellIndex("Utility 1");
        mainController.movePlayer(mainController.getPlayer(0), cellIndex);
        mainController.purchase();
        assertEquals(1350, mainController.getPlayer(0).getMoney());
        assertEquals(1, mainController.getPlayer(0).numberOfUtilities());
    }

    public void testRent() {
        UtilityCell utility1 =
                (UtilityCell) mainController.getGameBoard().queryCell("Utility 1");
        int cellIndex1 = mainController.getGameBoard().queryCellIndex("Utility 1");
        mainController.movePlayer(mainController.getPlayer(0), cellIndex1);
        mainController.purchase();
        assertEquals(40, utility1.getRent(10));

        UtilityCell utility2 =
                (UtilityCell) mainController.getGameBoard().queryCell("Utility 2");
        int cellIndex2 = mainController.getGameBoard().queryCellIndex("Utility 2");
        mainController.movePlayer(mainController.getPlayer(0), cellIndex2 - cellIndex1);
        mainController.purchase();
        assertEquals(100, utility1.getRent(10));
        assertEquals(100, utility2.getRent(10));
    }
}
