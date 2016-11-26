package monopoly;

import java.util.Arrays;

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
        return Arrays.stream(dice).sum();
    }

    public void setDice(int diceNumber, int value) {
        dice[diceNumber] = value;
    }
    
    public int getSingleDice(int diceNumber) {
        return dice[diceNumber];
    }
    
}
