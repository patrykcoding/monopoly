package monopoly;

public class JailCard extends Card {
    int type;
    
    public JailCard(int cardType) {
        type = cardType;
    }

    @Override
    public void applyAction(GameMaster master) {
        Player currentPlayer = master.getCurrentPlayer();
        master.sendToJail(currentPlayer);
    }

    @Override
    public int getCardType() {
        return type;
    }

    @Override
    public String toString() {
        return "Go directly to Jail without collecting $200 when passing GO";
    }
}
