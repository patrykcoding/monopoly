package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import monopoly.Card;
import tests.gameboards.GameBoardCCLoseMoney;
import monopoly.MainController;
import monopoly.cards.MoneyCard;

public class LoseMoneyCardTest extends TestCase {
    private MainController gameMaster;
    private Card loseMoneyCard;

    @Override
    protected void setUp() {
        gameMaster = new MainController();
        gameMaster.setGameBoard(new GameBoardCCLoseMoney());
        gameMaster.setNumberOfPlayers(1);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
        loseMoneyCard = new MoneyCard("Pay 20 dollars", -20, Card.TYPE_CC);
        gameMaster.getGameBoard().addCard(loseMoneyCard);
    }
    
    public void testLoseMoneyCardAction() {
        int origMoney = gameMaster.getCurrentPlayer().getMoney();
        Card card = gameMaster.drawCCCard();
        assertEquals(loseMoneyCard, card);
        card.applyAction(gameMaster);
        assertEquals(origMoney - 20, gameMaster.getCurrentPlayer().getMoney());
    }
    
    public void testLoseMoneyCardUI() {
        gameMaster.movePlayer(0, 1);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
    }
}
