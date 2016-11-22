package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import monopoly.Card;
import monopoly.enums.CardType;
import tests.gameboards.GameBoardCCGainMoney;
import monopoly.MainController;
import monopoly.cards.MoneyCard;

public class GainMoneyCardTest extends TestCase {
    private Card gainMoneyCard;
    private MainController mainCtl;

    @Override
    protected void setUp() {
        mainCtl = new MainController();
        mainCtl.setGameBoard(new GameBoardCCGainMoney());
        mainCtl.setNumberOfPlayers(1);
	mainCtl.reset();
	mainCtl.setGUI(new MockGUI());
	gainMoneyCard = new MoneyCard("Get 50 dollars", 50, CardType.CC);
        mainCtl.getGameBoard().addCard(gainMoneyCard);
    }
    
    public void testGainMoneyCardAction() {
        int origMoney = mainCtl.getPlayer(0).getMoney();
	Card card = mainCtl.drawCCCard();
	assertEquals(gainMoneyCard, card);
	card.applyAction(mainCtl);
	assertEquals(origMoney + 50, mainCtl.getPlayer(0).getMoney());
    }
    
    public void testGainMoneyCardUI() {
        mainCtl.movePlayer(mainCtl.getPlayer(0), 1);
        assertTrue(mainCtl.getGUI().isDrawCardButtonEnabled());
        assertFalse(mainCtl.getGUI().isEndTurnButtonEnabled());
        mainCtl.btnDrawCardClicked();
        assertFalse(mainCtl.getGUI().isDrawCardButtonEnabled());
	assertTrue(mainCtl.getGUI().isEndTurnButtonEnabled());
    }
}
