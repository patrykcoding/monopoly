package tests.gameboards;

import monopoly.GameBoard;
import monopoly.cells.FreeParkingCell;
import monopoly.cells.GoToJailCell;
import monopoly.cells.JailCell;
import monopoly.cells.PropertyCell;
import monopoly.cells.RailRoadCell;
import monopoly.enums.ColorGroup;

public class GameBoardRailRoad extends GameBoard {
    public GameBoardRailRoad() {
        super();
	
        int railroadBaseRent = 25;
        int railroadPrice = 200;
        
        PropertyCell blue1 = new PropertyCell();
        PropertyCell blue2 = new PropertyCell();
        PropertyCell green1 = new PropertyCell();
        PropertyCell green2 = new PropertyCell();
        JailCell jail = new JailCell();
        GoToJailCell goToJail = new GoToJailCell();
        FreeParkingCell freeParking = new FreeParkingCell();
        RailRoadCell railroad1 = new RailRoadCell();
        RailRoadCell railroad2 = new RailRoadCell();
        RailRoadCell railroad3 = new RailRoadCell();
        RailRoadCell railroad4 = new RailRoadCell();

        blue1.setName("Blue 1");
        blue2.setName("Blue 2");
        green1.setName("Green 1");
        green2.setName("Green 2");

        blue1.setColorGroup(ColorGroup.BLUE);
        blue2.setColorGroup(ColorGroup.BLUE);
        green1.setColorGroup(ColorGroup.GREEN);
        green2.setColorGroup(ColorGroup.GREEN);

        railroad1.setName("Railroad A");
        railroad1.setPrice(railroadPrice);
        railroad1.setBaseRent(railroadBaseRent);

        railroad2.setName("Railroad B");
        railroad2.setPrice(railroadPrice);
        railroad2.setBaseRent(railroadBaseRent);

        railroad4.setName("Railroad C");
        railroad4.setPrice(railroadPrice);
        railroad4.setBaseRent(railroadBaseRent);

        railroad4.setName("Railroad D");
        railroad4.setPrice(railroadPrice);
        railroad4.setBaseRent(railroadBaseRent);

        blue1.setPrice(100);
        blue2.setPrice(100);
        green1.setPrice(200);
        green2.setPrice(240);

        blue1.setRent(10);
        blue2.setRent(10);
        green1.setRent(20);
        green2.setRent(20);

        blue1.setHousePrice(50);
        blue2.setHousePrice(50);
        green1.setHousePrice(70);
        green2.setHousePrice(70);

        addCell(railroad1);
        super.addCell(blue1);
        addCell(jail);
        addCell(railroad2);
        super.addCell(blue2);
        addCell(freeParking);
        super.addCell(green1);
        addCell(railroad3);
        addCell(goToJail);
        super.addCell(green2);
        addCell(railroad4);
    }
}
