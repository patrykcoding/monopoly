package monopoly.cells;

import monopoly.enums.CardType;
import monopoly.Cell;

public class CardCell extends Cell {
    private final CardType type;
    
    public CardCell(CardType type, String name) {
        super.setName(name);
        this.type = type;
    }
    
    public CardType getType() {
        return type;
    }
}
