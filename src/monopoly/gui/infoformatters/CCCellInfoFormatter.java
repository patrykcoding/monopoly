package monopoly.gui.infoformatters;

import monopoly.Cell;
import monopoly.gui.CellInfoFormatter;

public class CCCellInfoFormatter implements CellInfoFormatter {
    /**
     * Format the CC cell
     * @param cell
     * @return string containing the cell name with specific format
     */
    @Override
    public String format(Cell cell) {
        return "<html><font color='black'><b>" + cell.getName() + "</b></font></html>";
    }

    /**
     * Format ToolTip of the CC cell
     * @param cell
     * @return string containing the cell name with specific format
     */
    @Override
    public String formatToolTip(Cell cell) {
        return "<html><font color='black'><b>" + cell.getName() + "</b></font></html>";
    }
}
