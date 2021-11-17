package monopoly.gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import monopoly.Cell;
import monopoly.GameBoard;

public class GameBoardUtil {

    /**
     * take the i param which is the number of cells in the board and subtract 4 from it then calculate the shortSide and the LongSide to get the Dimension
     * @param i
     * @return Dimension Object created by the calculated width and height
     */
    public static Dimension calculateDimension(int i) {
        i = i - 4;
        int shortSide = i / 4;
        int longSide = (i - (shortSide * 2)) / 2;
        return new Dimension(longSide, shortSide);
    }

    /**
     * @param board
     * @return list of east cells in the board
     */
    public static List<Cell> getEastCells(GameBoard board) {
        Dimension dimension = calculateDimension(board.getCellSize());
        int shortSide = dimension.height;
        List<Cell> cells = new ArrayList<>();
        for (int i = board.getCellSize() - shortSide; i <= board.getCellSize() - 1; i++) {
                cells.add(board.getCell(i));
        }
        return cells;
    }

    /**
     * @param board
     * @return list of north cells in the board
     */
    public static List<Cell> getNorthCells(GameBoard board) {
        Dimension dimension = calculateDimension(board.getCellSize());
        int longSide = dimension.width;
        int shortSide = dimension.height;
        List<Cell> cells = new ArrayList<>();
        for (int i = longSide + 2 + shortSide; i <= longSide + 2 + shortSide + longSide + 1; i++)
                cells.add(board.getCell(i));
        return cells;
    }

    /**
     * @param board
     * @return list of south cells in the board
     */
    public static List<Cell> getSouthCells(GameBoard board) {
        Dimension dimension = calculateDimension(board.getCellSize());
        int longSide = dimension.width;
        List<Cell> cells = new ArrayList<>();
        for (int i = longSide + 1; i >= 0; i--)
                cells.add(board.getCell(i));
        return cells;
    }

    /**
     * @param board
     * @return list of west cells in the board
     */
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
