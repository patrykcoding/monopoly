package monopoly.gui.infoformatters;

import monopoly.Cell;
import monopoly.gui.CellInfoFormatter;

public class FreeParkingCellInfoFormatter implements CellInfoFormatter {
    
    public static final String FP_CELL_LABEL = "<html><b>Free Parking</b></html>";
    /**
     * Format the FreeParking cell
     * @param cell
     * @return string containing the cell name with specific format
     */
    @Override
    public String format(Cell cell) {
        return FP_CELL_LABEL;
    }

    /**
     * Format ToolTip of the FreeParking cell
     * @param cell
     * @return string containing the cell name with specific format
     */
    @Override
    public String formatToolTip(Cell cell) {
        return FP_CELL_LABEL;
    }
}
