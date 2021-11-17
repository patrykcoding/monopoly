package monopoly.gui.infoformatters;

import monopoly.Cell;
import monopoly.gui.CellInfoFormatter;

public class GoCellInfoFormatter implements CellInfoFormatter {
    
    public static final String GO_CELL_LABEL = "<html><b>Go</b></html>";

    /**
     * Format the Go cell
     * @param cell
     * @return string containing the cell name with specific format
     */
    @Override
    public String format(Cell cell) {
        return GO_CELL_LABEL;
    }

    /**
     * Format ToolTip of the Go cell
     * @param cell
     * @return string containing the cell name with specific format
     */
    @Override
    public String formatToolTip(Cell cell) {
        return GO_CELL_LABEL;
    }
}
