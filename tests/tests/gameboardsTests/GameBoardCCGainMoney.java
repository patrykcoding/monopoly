package tests.gameboardsTests;

import monopoly.Card;
import monopoly.CardType;
import monopoly.cells.CardCell;
import monopoly.GameBoard;
import monopoly.cells.JailCell;
import monopoly.cards.MoneyCard;
import monopoly.cells.PropertyCell;

public class GameBoardCCGainMoney extends GameBoard {
    public GameBoardCCGainMoney() {
        super();
        
        PropertyCell blue1 = new PropertyCell();
        PropertyCell blue2 = new PropertyCell();
        CardCell cc1 = new CardCell(CardType.CC, "Community Chest 1");
        JailCell jail = new JailCell();
        CardCell chance1 = new CardCell(CardType.CHANCE, "Chance 1");

        Card ccCard1 = new MoneyCard("Win $50", 50, CardType.CC);
        Card chanceCard1 = new MoneyCard("Win $30", 30, CardType.CHANCE);
		
        blue1.setName("Blue 1");
        blue2.setName("Blue 2");

        blue1.setColorGroup("blue");
        blue2.setColorGroup("blue");

        blue1.setPrice(100);
        blue2.setPrice(100);

        blue1.setRent(10);
        blue2.setRent(10);

        blue1.setHousePrice(50);
        blue2.setHousePrice(50);

        super.addCard(ccCard1);
        super.addCard(chanceCard1);
		
        addCell(cc1);
        super.addCell(blue1);
        addCell(jail);
        super.addCell(blue2);
        addCell(chance1);	
    }
}
