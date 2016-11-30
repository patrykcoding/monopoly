package tests;

import junit.framework.TestCase;
import monopoly.Cell;
import monopoly.GameBoard;
import monopoly.MainController;
import tests.gameboards.SimpleGameBoard;
import tests.mocks.MockGUI;

public class PlayerTest extends TestCase {

    private MainController mainController;
	
    @Override
    protected void setUp() throws Exception {
        mainController = new MainController();
        mainController.setGameBoard(new SimpleGameBoard());
        mainController.setGUI(new MockGUI());
        mainController.reset();
    }

    public void testInit() {
        mainController.setNumberOfPlayers(1);
        assertEquals(1500, mainController.getPlayer(0).getMoney());
    }
    
    public void testSameGoCell() {
        GameBoard gameboard = mainController.getGameBoard();
        mainController.setNumberOfPlayers(2);
        
        Cell go = gameboard.queryCell("Go");
        assertSame(go, mainController.getPlayer(0).getPosition());
        assertSame(go, mainController.getPlayer(1).getPosition());
    }
	
    public void testPayRentTo() {
        mainController.setNumberOfPlayers(2);
        mainController.movePlayer(mainController.getPlayer(0), 4);
        mainController.purchase();
        mainController.buttonEndTurnClicked();
        mainController.movePlayer(mainController.getPlayer(1), 4);
        mainController.buttonEndTurnClicked();
        assertTrue(mainController.getPlayer(1).isBankrupt());
        assertEquals(2800, mainController.getPlayer(0).getMoney());
    }
	
    public void testGiveAllProperties() {
        mainController.setNumberOfPlayers(2);
        mainController.movePlayer(mainController.getPlayer(0), 3);
        mainController.purchase();
        mainController.buttonEndTurnClicked();
        mainController.giveAllProperties(mainController.getPlayer(0), mainController.getPlayer(1));
        assertEquals(1, mainController.getPlayer(1).getPropertyCount());
    }

    public void testResetProperty() {
        mainController.setNumberOfPlayers(1);
        mainController.movePlayer(mainController.getPlayer(0), 1);
        mainController.purchase();
        assertEquals(mainController.getGameBoard().getCell(1), 
            mainController.getPlayer(0).getAllProperties().get(0)
        );
        mainController.getPlayer(0).resetProperties();
        assertEquals(0, mainController.getPlayer(0).getAllProperties().size());
    }
}
