package monopoly;

import java.util.Arrays;

/**
 *
 * @author owner
 */
public class Dice {

    private static final int DICE_SIDES = 6; //Dice have 6 sides

    private final int[] dice; //Array of Dice amount.
    
    /**
     *
     * @param diceAmount
     */
    public Dice(int diceAmount) {
        dice = new int[diceAmount];
        roll();
    }

    /**
     *Roll dice will get
     * @return dice stop
     */
    public int[] getRoll() {
        roll();
        return dice;
    }
    
    /**
     *
     * @param diceNumber
     * @return output number for dice
     */
    public int getSingleDice(int diceNumber) {
        return dice[diceNumber];
    }
    
    /**
     *
     * @return total of dice in array.
     */
    public int getTotal() {
        return Arrays.stream(dice).sum();
    }
    
    /**
     * Get random number for dice
     */
    public final void roll() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = (int)(Math.random() * DICE_SIDES) + 1;
        }
    }

    /**
     *
     * @param diceNumber
     * @param value
     */
    public void setDice(int diceNumber, int value) {
        dice[diceNumber] = value;
    }
    
    
}
