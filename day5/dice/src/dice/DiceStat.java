package dice;

public class DiceStat {
    private final int[] diceNumberCount;

    public DiceStat(int diceSize) {
        if (diceSize < 1) {
            throw new IllegalArgumentException("주사위의 면은 1개 이상이어야 합니다.");
        }
        this.diceNumberCount = new int[diceSize];
    }

    public void countDiceNumber(int numberOfDice) {
        diceNumberCount[numberOfDice]++;
    }

    public void printStat() {
        for (int i = 0; i < diceNumberCount.length; i++) {
            System.out.printf("%d은 %d번 나왔습니다.\n", i+1, diceNumberCount[i]);
        }
    }
}
