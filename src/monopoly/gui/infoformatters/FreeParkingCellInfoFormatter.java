package monopoly.gui.infoformatters;

import monopoly.Cell;
import monopoly.gui.CellInfoFormatter;

public class FreeParkingCellInfoFormatter implements CellInfoFormatter {
    
    public static final String FP_CELL_LABEL = "<html><b>Free Parking</b></html>";
    
    @Override
    public String format(Cell cell) {
        return FP_CELL_LABEL;
    }
    
    @Override
    public String formatToolTip(Cell cell) {
        return FP_CELL_LABEL;
    }
}
