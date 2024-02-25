import dice.Dice;
import dice.DiceStat;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int trialsNumber = getTrialsNumber();

        Dice dice = new Dice();
        DiceStat diceStat = new DiceStat(dice.getDiceSize());

        for (int i = 0; i < trialsNumber; i++) {
            int numberOfDice = dice.rollOneDice();

            diceStat.countDiceNumber(numberOfDice);
        }

        diceStat.printStat();
    }

    private static int getTrialsNumber() {
        System.out.println("숫자를 입력하세요 : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}