package monopoly;

public class CardCell extends Cell {
    private final int type;
    
    public CardCell(int type, String name) {
        super.setName(name);
        this.type = type;
    }
    
    public int getType() {
        return type;
    }
}
