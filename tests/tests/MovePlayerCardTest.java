package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import monopoly.Card;
import monopoly.enums.CardType;
import monopoly.Cell;
import tests.gameboards.GameBoardCCMovePlayer;
import monopoly.MainController;
import monopoly.cards.MovePlayerCard;

public class MovePlayerCardTest extends TestCase {
    private MainController mainCtl;
    private Card movePlayerCard;
    
    @Override
    protected void setUp() {
        mainCtl = new MainController();
        mainCtl.setGameBoard(new GameBoardCCMovePlayer());
        mainCtl.setNumberOfPlayers(1);
        mainCtl.reset();
        mainCtl.setGUI(new MockGUI());
        movePlayerCard = new MovePlayerCard("Blue 1", CardType.CC);
        mainCtl.getGameBoard().addCard(movePlayerCard);
    }
    
    public void testJailCardLabel() {
        assertEquals("Go to Blue 1", movePlayerCard.toString());
    }
    
    public void testMovePlayerCardAction() {
        Card card = mainCtl.drawCCCard();
        assertEquals(movePlayerCard, card);
        card.applyAction(mainCtl);
        Cell cell = mainCtl.getCurrentPlayer().getPosition();
        assertEquals(mainCtl.getGameBoard().queryCell("Blue 1"), cell);
    }
    
    public void testMovePlayerCardUI() {
        mainCtl.movePlayer(mainCtl.getCurrentPlayer(), 2);
        assertTrue(mainCtl.getGUI().isDrawCardButtonEnabled());
        assertFalse(mainCtl.getGUI().isEndTurnButtonEnabled());
        mainCtl.btnDrawCardClicked();
        assertFalse(mainCtl.getGUI().isDrawCardButtonEnabled());
        Cell cell = mainCtl.getCurrentPlayer().getPosition();
        assertEquals(mainCtl.getGameBoard().queryCell("Blue 1"), cell);
        assertTrue(mainCtl.getGUI().isEndTurnButtonEnabled());
        assertEquals(1700, mainCtl.getCurrentPlayer().getMoney());
    }
}
