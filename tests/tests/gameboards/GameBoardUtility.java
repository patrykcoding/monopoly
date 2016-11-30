package tests.gameboards;

import monopoly.GameBoard;
import monopoly.cells.FreeParkingCell;
import monopoly.cells.GoToJailCell;
import monopoly.cells.JailCell;
import monopoly.cells.PropertyCell;
import monopoly.cells.RailRoadCell;
import monopoly.cells.UtilityCell;
import monopoly.enums.ColorGroup;

public class GameBoardUtility extends GameBoard {
    public GameBoardUtility() {
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
        UtilityCell utility1 = new UtilityCell();
        UtilityCell utility2 = new UtilityCell();

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

        railroad2.setName("Railroad C");
        railroad2.setPrice(railroadPrice);
        railroad2.setBaseRent(railroadBaseRent);


        utility1.setName("Utility 1");
        utility2.setName("Utility 2");
        UtilityCell.setPrice(150);

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
        addCell(utility1);
        super.addCell(blue2);
        addCell(freeParking);
        super.addCell(green1);
        addCell(railroad2);
        addCell(goToJail);
        super.addCell(green2);
        addCell(utility2);
    }
}
