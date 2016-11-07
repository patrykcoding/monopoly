package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import monopoly.Card;
import monopoly.Cell;
import tests.gameboards.GameBoardCCMovePlayer;
import monopoly.GameMaster;
import monopoly.cards.MovePlayerCard;

public class MovePlayerCardTest extends TestCase {
    GameMaster gameMaster;
    Card movePlayerCard;
    
    @Override
    protected void setUp() {
        gameMaster = new GameMaster();
        gameMaster.setGameBoard(new GameBoardCCMovePlayer());
        gameMaster.setNumberOfPlayers(1);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
        movePlayerCard = new MovePlayerCard("Blue 1", Card.TYPE_CC);
        gameMaster.getGameBoard().addCard(movePlayerCard);
    }
    
    public void testJailCardLabel() {
        assertEquals("Go to Blue 1", movePlayerCard.toString());
    }
    
    public void testMovePlayerCardAction() {
        Card card = gameMaster.drawCCCard();
        assertEquals(movePlayerCard, card);
        card.applyAction(gameMaster);
        Cell cell = gameMaster.getCurrentPlayer().getPosition();
        assertEquals(gameMaster.getGameBoard().queryCell("Blue 1"), cell);
    }
    
    public void testMovePlayerCardUI() {
        gameMaster.movePlayer(0, 2);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
        Cell cell = gameMaster.getCurrentPlayer().getPosition();
        assertEquals(gameMaster.getGameBoard().queryCell("Blue 1"), cell);
        assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
        assertEquals(1700, gameMaster.getCurrentPlayer().getMoney());
    }
}
