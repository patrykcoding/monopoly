package monopoly.gui;

import java.util.HashMap;
import java.util.Map;
import monopoly.CardCell;
import monopoly.Cell;
import monopoly.FreeParkingCell;
import monopoly.GoCell;
import monopoly.GoToJailCell;
import monopoly.JailCell;
import monopoly.PropertyCell;
import monopoly.RailRoadCell;
import monopoly.UtilityCell;

public class InfoFormatter {
    static Map<Object, CellInfoFormatter> cellInfoFormatters;
    
    static {
        cellInfoFormatters = new HashMap<>();
        addFormatters();
    }
    
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

    public static String cellInfo(Cell cell) {
        CellInfoFormatter formatter =
                (CellInfoFormatter) cellInfoFormatters.get(cell.getClass());
        return formatter.format(cell);
    }

}
