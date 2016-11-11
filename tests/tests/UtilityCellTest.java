package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import tests.gameboards.GameBoardUtility;
import monopoly.MainController;
import monopoly.cells.UtilityCell;

public class UtilityCellTest extends TestCase {
    private MainController mainCtl;

    @Override
    protected void setUp() {
        mainCtl = new MainController();
        mainCtl.setGameBoard(new GameBoardUtility());
        mainCtl.setNumberOfPlayers(2);
        mainCtl.reset();
        mainCtl.setGUI(new MockGUI());
    }
    
    public void testMonopoly() {
        int u1CellIndex = mainCtl.getGameBoard().queryCellIndex("Utility 1");
        mainCtl.movePlayer(0, u1CellIndex);
        mainCtl.getPlayer(0).purchase();
        int u2CellIndex = mainCtl.getGameBoard().queryCellIndex("Utility 2");
        mainCtl.movePlayer(0, u2CellIndex - u1CellIndex);
        mainCtl.getPlayer(0).purchase();
        assertFalse(mainCtl.getPlayer(0).canBuyHouse(mainCtl.getGameBoard()));
    }
	
    public void testPlayerAction() {
            UtilityCell cell =
                    (UtilityCell) mainCtl.getGameBoard().queryCell("Utility 1");
            int cellIndex = mainCtl.getGameBoard().queryCellIndex("Utility 1");
            mainCtl.movePlayer(0, cellIndex);
            mainCtl.getPlayer(0).purchase();
            mainCtl.switchTurn();
            mainCtl.movePlayer(1, cellIndex);
            cell.playAction(mainCtl);
            int diceRoll = mainCtl.getUtilDiceRoll();
            assertEquals(1500 - cell.getRent(diceRoll), 
                    mainCtl.getPlayer(1).getMoney()
            );
            assertEquals(1350 + cell.getRent(diceRoll), 
                    mainCtl.getPlayer(0).getMoney()
            );
    }

    public void testPurchaseUtility() {
        assertEquals(0, mainCtl.getPlayer(0).numberOfUtil());
        int cellIndex = mainCtl.getGameBoard().queryCellIndex("Utility 1");
        mainCtl.movePlayer(0, cellIndex);
        mainCtl.getPlayer(0).purchase();
        assertEquals(1350, mainCtl.getPlayer(0).getMoney());
        assertEquals(1, mainCtl.getPlayer(0).numberOfUtil());
    }

    public void testRent() {
        UtilityCell u1 =
                (UtilityCell) mainCtl.getGameBoard().queryCell("Utility 1");
        int cellIndex1 = mainCtl.getGameBoard().queryCellIndex("Utility 1");
        mainCtl.movePlayer(0, cellIndex1);
        mainCtl.getPlayer(0).purchase();
        assertEquals(40, u1.getRent(10));

        UtilityCell u2 =
                (UtilityCell) mainCtl.getGameBoard().queryCell("Utility 2");
        int cellIndex2 = mainCtl.getGameBoard().queryCellIndex("Utility 2");
        mainCtl.movePlayer(0, cellIndex2 - cellIndex1);
        mainCtl.getPlayer(0).purchase();
        assertEquals(100, u1.getRent(10));
        assertEquals(100, u2.getRent(10));
    }
}
