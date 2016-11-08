package tests;

import tests.mocks.MockGUI;
import junit.framework.TestCase;
import monopoly.Card;
import tests.gameboards.GameBoardCCGainMoney;
import monopoly.GameMaster;
import monopoly.cards.MoneyCard;

public class CardsTest extends TestCase {
    Card ccCard, chanceCard;
    
    GameMaster gameMaster;

    @Override
    protected void setUp() {
        gameMaster = new GameMaster();
        gameMaster.setGameBoard(new GameBoardCCGainMoney());
        gameMaster.setNumberOfPlayers(1);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
        ccCard = new MoneyCard("Get 50 dollars", 50, Card.TYPE_CC);
        chanceCard = new MoneyCard("Lose 50 dollars", -50, Card.TYPE_CHANCE);
        gameMaster.getGameBoard().addCard(ccCard);
    }
    
    public void testCardType() {
        Card card = gameMaster.drawCCCard();
        assertEquals(Card.TYPE_CC, ccCard.getCardType());
        card = gameMaster.drawChanceCard();
        assertEquals(Card.TYPE_CHANCE, chanceCard.getCardType());
    }
}
