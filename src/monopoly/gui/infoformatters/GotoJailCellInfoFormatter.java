package monopoly.gui.infoformatters;

import monopoly.Cell;
import monopoly.gui.CellInfoFormatter;

public class GotoJailCellInfoFormatter implements CellInfoFormatter {

    public static final String GOTO_JAIL_LABEL = "<html><b>Go to Jail</b></html>";

    @Override
    public String format(Cell cell) {
    	return GOTO_JAIL_LABEL;
    }
    
    @Override
    public String formatToolTip(Cell cell) {
        return GOTO_JAIL_LABEL;
    }
}
