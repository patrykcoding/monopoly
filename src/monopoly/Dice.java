package monopoly;

public class Dice {
    private static final int DICE_SIDES = 6;
    private final int[] dice;
    
    public Dice(int diceAmount) {
        dice = new int[diceAmount];
        roll();
    }
    
    public final void roll() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = (int)(Math.random() * DICE_SIDES) + 1;
        }
    }
    
    public int[] getRoll() {
        roll();
        return dice;
    }
    
    public int getTotal() {
        int total = 0;
        for (int i = 0; i < dice.length; i++) {
            total += dice[i];
        }
        return total;
    }
    
}
