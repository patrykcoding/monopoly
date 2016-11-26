package tests.gameboards;

import monopoly.GameBoard;
import monopoly.cells.PropertyCell;
import monopoly.enums.ColorGroup;

public class GameBoard14 extends GameBoard {
    public GameBoard14() {
        super();
        
        PropertyCell blue1 = new PropertyCell();
        PropertyCell blue2 = new PropertyCell();
        PropertyCell blue3 = new PropertyCell();
        PropertyCell green1 = new PropertyCell();
        PropertyCell green2 = new PropertyCell();
        PropertyCell green3 = new PropertyCell();
        PropertyCell red1 = new PropertyCell();
        PropertyCell red2 = new PropertyCell();
        PropertyCell red3 = new PropertyCell();
        PropertyCell purple1 = new PropertyCell();
        PropertyCell purple2 = new PropertyCell();
        PropertyCell yellow1 = new PropertyCell();
        PropertyCell yellow2 = new PropertyCell();

        blue1.setName("Blue 1");
        blue1.setColorGroup(ColorGroup.BLUE);
        blue2.setName("Blue 2");
        blue2.setColorGroup(ColorGroup.BLUE);
        blue3.setName("Blue 3");
        blue3.setColorGroup(ColorGroup.BLUE);
        green1.setName("Green 1");
        green1.setColorGroup(ColorGroup.GREEN);
        green2.setName("Green 2");
        green2.setColorGroup(ColorGroup.GREEN);
        green3.setName("Green 3");
        green3.setColorGroup(ColorGroup.GREEN);
        red1.setName("Red 1");
        red1.setColorGroup(ColorGroup.RED);
        red2.setName("Red 2");
        red2.setColorGroup(ColorGroup.RED);
        red3.setName("Red 3");
        red3.setColorGroup(ColorGroup.RED);
        purple1.setName("Purple 1");
        purple1.setColorGroup(ColorGroup.PURPLE);
        purple2.setName("Purple 2");		
        purple2.setColorGroup(ColorGroup.PURPLE);
        yellow1.setName("Yellow 1");
        yellow1.setColorGroup(ColorGroup.YELLOW);
        yellow2.setName("Yellow 2");
        yellow2.setColorGroup(ColorGroup.YELLOW);
		
        blue1.setPrice(100);
        blue2.setPrice(100);
        blue3.setPrice(120);
        green1.setPrice(200);
        green2.setPrice(240);
        green3.setPrice(260);
        red1.setPrice(300);
        red2.setPrice(300);
        red3.setPrice(320);
        purple1.setPrice(340);
        purple2.setPrice(360);
        yellow1.setPrice(400);
        yellow2.setPrice(420);
		

        blue1.setRent(10);
        blue2.setRent(10);
        blue3.setRent(12);
        green1.setRent(20);
        green2.setRent(24);
        green3.setRent(26);
        red1.setRent(30);
        red2.setRent(30);
        red3.setRent(32);
        purple1.setRent(34);
        purple2.setRent(36);
        yellow1.setRent(40);
        yellow2.setRent(42);

        blue1.setHousePrice(30);
        blue2.setHousePrice(30);
        blue3.setHousePrice(30);
        green1.setHousePrice(40);
        green2.setHousePrice(40);
        green3.setHousePrice(40);
        red1.setHousePrice(50);
        red2.setHousePrice(50);
        red3.setHousePrice(50);
        purple1.setHousePrice(60);
        purple2.setHousePrice(60);
        yellow1.setHousePrice(70);
        yellow2.setHousePrice(70);

        super.addCell(blue1);
        super.addCell(blue2);
        super.addCell(blue3);
        super.addCell(green1);
        super.addCell(green2);
        super.addCell(green3);
        super.addCell(red1);
        super.addCell(red2);
        super.addCell(red3);
        super.addCell(purple1);
        super.addCell(purple2);
        super.addCell(yellow1);
        super.addCell(yellow2);
    }
}
