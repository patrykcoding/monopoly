package monopoly.cells;

import monopoly.Cell;

/**
 *GoCell class (subclass) inherits the attributes and methods from the Cell class (superclass)
 * @author owner
 */
public class GoCell extends Cell {

    /**
     * set message 'GO' if the sell is available, if not will return false.
     */
    public GoCell() {
            super.setName("Go");
            super.setAvailable(false);
	}
}
