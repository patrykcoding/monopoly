package tests;

import junit.framework.TestCase;
import monopoly.Card;
import monopoly.MainController;
import monopoly.cards.MoneyCard;
import monopoly.enums.CardType;
import tests.gameboards.GameBoardCCGainMoney;
import tests.mocks.MockGUI;

public class GainMoneyCardTest extends TestCase {
    private Card gainMoneyCard;
    private MainController mainController;

    @Override
    protected void setUp() {
        mainController = new MainController();
        mainController.setGameBoard(new GameBoardCCGainMoney());
        mainController.setNumberOfPlayers(1);
	mainController.reset();
	mainController.setGUI(new MockGUI());
	gainMoneyCard = new MoneyCard("Get 50 dollars", 50, CardType.CC);
        mainController.getGameBoard().addCard(gainMoneyCard);
    }
    
    public void testGainMoneyCardAction() {
        int originalMoney = mainController.getPlayer(0).getMoney();
	Card card = mainController.drawCCCard();
	assertEquals(gainMoneyCard, card);
	card.applyAction(mainController);
	assertEquals(originalMoney + 50, mainController.getPlayer(0).getMoney());
    }
    
    public void testGainMoneyCardUI() {
        mainController.movePlayer(mainController.getPlayer(0), 1);
        assertTrue(mainController.getGUI().isDrawCardButtonEnabled());
        assertFalse(mainController.getGUI().isEndTurnButtonEnabled());
        mainController.buttonDrawCardClicked();
        assertFalse(mainController.getGUI().isDrawCardButtonEnabled());
	assertTrue(mainController.getGUI().isEndTurnButtonEnabled());
    }
}
