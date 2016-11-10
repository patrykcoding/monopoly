package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import monopoly.Cell;
import monopoly.GameBoard;
import monopoly.MainController;
import monopoly.Player;
import monopoly.cells.PropertyCell;
import tests.gameboards.SimpleGameBoard;

public class PlayerTest extends TestCase {

    private MainController mainCtl;
	
    @Override
    protected void setUp() throws Exception {
        mainCtl = new MainController();
        mainCtl.setGameBoard(new SimpleGameBoard());
        mainCtl.setGUI(new MockGUI());
        mainCtl.setTestMode(true);
        mainCtl.reset();
    }
    
    public void testPurchaseProperty() {
        mainCtl.setNumberOfPlayers(1);
        mainCtl.movePlayer(0, 3);
        Player player = mainCtl.getPlayer(0);
        player.purchase();
        assertEquals(1380, player.getMoney());
        assertEquals("Blue 3", player.getProperty(0).getName());
        PropertyCell cell =
        (PropertyCell) mainCtl.getGameBoard().queryCell("Blue 3");
        assertSame(player, cell.getPlayer());
    }

    public void testSameGoCell() {
        GameBoard gameboard = mainCtl.getGameBoard();
        mainCtl.setNumberOfPlayers(2);
        
        Cell go = gameboard.queryCell("Go");
        assertSame(go, mainCtl.getPlayer(0).getPosition());
        assertSame(go, mainCtl.getPlayer(1).getPosition());
    }
	
    public void testPayRentTo() {
        mainCtl.setNumberOfPlayers(2);
        mainCtl.movePlayer(0,4);
        mainCtl.getCurrentPlayer().purchase();
        mainCtl.btnEndTurnClicked();
        mainCtl.movePlayer(1,4);
        mainCtl.btnEndTurnClicked();
        assertTrue(mainCtl.getPlayer(1).isBankrupt());
        assertEquals(2800, mainCtl.getPlayer(0).getMoney());
    }
	
    public void testExchangeProperty() {
        mainCtl.setNumberOfPlayers(2);
        mainCtl.movePlayer(0,3);
        mainCtl.getCurrentPlayer().purchase();
        mainCtl.btnEndTurnClicked();
        mainCtl.getPlayer(0).exchangeProperty(mainCtl.getPlayer(1));
        assertEquals(1,mainCtl.getCurrentPlayer().getPropertyCount());
    }

    public void testResetProperty() {
        mainCtl.setNumberOfPlayers(1);
        mainCtl.movePlayer(0,1);
        mainCtl.getCurrentPlayer().purchase();
        assertEquals(
            mainCtl.getGameBoard().getCell(1), 
            mainCtl.getCurrentPlayer().getAllProperties()[0]
        );
        mainCtl.getCurrentPlayer().resetProperties();
        assertEquals(0,mainCtl.getCurrentPlayer().getAllProperties().length);
    }
}
