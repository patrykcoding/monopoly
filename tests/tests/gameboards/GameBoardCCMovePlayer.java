package tests.gameboards;

import monopoly.Card;
import monopoly.GameBoard;
import monopoly.cards.MovePlayerCard;
import monopoly.cells.CardCell;
import monopoly.cells.JailCell;
import monopoly.cells.PropertyCell;
import monopoly.enums.CardType;
import monopoly.enums.ColorGroup;

public class GameBoardCCMovePlayer extends GameBoard {
    public GameBoardCCMovePlayer() {
        super();

        PropertyCell blue1 = new PropertyCell();
        PropertyCell blue2 = new PropertyCell();
        CardCell cc1 = new CardCell(CardType.CC, "Community Chest 1");
        JailCell jail = new JailCell();
        CardCell chance1 = new CardCell(CardType.CHANCE, "Chance 1");
        
        Card ccCard1 = new MovePlayerCard("Blue 1" , CardType.CC);
        Card ccCard2 = new MovePlayerCard("Blue 2", CardType.CC);
		
        blue1.setName("Blue 1");
        blue2.setName("Blue 2");

        blue1.setColorGroup(ColorGroup.BLUE);
        blue2.setColorGroup(ColorGroup.BLUE);

        blue1.setPrice(100);
        blue2.setPrice(100);

        blue1.setRent(10);
        blue2.setRent(10);

        blue1.setHousePrice(50);
        blue2.setHousePrice(50);

        super.addCard(ccCard1);
        super.addCard(ccCard2);

        super.addCell(blue1);
        addCell(cc1);
        addCell(jail);
        super.addCell(blue2);
        addCell(chance1);
    }
}
