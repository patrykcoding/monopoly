package tests;

import java.util.List;
import junit.framework.TestCase;
import monopoly.Dice;
import monopoly.MainController;
import monopoly.Player;
import monopoly.RespondDialog;
import monopoly.TradeDeal;
import monopoly.TradeDialog;
import monopoly.cells.PropertyCell;
import monopoly.enums.ColorGroup;
import monopoly.gameboards.GameBoardDefault;
import monopoly.gui.MonopolyGUI;
import monopoly.gui.PlayerPanel;
import tests.mocks.MockGUI;

public class MainControllerTest extends TestCase {

    private MainController mainController;
    
    @Override
    protected void setUp() throws Exception {
        mainController = new MainController();
        mainController.setGameBoard(new GameBoardDefault());
        mainController.setNumberOfPlayers(2);
        mainController.getPlayer(0).setName("Player 1");
        mainController.getPlayer(1).setName("Player 2");
        mainController.reset();
        mainController.setGUI(new MockGUI());
        mainController.startGame();
    }

    public void testReset() {
        mainController.movePlayer(mainController.getPlayer(0), 3);
        mainController.movePlayer(mainController.getPlayer(1), 4);
        mainController.reset();

        for(int i = 0; i < mainController.getNumberOfPlayers(); i++) {
            Player player = mainController.getPlayer(i);
            assertEquals("Go", player.getPosition().getName());
        }
        assertEquals(0, mainController.getTurn());
    }
    
    public void testTradeProcess() {
        MonopolyGUI gui = mainController.getGUI();
        assertTrue(gui.isTradeButtonEnabled(0));
        assertFalse(gui.isTradeButtonEnabled(1));
        mainController.movePlayer(mainController.getPlayer(0), 1);
        assertFalse(gui.isTradeButtonEnabled(0));
        assertFalse(gui.isTradeButtonEnabled(1));
        mainController.purchase();
        assertEquals(mainController.getGameBoard().getCell(1),
                     mainController.getPlayer(0).getAllProperties().get(0));
        mainController.buttonEndTurnClicked();
        TradeDialog dialog = gui.openTradeDialog();
        int numberOfSellers =  mainController.getNumberOfPlayers() - 1;
        assertEquals(1, numberOfSellers);
        List<Player> sellerList = mainController.getSellerList();
        assertEquals(mainController.getPlayer(0), sellerList.get(0));
        TradeDeal deal = dialog.getTradeDeal(mainController);
        RespondDialog respond = gui.openRespondDialog(deal);
        Player player1 = mainController.getPlayer(0);
        Player player2 = mainController.getPlayer(1);
        assertTrue(respond.getResponse());
        mainController.completeTrade(deal);
        assertEquals(1440 + deal.getAmount(), player1.getMoney());
        assertEquals(1500 - deal.getAmount(), player2.getMoney());
        assertFalse(player1.checkProperty(deal.getPropertyName()));
        assertTrue(player2.checkProperty(deal.getPropertyName()));
    }

    public void testTurn() {
        assertEquals(0, mainController.getTurn());
        mainController.switchTurn();
        assertEquals(1, mainController.getTurn());
        mainController.switchTurn();
        assertEquals(0, mainController.getTurn());
    }

    public void testButtonGetOutOfJailClicked() {
        MonopolyGUI gui = mainController.getGUI();
        mainController.movePlayer(mainController.getPlayer(0),30);
        mainController.buttonEndTurnClicked();
        assertEquals("Jail", mainController.getPlayer(0).getPosition().getName());
        mainController.movePlayer(mainController.getPlayer(1), 2);
        mainController.buttonEndTurnClicked();
        assertTrue(gui.isGetOutOfJailButtonEnabled());
        assertTrue(mainController.getPlayer(0).isInJail());
        mainController.buttonGetOutOfJailClicked();
        assertFalse(mainController.getPlayer(0).isInJail());
        assertEquals(1450,mainController.getPlayer(0).getMoney());
    }

    public void testButtonPurchasePropertyClicked() {
        mainController.movePlayer(mainController.getPlayer(0),1);
        mainController.buttonPurchasePropertyClicked();
        assertEquals(mainController.getGameBoard().getCell(1), mainController.getPlayer(0).getAllProperties().get(0));
        assertEquals(1440, mainController.getPlayer(0).getMoney());
    }

    public void testButtonRollDiceClicked() {
        mainController.reset();
        PlayerPanel panel = new PlayerPanel(mainController, mainController.getCurrentPlayer());
        mainController.buttonRollDiceClicked(panel);
        Dice dice = mainController.getDice();
        assertEquals(0, mainController.getTurn());
        assertEquals(mainController.getGameBoard().getCell(0 + dice.getTotal()), 
                mainController.getPlayer(0).getPosition()
        );
    }

    public void testButtonTradeClicked() {
        mainController.movePlayer(mainController.getPlayer(0), 1);
        mainController.purchase();
        mainController.buttonEndTurnClicked();
        mainController.buttonTradeClicked();
        assertEquals(mainController.getGameBoard().getCell(1), mainController.getPlayer(1).getAllProperties().get(0));
        assertEquals(1640, mainController.getPlayer(0).getMoney());
        assertEquals(1300, mainController.getPlayer(1).getMoney());
    }
    
    public void testPurchaseHouse() {
        mainController.movePlayer(mainController.getPlayer(0), 1);
        mainController.purchase();
        mainController.movePlayer(mainController.getPlayer(0), 2);
        mainController.purchase();
        mainController.movePlayer(mainController.getPlayer(0), 1);
        mainController.purchase();
        mainController.purchaseHouse(ColorGroup.PURPLE, 2);
        assertEquals(ColorGroup.PURPLE, mainController.getMonopolies(mainController.getPlayer(0)).get(0));
        assertEquals(1020, mainController.getPlayer(0).getMoney());
    }
    
    public void testPurchaseProperty() {
        mainController.setNumberOfPlayers(1);
        mainController.movePlayer(mainController.getPlayer(0), 3);
        Player player = mainController.getPlayer(0);
        mainController.purchase();
        assertEquals(1440, player.getMoney());
        assertEquals("Baltic Avenue", player.getProperty(0).getName());
        PropertyCell cell =
        (PropertyCell) mainController.getGameBoard().queryCell("Baltic Avenue");
        assertSame(player, cell.getOwner());
    }
}
