package monopoly;

public class CardCell extends Cell {
    private final int type;
    
    public CardCell(int type, String name) {
        setName(name);
        this.type = type;
    }
    
    @Override
    public void playAction() {}
    
    public int getType() {
        return type;
    }
}
