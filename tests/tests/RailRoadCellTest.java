package tests;

import junit.framework.TestCase;
import monopoly.MainController;
import monopoly.cells.RailRoadCell;
import tests.gameboards.GameBoardRailRoad;
import tests.mocks.MockGUI;

public class RailRoadCellTest extends TestCase {
    private MainController mainController;

    @Override
    protected void setUp() {
        mainController = new MainController();
        mainController.setGameBoard(new GameBoardRailRoad());
        mainController.setNumberOfPlayers(2);
        mainController.reset();
        mainController.setGUI(new MockGUI());
    }

    public void testPlayerAction() {
        RailRoadCell cell =
                (RailRoadCell) mainController.getGameBoard().queryCell("Railroad A");
        int cellIndex = mainController.getGameBoard().queryCellIndex("Railroad A");
        mainController.movePlayer(mainController.getPlayer(0), cellIndex);
        mainController.purchase();
        mainController.switchTurn();
        mainController.movePlayer(mainController.getPlayer(1), cellIndex);
        cell.playAction(mainController);
        assertEquals(1500 - cell.getRent(), mainController.getPlayer(1).getMoney());
        assertEquals(1300 + cell.getRent(), mainController.getPlayer(0).getMoney());
    }

    public void testPurchaseRailroad() {
        assertEquals(0, mainController.getPlayer(0).numberOfRailroads());
        int cellIndex = mainController.getGameBoard().queryCellIndex("Railroad A");
        mainController.movePlayer(mainController.getPlayer(0), cellIndex);
        mainController.purchase();
        assertEquals(1300, mainController.getPlayer(0).getMoney());
        assertEquals(1, mainController.getPlayer(0).numberOfRailroads());
    }

    public void testRent() {
        RailRoadCell railroad1 =
                (RailRoadCell) mainController.getGameBoard().queryCell("Railroad A");
        int cellIndex1 = mainController.getGameBoard().queryCellIndex("Railroad A");
        mainController.movePlayer(mainController.getPlayer(0), cellIndex1);
        mainController.purchase();
        assertEquals(25, railroad1.getRent());

        RailRoadCell railroad2 =
                (RailRoadCell) mainController.getGameBoard().queryCell("Railroad B");
        int cellIndex2 = mainController.getGameBoard().queryCellIndex("Railroad B");
        mainController.movePlayer(mainController.getPlayer(0), cellIndex2 - cellIndex1);
        mainController.purchase();
        assertEquals(50, railroad1.getRent());
        assertEquals(50, railroad2.getRent());
    }
}
