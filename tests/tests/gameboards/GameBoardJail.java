package tests.gameboards;

import monopoly.GameBoard;
import monopoly.cells.GoToJailCell;
import monopoly.cells.JailCell;
import monopoly.cells.PropertyCell;
import monopoly.enums.ColorGroup;

public class GameBoardJail extends GameBoard {
    public GameBoardJail() {
        super();
	
        PropertyCell blue1 = new PropertyCell();
        PropertyCell blue2 = new PropertyCell();
        PropertyCell blue3 = new PropertyCell();
        PropertyCell green1 = new PropertyCell();
        PropertyCell green2 = new PropertyCell();
        JailCell jail = new JailCell();
        GoToJailCell goToJail = new GoToJailCell();

        blue1.setName("Blue 1");
        blue2.setName("Blue 2");
        blue3.setName("Blue 3");
        green1.setName("Green 1");
        green2.setName("Green 2");

        blue1.setColorGroup(ColorGroup.BLUE);
        blue2.setColorGroup(ColorGroup.BLUE);
        blue3.setColorGroup(ColorGroup.BLUE);
        green1.setColorGroup(ColorGroup.GREEN);
        green2.setColorGroup(ColorGroup.GREEN);

        blue1.setPrice(100);
        blue2.setPrice(100);
        blue3.setPrice(1450);
        green1.setPrice(200);
        green2.setPrice(240);

        blue1.setRent(10);
        blue2.setRent(10);
        blue3.setRent(10);
        green1.setRent(20);
        green2.setRent(20);

        blue1.setHousePrice(50);
        blue2.setHousePrice(50);
        blue3.setHousePrice(50);
        green1.setHousePrice(70);
        green2.setHousePrice(70);

        super.addCell(blue1);
        addCell(jail);
        super.addCell(blue2);
        super.addCell(blue3);
        super.addCell(green1);
        addCell(goToJail);
        super.addCell(green2);
    }
}
