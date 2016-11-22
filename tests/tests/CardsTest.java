package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import monopoly.Card;
import monopoly.enums.CardType;
import tests.gameboardsTests.GameBoardCCGainMoney;
import monopoly.MainController;
import monopoly.cards.MoneyCard;

public class CardsTest extends TestCase {
    private Card ccCard, chanceCard;
    
    private MainController mainCtrl;

    @Override
    protected void setUp() {
        mainCtrl = new MainController();
        mainCtrl.setGameBoard(new GameBoardCCGainMoney());
        mainCtrl.setNumberOfPlayers(1);
        mainCtrl.reset();
        mainCtrl.setGUI(new MockGUI());
        ccCard = new MoneyCard("Get 50 dollars", 50, CardType.CC);
        chanceCard = new MoneyCard("Lose 50 dollars", -50, CardType.CHANCE);
        mainCtrl.getGameBoard().addCard(ccCard);
    }
    
    public void testCardType() {
        Card card = mainCtrl.drawCCCard();
        assertEquals(CardType.CC, ccCard.getCardType());
        card = mainCtrl.drawChanceCard();
        assertEquals(CardType.CHANCE, chanceCard.getCardType());
    }
}
