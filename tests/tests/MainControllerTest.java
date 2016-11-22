package tests;

import tests.mocks.MockGUI;
import java.util.List;

import junit.framework.TestCase;
import monopoly.Dice;
import monopoly.gameboards.GameBoardDefault;
import monopoly.MainController;
import monopoly.gui.MonopolyGUI;
import monopoly.Player;
import monopoly.RespondDialog;
import monopoly.TradeDeal;
import monopoly.TradeDialog;
import monopoly.cells.PropertyCell;
import monopoly.gui.PlayerPanel;

public class MainControllerTest extends TestCase {

    private MainController mainCtl;
    
    @Override
    protected void setUp() throws Exception {
        mainCtl = new MainController();
        mainCtl.setGameBoard(new GameBoardDefault());
        mainCtl.setNumberOfPlayers(2);
        mainCtl.getPlayer(0).setName("Player 1");
        mainCtl.getPlayer(1).setName("Player 2");
        mainCtl.reset();
        mainCtl.setGUI(new MockGUI());
        mainCtl.startGame();
    }

    public void testReset() {
        mainCtl.movePlayer(mainCtl.getPlayer(0), 3);
        mainCtl.movePlayer(mainCtl.getPlayer(1), 4);
        mainCtl.reset();

        for(int i = 0; i < mainCtl.getNumberOfPlayers(); i++) {
            Player player = mainCtl.getPlayer(i);
            assertEquals("Go", player.getPosition().getName());
        }
        assertEquals(0, mainCtl.getTurn());
    }
    
    public void testTradeProcess() {
        MonopolyGUI gui = mainCtl.getGUI();
        assertTrue(gui.isTradeButtonEnabled(0));
        assertFalse(gui.isTradeButtonEnabled(1));
        mainCtl.movePlayer(mainCtl.getPlayer(0), 1);
        assertFalse(gui.isTradeButtonEnabled(0));
        assertFalse(gui.isTradeButtonEnabled(1));
        mainCtl.purchase();
        assertEquals(mainCtl.getGameBoard().getCell(1),
                     mainCtl.getPlayer(0).getAllProperties().get(0));
        mainCtl.btnEndTurnClicked();
        TradeDialog dialog = gui.openTradeDialog();
        int numberOfSellers =  mainCtl.getNumberOfPlayers() - 1;
        assertEquals(1, numberOfSellers);
        List<Player> sellerList = mainCtl.getSellerList();
        assertEquals(mainCtl.getPlayer(0), sellerList.get(0));
        TradeDeal deal = dialog.getTradeDeal(mainCtl);
        RespondDialog respond = gui.openRespondDialog(deal);
        Player player1 = mainCtl.getPlayer(0);
        Player player2 = mainCtl.getPlayer(1);
        assertTrue(respond.getResponse());
        mainCtl.completeTrade(deal);
        assertEquals(1440 + deal.getAmount(), player1.getMoney());
        assertEquals(1500 - deal.getAmount(), player2.getMoney());
        assertFalse(player1.checkProperty(deal.getPropertyName()));
        assertTrue(player2.checkProperty(deal.getPropertyName()));
    }

    public void testTurn() {
        assertEquals(0, mainCtl.getTurn());
        mainCtl.switchTurn();
        assertEquals(1, mainCtl.getTurn());
        mainCtl.switchTurn();
        assertEquals(0, mainCtl.getTurn());
    }

    public void testButtonGetOutOfJailClicked() {
        MonopolyGUI gui = mainCtl.getGUI();
        mainCtl.movePlayer(mainCtl.getPlayer(0),30);
        mainCtl.btnEndTurnClicked();
        assertEquals("Jail", mainCtl.getPlayer(0).getPosition().getName());
        mainCtl.movePlayer(mainCtl.getPlayer(1), 2);
        mainCtl.btnEndTurnClicked();
        assertTrue(gui.isGetOutOfJailButtonEnabled());
        assertTrue(mainCtl.getPlayer(0).isInJail());
        mainCtl.btnGetOutOfJailClicked();
        assertFalse(mainCtl.getPlayer(0).isInJail());
        assertEquals(1450,mainCtl.getPlayer(0).getMoney());
    }

    public void testButtonPurchasePropertyClicked() {
        mainCtl.movePlayer(mainCtl.getPlayer(0),1);
        mainCtl.btnPurchasePropertyClicked();
        assertEquals(mainCtl.getGameBoard().getCell(1), mainCtl.getPlayer(0).getAllProperties().get(0));
        assertEquals(1440, mainCtl.getPlayer(0).getMoney());
    }

    public void testButtonRollDiceClicked() {
        mainCtl.reset();
        PlayerPanel panel = new PlayerPanel(mainCtl, mainCtl.getCurrentPlayer());
        mainCtl.btnRollDiceClicked(panel);
        Dice dice = mainCtl.getDice();
        assertEquals(0, mainCtl.getTurn());
        assertEquals(
                mainCtl.getGameBoard().getCell(0 + dice.getTotal()), 
                mainCtl.getPlayer(0).getPosition()
        );
    }

    public void testButtonTradeClicked() {
        mainCtl.movePlayer(mainCtl.getPlayer(0), 1);
        mainCtl.purchase();
        mainCtl.btnEndTurnClicked();
        mainCtl.btnTradeClicked();
        assertEquals(mainCtl.getGameBoard().getCell(1), mainCtl.getPlayer(1).getAllProperties().get(0));
        assertEquals(1640, mainCtl.getPlayer(0).getMoney());
        assertEquals(1300, mainCtl.getPlayer(1).getMoney());
    }
    
    public void testPurchaseHouse() {
        mainCtl.movePlayer(mainCtl.getPlayer(0), 1);
        mainCtl.purchase();
        mainCtl.movePlayer(mainCtl.getPlayer(0), 2);
        mainCtl.purchase();
        mainCtl.movePlayer(mainCtl.getPlayer(0), 1);
        mainCtl.purchase();
        mainCtl.purchaseHouse("purple", 2);
        assertEquals("purple", mainCtl.getMonopolies(mainCtl.getPlayer(0)).get(0));
        assertEquals(1020, mainCtl.getPlayer(0).getMoney());
    }
    
    public void testPurchaseProperty() {
        mainCtl.setNumberOfPlayers(1);
        mainCtl.movePlayer(mainCtl.getPlayer(0), 3);
        Player player = mainCtl.getPlayer(0);
        mainCtl.purchase();
        assertEquals(1440, player.getMoney());
        assertEquals("Baltic Avenue", player.getProperty(0).getName());
        PropertyCell cell =
        (PropertyCell) mainCtl.getGameBoard().queryCell("Baltic Avenue");
        assertSame(player, cell.getPlayer());
    }
}
