package dice;

public class Dice {
    private static final int DICE_SIZE = 6;

    public int rollOneDice() {
        return (int) Math.floor(Math.random() * DICE_SIZE);
    }

    public int getDiceSize() {
        return DICE_SIZE;
    }
}
