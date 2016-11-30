package tests;

import junit.framework.TestCase;
import monopoly.Card;
import monopoly.Cell;
import monopoly.MainController;
import monopoly.cards.MovePlayerCard;
import monopoly.enums.CardType;
import tests.gameboards.GameBoardCCMovePlayer;
import tests.mocks.MockGUI;

public class MovePlayerCardTest extends TestCase {
    private MainController mainController;
    private Card movePlayerCard;
    
    @Override
    protected void setUp() {
        mainController = new MainController();
        mainController.setGameBoard(new GameBoardCCMovePlayer());
        mainController.setNumberOfPlayers(1);
        mainController.reset();
        mainController.setGUI(new MockGUI());
        movePlayerCard = new MovePlayerCard("Blue 1", CardType.CC);
        mainController.getGameBoard().addCard(movePlayerCard);
    }
    
    public void testJailCardLabel() {
        assertEquals("Go to Blue 1", movePlayerCard.toString());
    }
    
    public void testMovePlayerCardAction() {
        Card card = mainController.drawCCCard();
        assertEquals(movePlayerCard, card);
        card.applyAction(mainController);
        Cell cell = mainController.getCurrentPlayer().getPosition();
        assertEquals(mainController.getGameBoard().queryCell("Blue 1"), cell);
    }
    
    public void testMovePlayerCardUI() {
        mainController.movePlayer(mainController.getCurrentPlayer(), 2);
        assertTrue(mainController.getGUI().isDrawCardButtonEnabled());
        assertFalse(mainController.getGUI().isEndTurnButtonEnabled());
        mainController.buttonDrawCardClicked();
        assertFalse(mainController.getGUI().isDrawCardButtonEnabled());
        Cell cell = mainController.getCurrentPlayer().getPosition();
        assertEquals(mainController.getGameBoard().queryCell("Blue 1"), cell);
        assertTrue(mainController.getGUI().isEndTurnButtonEnabled());
        assertEquals(1700, mainController.getCurrentPlayer().getMoney());
    }
}
