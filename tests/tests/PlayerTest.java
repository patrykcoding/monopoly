package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import monopoly.Cell;
import monopoly.GameBoard;
import monopoly.MainController;
import tests.gameboardsTests.SimpleGameBoard;

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

    public void testInit() {
        mainCtl.setNumberOfPlayers(1);
        assertEquals(1500, mainCtl.getPlayer(0).getMoney());
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
        mainCtl.movePlayer(mainCtl.getPlayer(0), 4);
        mainCtl.purchase();
        mainCtl.btnEndTurnClicked();
        mainCtl.movePlayer(mainCtl.getPlayer(1), 4);
        mainCtl.btnEndTurnClicked();
        assertTrue(mainCtl.getPlayer(1).isBankrupt());
        assertEquals(2800, mainCtl.getPlayer(0).getMoney());
    }
	
    public void testGiveAllProperties() {
        mainCtl.setNumberOfPlayers(2);
        mainCtl.movePlayer(mainCtl.getPlayer(0), 3);
        mainCtl.purchase();
        mainCtl.btnEndTurnClicked();
        mainCtl.giveAllProperties(mainCtl.getPlayer(0), mainCtl.getPlayer(1));
        assertEquals(1, mainCtl.getPlayer(1).getPropertyCount());
    }

    public void testResetProperty() {
        mainCtl.setNumberOfPlayers(1);
        mainCtl.movePlayer(mainCtl.getPlayer(0), 1);
        mainCtl.purchase();
        assertEquals(
            mainCtl.getGameBoard().getCell(1), 
            mainCtl.getPlayer(0).getAllProperties().get(0)
        );
        mainCtl.getPlayer(0).resetProperties();
        assertEquals(0, mainCtl.getPlayer(0).getAllProperties().size());
    }
}
