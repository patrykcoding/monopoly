package tests;

import junit.framework.TestCase;
import monopoly.Card;
import monopoly.Cell;
import monopoly.GameBoardCCJail;
import monopoly.GameMaster;
import monopoly.JailCard;
import monopoly.MockGUI;

public class GoToJailCardTest extends TestCase {
    GameMaster gameMaster;
    Card jailCard = new JailCard(Card.TYPE_CC);
    
    @Override
    protected void setUp() {
        gameMaster = GameMaster.instance();
        gameMaster.setGameBoard(new GameBoardCCJail());
        gameMaster.setNumberOfPlayers(1);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
        gameMaster.getGameBoard().addCard(jailCard);
    }
    
    public void testJailCardAction() {
        Card card = gameMaster.drawCCCard();
        assertEquals(jailCard, card);
        card.applyAction();
        Cell cell = gameMaster.getCurrentPlayer().getPosition();
        assertEquals(gameMaster.getGameBoard().queryCell("Jail"), cell);
    }
    
    public void testJailCardLabel() {
        assertEquals(
            "Go directly to Jail without collecting $200 when passing GO", 
            jailCard.toString()
        );
    }
    
    public void testJailCardUI() {
        gameMaster.movePlayer(0, 1);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
        Cell cell = gameMaster.getCurrentPlayer().getPosition();
        assertEquals(gameMaster.getGameBoard().queryCell("Jail"), cell);
        assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
    }
}
