package monopoly;

public class Dice {
    private static final int DICE_SIDES = 6;
    private final int[] dice;
    
    public Dice(int numDice) {
        dice = new int[numDice];
        roll();
    }
    
    public final void roll() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = (int)(Math.random() * DICE_SIDES) + 1;
        }
    }
    
    public void setDice(int diceNumber, int value) {
        dice[diceNumber] = value;
    }
    
    public int[] getDice() {
        return dice;
    }
    
    public int getSingleDice(int diceNumber) {
        return dice[diceNumber];
    }
    
    public int getTotal() {
        int total = 0;
        for (int i = 0; i < dice.length; i++) {
            total += dice[i];
        }
        return total;
    }
    
}
