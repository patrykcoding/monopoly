package monopoly.gui.infoformatters;

import monopoly.Cell;
import monopoly.Player;
import monopoly.cells.UtilityCell;
import monopoly.gui.CellInfoFormatter;

public class UtilCellInfoFormatter implements CellInfoFormatter {

    @Override
    public String format(Cell cell) {
        StringBuilder buf = new StringBuilder();
        buf.append("<html><b><font color='olive'>")
                .append(cell.getName())
                .append("</font>")
                .append("</html>");
        return buf.toString();
    }
    
    @Override
    public String formatToolTip(Cell cell) {
        UtilityCell c = (UtilityCell)cell;
        StringBuilder buf = new StringBuilder();
        Player owner = cell.getOwner();
        String ownerName = "";
        if (owner != null) {
                ownerName = owner.getName();
        }
        buf.append("<html><b><font color='olive'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("Price: $")
                .append(c.getPrice())
                .append("<br><br>Owner: ")
                .append(ownerName)
                .append("</html>");
        return buf.toString();
    }
}
