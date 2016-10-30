package monopoly.gui;

import monopoly.Cell;
import monopoly.Player;
import monopoly.PropertyCell;

public class PropertyCellInfoFormatter implements CellInfoFormatter {
    @Override
    public String format(Cell cell) {
        PropertyCell c = (PropertyCell)cell;
        StringBuilder buf = new StringBuilder();
        Player owner = cell.getPlayer();
        String ownerName = "";
        if (owner != null) {
        	ownerName = owner.getName();
        }
        buf.append("<html><b><font color='")
                .append(c.getColorGroup())
                .append("'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("$")
                .append(c.getPrice())
                .append("<br>Owner: ")
                .append(ownerName)
                .append("<br>* ")
                .append(c.getNumHouses())
                .append("</html>");
        return buf.toString();
    }
}
