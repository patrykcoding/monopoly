package monopoly;

public class Dice {
    public static final int DICE_SIDES = 6;

    public int getRoll() {
        return (int)(Math.random() * DICE_SIDES) + 1;
    }
    
    public int[] getDoubleRoll() {
        return new int[] { this.getRoll(), this.getRoll() };
    }
}
