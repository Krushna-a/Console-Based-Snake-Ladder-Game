package game;

import java.util.Random;

public class Dice {
    private Random random;
    private int numberOfDice;
    
    public Dice(int numberOfDice) {
        this.random = new Random();
        this.numberOfDice = numberOfDice;
    }
    
    public int roll() {
        int total = 0;
        for (int i = 0; i < numberOfDice; i++) {
            total += random.nextInt(6) + 1;
        }
        return total;
    }
    
    public int getNumberOfDice() {
        return numberOfDice;
    }
}
