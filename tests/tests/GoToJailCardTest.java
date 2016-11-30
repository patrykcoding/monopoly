package tests;

import junit.framework.TestCase;
import monopoly.Card;
import monopoly.Cell;
import monopoly.MainController;
import monopoly.cards.JailCard;
import monopoly.enums.CardType;
import tests.gameboards.GameBoardCCJail;
import tests.mocks.MockGUI;

public class GoToJailCardTest extends TestCase {
    private MainController mainController;
    private final Card jailCard = new JailCard(CardType.CC);
    
    @Override
    protected void setUp() {
        mainController = new MainController();
        mainController.setGameBoard(new GameBoardCCJail());
        mainController.setNumberOfPlayers(1);
        mainController.reset();
        mainController.setGUI(new MockGUI());
        mainController.getGameBoard().addCard(jailCard);
    }
    
    public void testJailCardAction() {
        Card card = mainController.drawCCCard();
        assertEquals(jailCard, card);
        card.applyAction(mainController);
        Cell cell = mainController.getPlayer(0).getPosition();
        assertEquals(mainController.getGameBoard().queryCell("Jail"), cell);
    }
    
    public void testJailCardLabel() {
        assertEquals(
            "Go directly to Jail without collecting $200 when passing GO", 
            jailCard.toString()
        );
    }
    
    public void testJailCardUI() {
        mainController.movePlayer(mainController.getPlayer(0), 1);
        assertTrue(mainController.getGUI().isDrawCardButtonEnabled());
        assertFalse(mainController.getGUI().isEndTurnButtonEnabled());
        mainController.buttonDrawCardClicked();
        assertFalse(mainController.getGUI().isDrawCardButtonEnabled());
        Cell cell = mainController.getPlayer(0).getPosition();
        assertEquals(mainController.getGameBoard().queryCell("Jail"), cell);
        assertTrue(mainController.getGUI().isEndTurnButtonEnabled());
    }
}
