package tests;

import junit.framework.TestCase;
import monopoly.Card;
import monopoly.MainController;
import monopoly.cards.MoneyCard;
import monopoly.enums.CardType;
import tests.gameboards.GameBoardCCGainMoney;
import tests.mocks.MockGUI;

public class CardsTest extends TestCase {
    private Card ccCard, chanceCard;
    private MainController mainController;

    @Override
    protected void setUp() {
        mainController = new MainController();
        mainController.setGameBoard(new GameBoardCCGainMoney());
        mainController.setNumberOfPlayers(1);
        mainController.reset();
        mainController.setGUI(new MockGUI());
        ccCard = new MoneyCard("Get 50 dollars", 50, CardType.CC);
        chanceCard = new MoneyCard("Lose 50 dollars", -50, CardType.CHANCE);
        mainController.getGameBoard().addCard(ccCard);
    }
    
    public void testCardType() {
        assertEquals(CardType.CC, ccCard.getCardType());
        assertEquals(CardType.CHANCE, chanceCard.getCardType());
    }
}
