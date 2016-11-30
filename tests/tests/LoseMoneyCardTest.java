package tests;

import junit.framework.TestCase;
import monopoly.Card;
import monopoly.MainController;
import monopoly.cards.MoneyCard;
import monopoly.enums.CardType;
import tests.gameboards.GameBoardCCLoseMoney;
import tests.mocks.MockGUI;

public class LoseMoneyCardTest extends TestCase {
    private MainController mainController;
    private Card loseMoneyCard;

    @Override
    protected void setUp() {
        mainController = new MainController();
        mainController.setGameBoard(new GameBoardCCLoseMoney());
        mainController.setNumberOfPlayers(1);
        mainController.reset();
        mainController.setGUI(new MockGUI());
        loseMoneyCard = new MoneyCard("Pay 20 dollars", -20, CardType.CC);
        mainController.getGameBoard().addCard(loseMoneyCard);
    }
    
    public void testLoseMoneyCardAction() {
        int originalMoney = mainController.getPlayer(0).getMoney();
        Card card = mainController.drawCCCard();
        assertEquals(loseMoneyCard, card);
        card.applyAction(mainController);
        assertEquals(originalMoney - 20, mainController.getPlayer(0).getMoney());
    }
    
    public void testLoseMoneyCardUI() {
        mainController.movePlayer(mainController.getPlayer(0), 1);
        assertTrue(mainController.getGUI().isDrawCardButtonEnabled());
        assertFalse(mainController.getGUI().isEndTurnButtonEnabled());
        mainController.buttonDrawCardClicked();
        assertFalse(mainController.getGUI().isDrawCardButtonEnabled());
        assertTrue(mainController.getGUI().isEndTurnButtonEnabled());
    }
}
