package monopoly.gui.infoformatters;

import monopoly.Cell;
import monopoly.Player;
import monopoly.cells.PropertyCell;
import monopoly.gui.CellInfoFormatter;

public class PropertyCellInfoFormatter implements CellInfoFormatter {
    @Override
    public String format(Cell cell) {
        PropertyCell c = (PropertyCell)cell;
        StringBuilder buf = new StringBuilder();
        buf.append("<html><b><font color='")
                .append(c.getColorGroup().name())
                .append("'>")
                .append(cell.getName())
                .append("</font>")
                .append("</html>");
        return buf.toString();
    }
    
    @Override
    public String formatToolTip(Cell cell) {
        PropertyCell c = (PropertyCell)cell;
        StringBuilder buf = new StringBuilder();
        Player owner = cell.getOwner();
        String ownerName = "";
        if (owner != null) {
            ownerName = owner.getName();
        }
        buf.append("<html><b><font color='")
                .append(c.getColorGroup())
                .append("'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("Property price: $")
                .append(c.getPrice())
                .append("<br>Rent price: $")
                .append(c.getRent())
                .append("<br><br>Owner: ")
                .append(ownerName)
                .append("<br><br>Houses ⌂: ")
                .append(c.getNumHouses())
                .append("<br>House price: $")
                .append(c.getHousePrice())
                .append("</html>");
        return buf.toString();
    }
}
