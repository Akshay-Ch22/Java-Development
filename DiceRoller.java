import java.util.Random;

public class DiceRoller {

    public static void main(String[] args) {
        // Simulate rolling a pair of dice
        int die1 = rollDie();
        int die2 = rollDie();

        // Display the results
        System.out.println("Dice Roll Results:");
        System.out.println("Die 1 (Decimal): " + die1);
        System.out.println("Die 2 (Decimal): " + die2);
        System.out.println("Die 1 (Binary): " + decimalToBinary(die1));
        System.out.println("Die 2 (Binary): " + decimalToBinary(die2));
    }

    public static int rollDie() {
        // Simulate rolling a six-sided die
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    public static String decimalToBinary(int decimal) {
        // Convert decimal to binary representation
        return Integer.toBinaryString(decimal);
    }
}
