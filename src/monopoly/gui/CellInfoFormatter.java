package monopoly.gui;

import monopoly.Cell;

public interface CellInfoFormatter {
    public String format(Cell cell);
    public String formatToolTip(Cell cell);
}
