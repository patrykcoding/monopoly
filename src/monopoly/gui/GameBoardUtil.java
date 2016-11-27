package monopoly.gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import monopoly.Cell;
import monopoly.GameBoard;

public class GameBoardUtil {
    
    public static Dimension calculateDimension(int i) {
        i = i - 4;
        int shortSide = i / 4;
        int longSide = (i - (shortSide * 2)) / 2;
        return new Dimension(longSide, shortSide);
    }
	
    public static List<Cell> getEastCells(GameBoard board) {
        Dimension dimension = calculateDimension(board.getCellSize());
        int shortSide = dimension.height;
        List<Cell> cells = new ArrayList<>();
        for (int i = board.getCellSize() - shortSide; i <= board.getCellSize() - 1; i++) {
                cells.add(board.getCell(i));
        }
        return cells;
    }
	
    public static List<Cell> getNorthCells(GameBoard board) {
        Dimension dimension = calculateDimension(board.getCellSize());
        int longSide = dimension.width;
        int shortSide = dimension.height;
        List<Cell> cells = new ArrayList<>();
        for (int i = longSide + 2 + shortSide; i <= longSide + 2 + shortSide + longSide + 1; i++)
                cells.add(board.getCell(i));
        return cells;
    }
	
    public static List<Cell> getSouthCells(GameBoard board) {
        Dimension dimension = calculateDimension(board.getCellSize());
        int longSide = dimension.width;
        List<Cell> cells = new ArrayList<>();
        for (int i = longSide + 1; i >= 0; i--)
                cells.add(board.getCell(i));
        return cells;
    }

    public static List<Cell> getWestCells(GameBoard board) {
        Dimension dimension = calculateDimension(board.getCellSize());
        int longSide = dimension.width;
        int shortSide = dimension.height;
        List<Cell> cells = new ArrayList<>();
        for (int i = longSide + 1 + shortSide; i > longSide + 1; i--)
                cells.add(board.getCell(i));
        return cells;
    }
}
