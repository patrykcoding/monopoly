package monopoly;

import java.util.Arrays;

public class Dice {
    private static final int DICE_SIDES = 6;
    private final int[] dice;
    
    public Dice(int diceAmount) {
        dice = new int[diceAmount];
        roll();
    }

    public int[] getRoll() {
        roll();
        return dice;
    }
    
    public int getSingleDice(int diceNumber) {
        return dice[diceNumber];
    }
    
    public int getTotal() {
        return Arrays.stream(dice).sum();
    }
    
    public final void roll() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = (int)(Math.random() * DICE_SIDES) + 1;
        }
    }

    public void setDice(int diceNumber, int value) {
        dice[diceNumber] = value;
    }
    
    
}
