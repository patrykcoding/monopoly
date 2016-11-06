package monopoly;

public abstract class Card {

    public static int TYPE_CHANCE = 1;
    public static int TYPE_CC = 2;

    public abstract void applyAction(GameMaster master);
    public abstract int getCardType();
}
