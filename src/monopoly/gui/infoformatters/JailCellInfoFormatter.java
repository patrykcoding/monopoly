package monopoly.gui.infoformatters;

import monopoly.Cell;
import monopoly.gui.CellInfoFormatter;

public class JailCellInfoFormatter implements CellInfoFormatter {

    public static final String JAIL_CELL_LABEL = "<html><b>Jail</b></html>";

    @Override
    public String format(Cell cell) {
        return JAIL_CELL_LABEL;
    }
    
    @Override
    public String formatToolTip(Cell cell) {
        return JAIL_CELL_LABEL;
    }

}
