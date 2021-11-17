package monopoly.cells;

import monopoly.Cell;
import monopoly.enums.CardType;

/**
 * CardCell class (subclass) inherits the attributes and methods from the Cell class (superclass)
 * @author owner
 */
public class CardCell extends Cell {

    private final CardType type;
    
    /**
     *
     * @param type
     * @param name
     */
    public CardCell(CardType type, String name) {
        super.setName(name);
        this.type = type;
    }
    
    /**
     *
     * @return type of the card
     */
    public CardType getType() {
        return type;
    }
}
