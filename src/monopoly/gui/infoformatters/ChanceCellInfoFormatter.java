package monopoly.gui.infoformatters;

import monopoly.Cell;
import monopoly.gui.CellInfoFormatter;

public class ChanceCellInfoFormatter implements CellInfoFormatter {
    
    public static final String CHANCE_CELL_LABEL = "<html><font color='teal'><b>Chance</b></font></html>";
    
    @Override
    public String format(Cell cell) {
        return CHANCE_CELL_LABEL;
    }
    
    @Override
    public String formatToolTip(Cell cell) {
        return CHANCE_CELL_LABEL;
    }
}
