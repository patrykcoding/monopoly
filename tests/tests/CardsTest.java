package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import monopoly.Card;
import tests.gameboards.GameBoardCCGainMoney;
import monopoly.MainController;
import monopoly.cards.MoneyCard;

public class CardsTest extends TestCase {
    Card ccCard, chanceCard;
    
    private MainController mainCtrl;

    @Override
    protected void setUp() {
        mainCtrl = new MainController();
        mainCtrl.setGameBoard(new GameBoardCCGainMoney());
        mainCtrl.setNumberOfPlayers(1);
        mainCtrl.reset();
        mainCtrl.setGUI(new MockGUI());
        ccCard = new MoneyCard("Get 50 dollars", 50, Card.TYPE_CC);
        chanceCard = new MoneyCard("Lose 50 dollars", -50, Card.TYPE_CHANCE);
        mainCtrl.getGameBoard().addCard(ccCard);
    }
    
    public void testCardType() {
        Card card = mainCtrl.drawCCCard();
        assertEquals(Card.TYPE_CC, ccCard.getCardType());
        card = mainCtrl.drawChanceCard();
        assertEquals(Card.TYPE_CHANCE, chanceCard.getCardType());
    }
}
