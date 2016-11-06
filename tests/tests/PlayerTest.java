package tests;

import mocks.MockGUI;
import junit.framework.TestCase;
import monopoly.Cell;
import monopoly.GameBoard;
import monopoly.GameMaster;
import monopoly.Player;
import monopoly.PropertyCell;
import monopoly.SimpleGameBoard;

public class PlayerTest extends TestCase {

    GameMaster gameMaster;
	
    @Override
    protected void setUp() throws Exception {
        gameMaster = new GameMaster();
        gameMaster.setGameBoard(new SimpleGameBoard());
        gameMaster.setGUI(new MockGUI());
        gameMaster.setTestMode(true);
        gameMaster.reset();
    }
    
    public void testPurchaseProperty() {
        gameMaster.setNumberOfPlayers(1);
        gameMaster.movePlayer(0, 3);
        Player player = gameMaster.getPlayer(0);
        player.purchase();
        assertEquals(1380, player.getMoney());
        assertEquals("Blue 3", player.getProperty(0).getName());
        PropertyCell cell =
        (PropertyCell) gameMaster.getGameBoard().queryCell("Blue 3");
        assertSame(player, cell.getPlayer());
    }

    public void testSameGoCell() {
        GameBoard gameboard = gameMaster.getGameBoard();
        gameMaster.setNumberOfPlayers(2);
        
        Cell go = gameboard.queryCell("Go");
        assertSame(go, gameMaster.getPlayer(0).getPosition());
        assertSame(go, gameMaster.getPlayer(1).getPosition());
    }
	
    public void testPayRentTo() {
        gameMaster.setNumberOfPlayers(2);
        gameMaster.movePlayer(0,4);
        gameMaster.getCurrentPlayer().purchase();
        gameMaster.btnEndTurnClicked();
        gameMaster.movePlayer(1,4);
        gameMaster.btnEndTurnClicked();
        assertTrue(gameMaster.getPlayer(1).isBankrupt());
        assertEquals(2800, gameMaster.getPlayer(0).getMoney());
    }
	
    public void testExchangeProperty() {
        gameMaster.setNumberOfPlayers(2);
        gameMaster.movePlayer(0,3);
        gameMaster.getCurrentPlayer().purchase();
        gameMaster.btnEndTurnClicked();
        gameMaster.getPlayer(0).exchangeProperty(gameMaster.getPlayer(1));
        assertEquals(1,gameMaster.getCurrentPlayer().getPropertyNumber());
    }
	
    public void testPurchaseHouse() {
        gameMaster.setNumberOfPlayers(1);
        gameMaster.startGame();
        gameMaster.movePlayer(gameMaster.getCurrentPlayerIndex(),1);
        gameMaster.getCurrentPlayer().purchase();
        gameMaster.btnEndTurnClicked();
        gameMaster.movePlayer(0,1);
        gameMaster.getCurrentPlayer().purchase();
        gameMaster.btnEndTurnClicked();
        gameMaster.movePlayer(0,1);
        gameMaster.getCurrentPlayer().purchase();
        gameMaster.btnEndTurnClicked();
        gameMaster.getCurrentPlayer().purchaseHouse(gameMaster, "blue",2);
        assertEquals("blue", gameMaster.getCurrentPlayer().getMonopolies(gameMaster)[0]);
        assertEquals(880, gameMaster.getCurrentPlayer().getMoney());
    }

    public void testResetProperty() {
        gameMaster.setNumberOfPlayers(1);
        gameMaster.movePlayer(0,1);
        gameMaster.getCurrentPlayer().purchase();
        assertEquals(
            gameMaster.getGameBoard().getCell(1), 
            gameMaster.getCurrentPlayer().getAllProperties()[0]
        );
        gameMaster.getCurrentPlayer().resetProperty();
        assertEquals(0,gameMaster.getCurrentPlayer().getAllProperties().length);
    }
}
