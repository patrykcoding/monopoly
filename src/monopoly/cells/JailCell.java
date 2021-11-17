package monopoly.cells;

import monopoly.Cell;

/**
 * JailCell class (subclass) inherits the attributes and methods from the Cell class (superclass)
 * @author owner
 */
public class JailCell extends Cell {

    public static int BAIL = 50;

    /**
     * Set the name of cell with "Jail" name.
     */
    public JailCell() {
        super.setName("Jail");
    }
}
