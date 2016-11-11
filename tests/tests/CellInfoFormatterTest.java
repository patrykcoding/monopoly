package tests;

import monopoly.gui.infoformatters.GoCellInfoFormatter;
import monopoly.gui.InfoFormatter;
import junit.framework.TestCase;
import monopoly.cells.GoCell;
import monopoly.Player;
import monopoly.cells.PropertyCell;

public class CellInfoFormatterTest extends TestCase {
    
    public void testGoCellTest() {
        GoCell cell = new GoCell();
        String goLabel = GoCellInfoFormatter.GO_CELL_LABEL;
        assertEquals(goLabel, InfoFormatter.cellInfo(cell));
    }
    
    public void testPropertyCellText() {
        String propertyName = "Blue 1";
        String propertyColor = "blue";
        String ownerName = "Owner1";
        int numHouses = 2;
        int propertyValue = 120;
        String propertyLabel = "<html><b><font color='" +
                                propertyColor +"'>" + 
                                propertyName + "</font></html>";
        String propertyToolTip = "<html><b><font color='" +
                                propertyColor +"'>" + 
                                propertyName + "</font></b><br>" +
				"$" + propertyValue +
				"<br>Owner: " + ownerName +
				"<br>* " + numHouses +
				"</html>";
        PropertyCell cell = new PropertyCell();
        cell.setName(propertyName);
        cell.setPrice(propertyValue);
        cell.setColorGroup(propertyColor);
        Player p = new Player();
        p.setName(ownerName);
        cell.setPlayer(p);
        cell.setNumHouses(numHouses);
        assertEquals(propertyLabel, InfoFormatter.cellInfo(cell));
        assertEquals(propertyToolTip, InfoFormatter.cellToolTip(cell));
    }
}
