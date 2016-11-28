package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import tests.gameboards.GameBoardRailRoad;
import monopoly.MainController;
import monopoly.cells.RailRoadCell;

public class RailRoadCellTest extends TestCase {
    private MainController mainCtl;

    @Override
    protected void setUp() {
        mainCtl = new MainController();
        mainCtl.setGameBoard(new GameBoardRailRoad());
        mainCtl.setNumberOfPlayers(2);
        mainCtl.reset();
        mainCtl.setGUI(new MockGUI());
    }

    public void testPlayerAction() {
        RailRoadCell cell =
                (RailRoadCell) mainCtl.getGameBoard().queryCell("Railroad A");
        int cellIndex = mainCtl.getGameBoard().queryCellIndex("Railroad A");
        mainCtl.movePlayer(mainCtl.getPlayer(0), cellIndex);
        mainCtl.purchase();
        mainCtl.switchTurn();
        mainCtl.movePlayer(mainCtl.getPlayer(1), cellIndex);
        cell.playAction(mainCtl);
        assertEquals(1500 - cell.getRent(), mainCtl.getPlayer(1).getMoney());
        assertEquals(1300 + cell.getRent(), mainCtl.getPlayer(0).getMoney());
    }

    public void testPurchaseRailroad() {
        assertEquals(0, mainCtl.getPlayer(0).numberOfRailroads());
        int cellIndex = mainCtl.getGameBoard().queryCellIndex("Railroad A");
        mainCtl.movePlayer(mainCtl.getPlayer(0), cellIndex);
        mainCtl.purchase();
        assertEquals(1300, mainCtl.getPlayer(0).getMoney());
        assertEquals(1, mainCtl.getPlayer(0).numberOfRailroads());
    }

    public void testRent() {
        RailRoadCell rr1 =
                (RailRoadCell) mainCtl.getGameBoard().queryCell("Railroad A");
        int cellIndex1 = mainCtl.getGameBoard().queryCellIndex("Railroad A");
        mainCtl.movePlayer(mainCtl.getPlayer(0), cellIndex1);
        mainCtl.purchase();
        assertEquals(25, rr1.getRent());

        RailRoadCell rr2 =
                (RailRoadCell) mainCtl.getGameBoard().queryCell("Railroad B");
        int cellIndex2 = mainCtl.getGameBoard().queryCellIndex("Railroad B");
        mainCtl.movePlayer(mainCtl.getPlayer(0), cellIndex2 - cellIndex1);
        mainCtl.purchase();
        assertEquals(50, rr1.getRent());
        assertEquals(50, rr2.getRent());
    }
}
