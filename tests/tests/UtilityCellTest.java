package tests;

import junit.framework.TestCase;
import monopoly.MainController;
import monopoly.cells.UtilityCell;
import tests.gameboards.GameBoardUtility;
import tests.mocks.MockGUI;

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
        mainCtl.movePlayer(mainCtl.getPlayer(0), u1CellIndex);
        mainCtl.purchase();
        int u2CellIndex = mainCtl.getGameBoard().queryCellIndex("Utility 2");
        mainCtl.movePlayer(mainCtl.getPlayer(0), u2CellIndex - u1CellIndex);
        mainCtl.purchase();
        assertFalse(mainCtl.canBuyHouse());
    }
	
    public void testPlayerAction() {
            UtilityCell cell =
                    (UtilityCell) mainCtl.getGameBoard().queryCell("Utility 1");
            int cellIndex = mainCtl.getGameBoard().queryCellIndex("Utility 1");
            mainCtl.movePlayer(mainCtl.getPlayer(0), cellIndex);
            mainCtl.purchase();
            mainCtl.switchTurn();
            mainCtl.movePlayer(mainCtl.getPlayer(1), cellIndex);
            cell.playAction(mainCtl);
            int diceRoll = mainCtl.getUtilityDiceRoll();
            assertEquals(1500 - cell.getRent(diceRoll), 
                    mainCtl.getPlayer(1).getMoney()
            );
            assertEquals(1350 + cell.getRent(diceRoll), 
                    mainCtl.getPlayer(0).getMoney()
            );
    }

    public void testPurchaseUtility() {
        assertEquals(0, mainCtl.getPlayer(0).numberOfUtilities());
        int cellIndex = mainCtl.getGameBoard().queryCellIndex("Utility 1");
        mainCtl.movePlayer(mainCtl.getPlayer(0), cellIndex);
        mainCtl.purchase();
        assertEquals(1350, mainCtl.getPlayer(0).getMoney());
        assertEquals(1, mainCtl.getPlayer(0).numberOfUtilities());
    }

    public void testRent() {
        UtilityCell u1 =
                (UtilityCell) mainCtl.getGameBoard().queryCell("Utility 1");
        int cellIndex1 = mainCtl.getGameBoard().queryCellIndex("Utility 1");
        mainCtl.movePlayer(mainCtl.getPlayer(0), cellIndex1);
        mainCtl.purchase();
        assertEquals(40, u1.getRent(10));

        UtilityCell u2 =
                (UtilityCell) mainCtl.getGameBoard().queryCell("Utility 2");
        int cellIndex2 = mainCtl.getGameBoard().queryCellIndex("Utility 2");
        mainCtl.movePlayer(mainCtl.getPlayer(0), cellIndex2 - cellIndex1);
        mainCtl.purchase();
        assertEquals(100, u1.getRent(10));
        assertEquals(100, u2.getRent(10));
    }
}
