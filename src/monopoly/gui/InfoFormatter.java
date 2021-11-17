package monopoly.gui;

import java.util.HashMap;
import java.util.Map;
import monopoly.Cell;
import monopoly.cells.CardCell;
import monopoly.cells.FreeParkingCell;
import monopoly.cells.GoCell;
import monopoly.cells.GoToJailCell;
import monopoly.cells.JailCell;
import monopoly.cells.PropertyCell;
import monopoly.cells.RailRoadCell;
import monopoly.cells.UtilityCell;
import monopoly.gui.infoformatters.CCCellInfoFormatter;
import monopoly.gui.infoformatters.FreeParkingCellInfoFormatter;
import monopoly.gui.infoformatters.GoCellInfoFormatter;
import monopoly.gui.infoformatters.GotoJailCellInfoFormatter;
import monopoly.gui.infoformatters.JailCellInfoFormatter;
import monopoly.gui.infoformatters.PropertyCellInfoFormatter;
import monopoly.gui.infoformatters.RRCellInfoFormatter;
import monopoly.gui.infoformatters.UtilCellInfoFormatter;

public class InfoFormatter {
    static Map<Object, CellInfoFormatter> cellInfoFormatters;
    
    static {
        cellInfoFormatters = new HashMap<>();
        addFormatters();
    }

    /**
     * add infoFormatter of each cell type in the game to cellInfoFormatters HashMap.
     */
    private static void addFormatters() {
        cellInfoFormatters.put(PropertyCell.class, new PropertyCellInfoFormatter());
        cellInfoFormatters.put(GoCell.class, new GoCellInfoFormatter());
        cellInfoFormatters.put(JailCell.class, new JailCellInfoFormatter());
        cellInfoFormatters.put(GoToJailCell.class, new GotoJailCellInfoFormatter());
        cellInfoFormatters.put(FreeParkingCell.class, new FreeParkingCellInfoFormatter());
        cellInfoFormatters.put(RailRoadCell.class, new RRCellInfoFormatter());
        cellInfoFormatters.put(UtilityCell.class, new UtilCellInfoFormatter());
        cellInfoFormatters.put(CardCell.class, new CCCellInfoFormatter());
    }

    /**
     * @param cell
     * @return the cell Info which is the format of the given cell's name
     * based on the class(type) of the given cell by the help of its appropriate formatter.
     */
    public static String cellInfo(Cell cell) {
        CellInfoFormatter formatter = cellInfoFormatters.get(cell.getClass());
        return formatter.format(cell);
    }

    /**
     * @param cell
     * @return the cell ToolTip which is the formatToolTip of the given cell's name
     * based on the class(type) of the given cell by the help of its appropriate formatter.
     */
    public static String cellToolTip(Cell cell) {
        CellInfoFormatter formatter = cellInfoFormatters.get(cell.getClass());
        return formatter.formatToolTip(cell);
    }

}
