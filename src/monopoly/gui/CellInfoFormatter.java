package monopoly.gui;

import monopoly.Cell;

public interface CellInfoFormatter {
    /**
     * @param cell
     * @return string containing the cell name with specific format
     */
    public String format(Cell cell);

    /**
     * @param cell
     * @return string containing the cell name with specific format
     */
    public String formatToolTip(Cell cell);
}
