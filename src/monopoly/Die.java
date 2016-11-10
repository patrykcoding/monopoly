package monopoly;

public class Die {
    public static final int DICE_SIDES = 6;

    public int getRoll() {
        return (int)(Math.random() * DICE_SIDES) + 1;
    }
}
