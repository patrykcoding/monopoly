package tests;

import monopoly.gui.infoformatters.GoCellInfoFormatter;
import monopoly.gui.InfoFormatter;
import junit.framework.TestCase;
import monopoly.cells.GoCell;
import monopoly.Player;
import monopoly.cells.PropertyCell;
import monopoly.enums.ColorGroup;

public class CellInfoFormatterTest extends TestCase {
    
    public void testGoCellTest() {
        GoCell cell = new GoCell();
        String goLabel = GoCellInfoFormatter.GO_CELL_LABEL;
        assertEquals(goLabel, InfoFormatter.cellInfo(cell));
    }
    
    public void testPropertyCellText() {
        String propertyName = "Blue 1";
        ColorGroup propertyColor = ColorGroup.BLUE;
        String ownerName = "Owner1";
        int numHouses = 2;
        int propertyValue = 120;
        int housePrice = 100;
        int rentPrice = 200;
        
        String propertyLabel = "<html><b><font color='" +
                                propertyColor +"'>" + 
                                propertyName + "</font></html>";
        String propertyToolTip = "<html><b><font color='" +
                                propertyColor +"'>" + 
                                propertyName + "</font></b><br>" +
				"Property price: $" + propertyValue +
				"<br>Rent price: $" + rentPrice +
				"<br><br>Owner: " + ownerName +
				"<br><br>Houses ⌂: " + numHouses +
                                "<br>House price: $" + housePrice +
				"</html>";
        PropertyCell cell = new PropertyCell();
        cell.setName(propertyName);
        cell.setPrice(propertyValue);
        cell.setRent(rentPrice);
        cell.setColorGroup(propertyColor);
        cell.setHousePrice(100);
        Player p = new Player(cell);
        p.setName(ownerName);
        cell.setPlayer(p);
        cell.setNumHouses(numHouses);
        assertEquals(propertyLabel, InfoFormatter.cellInfo(cell));
        assertEquals(propertyToolTip, InfoFormatter.cellToolTip(cell));
    }
}
