package tests;

import java.util.List;
import junit.framework.TestCase;
import monopoly.Cell;
import monopoly.GameBoard;
import monopoly.cells.PropertyCell;
import monopoly.enums.ColorGroup;
import tests.gameboards.SimpleGameBoard;

public class GameboardTest extends TestCase {

    private Cell cell;
    private GameBoard gameBoard;

    @Override
    protected void setUp() throws Exception {
        gameBoard = new GameBoard();
        cell = new PropertyCell();
        cell.setName("TempCell");
    }

    public void testAddCell() {
        assertEquals(1, gameBoard.getCellSize());
        gameBoard.addCell(cell);
        assertEquals(2, gameBoard.getCellSize());
    }

    public void testCellsForMonopoly() {
        GameBoard gameboard = new SimpleGameBoard();
        List<PropertyCell> properties = gameboard.getPropertiesInMonopoly(ColorGroup.BLUE);
        assertEquals("Blue 1", properties.get(0).getName());
        assertEquals("Blue 2", properties.get(1).getName());
        assertEquals("Blue 3", properties.get(2).getName());
        assertEquals(3, properties.size());
    }

    public void testPropertyNumberForColor() {
        PropertyCell cell1 = new PropertyCell();
        cell1.setName("Blue 1");
        cell1.setColorGroup(ColorGroup.BLUE);
        PropertyCell cell2 = new PropertyCell();
        cell2.setName("Blue 2");
        cell2.setColorGroup(ColorGroup.BLUE);
        PropertyCell cell3 = new PropertyCell();
        cell3.setName("Green 1");
        cell3.setColorGroup(ColorGroup.GREEN);

        gameBoard.addCell(cell1);
        gameBoard.addCell(cell2);
        gameBoard.addCell(cell3);
        assertEquals(2, gameBoard.getPropertyNumberForColor(ColorGroup.BLUE));
        assertEquals(1, gameBoard.getPropertyNumberForColor(ColorGroup.GREEN));
    }

    public void testQueryCell() {
        gameBoard.addCell(cell);
        assertSame(cell,gameBoard.queryCell("TempCell"));
    }

    public void testQueryCellIndex() {
        gameBoard.addCell(cell);
        assertEquals(0,gameBoard.queryCellIndex("Go"));
        assertEquals(1,gameBoard.queryCellIndex("TempCell"));
    }
}
