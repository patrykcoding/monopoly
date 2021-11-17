package monopoly.gui.infoformatters;

import monopoly.Cell;
import monopoly.gui.CellInfoFormatter;

public class GotoJailCellInfoFormatter implements CellInfoFormatter {

    public static final String GOTO_JAIL_LABEL = "<html><b>Go to Jail</b></html>";

    /**
     * Format the GotoJail cell
     * @param cell
     * @return string containing the cell name with specific format
     */
    @Override
    public String format(Cell cell) {
    	return GOTO_JAIL_LABEL;
    }

    /**
     * Format ToolTip of the GotoJail cell
     * @param cell
     * @return string containing the cell name with specific format
     */
    @Override
    public String formatToolTip(Cell cell) {
        return GOTO_JAIL_LABEL;
    }
}
