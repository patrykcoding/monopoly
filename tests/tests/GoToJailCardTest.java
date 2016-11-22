package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import monopoly.Card;
import monopoly.enums.CardType;
import monopoly.Cell;
import tests.gameboards.GameBoardCCJail;
import monopoly.MainController;
import monopoly.cards.JailCard;

public class GoToJailCardTest extends TestCase {
    private MainController mainCtl;
    private final Card jailCard = new JailCard(CardType.CC);
    
    @Override
    protected void setUp() {
        mainCtl = new MainController();
        mainCtl.setGameBoard(new GameBoardCCJail());
        mainCtl.setNumberOfPlayers(1);
        mainCtl.reset();
        mainCtl.setGUI(new MockGUI());
        mainCtl.getGameBoard().addCard(jailCard);
    }
    
    public void testJailCardAction() {
        Card card = mainCtl.drawCCCard();
        assertEquals(jailCard, card);
        card.applyAction(mainCtl);
        Cell cell = mainCtl.getPlayer(0).getPosition();
        assertEquals(mainCtl.getGameBoard().queryCell("Jail"), cell);
    }
    
    public void testJailCardLabel() {
        assertEquals(
            "Go directly to Jail without collecting $200 when passing GO", 
            jailCard.toString()
        );
    }
    
    public void testJailCardUI() {
        mainCtl.movePlayer(mainCtl.getPlayer(0), 1);
        assertTrue(mainCtl.getGUI().isDrawCardButtonEnabled());
        assertFalse(mainCtl.getGUI().isEndTurnButtonEnabled());
        mainCtl.btnDrawCardClicked();
        assertFalse(mainCtl.getGUI().isDrawCardButtonEnabled());
        Cell cell = mainCtl.getPlayer(0).getPosition();
        assertEquals(mainCtl.getGameBoard().queryCell("Jail"), cell);
        assertTrue(mainCtl.getGUI().isEndTurnButtonEnabled());
    }
}
