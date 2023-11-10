import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for password criteria
        System.out.print("Enter the desired password length: ");
        int length = scanner.nextInt();

        System.out.print("Include uppercase letters? (y/n): ");
        boolean useUppercase = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include lowercase letters? (y/n): ");
        boolean useLowercase = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include digits? (y/n): ");
        boolean useDigits = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include symbols? (y/n): ");
        boolean useSymbols = scanner.next().equalsIgnoreCase("y");

        // Generate and print the password
        String password = generatePassword(length, useUppercase, useLowercase, useDigits, useSymbols);

        if (password != null) {
            System.out.println("Generated Password: " + password);
        }
    }

    public static String generatePassword(int length, boolean useUppercase, boolean useLowercase, boolean useDigits, boolean useSymbols) {
        // Define character sets based on user-defined criteria
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String digitChars = "0123456789";
        String symbolChars = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";

        // Combine character sets
        StringBuilder allChars = new StringBuilder();
        if (useUppercase) allChars.append(uppercaseChars);
        if (useLowercase) allChars.append(lowercaseChars);
        if (useDigits) allChars.append(digitChars);
        if (useSymbols) allChars.append(symbolChars);

        // Check if at least one character set is selected
        if (allChars.length() == 0) {
            System.out.println("Error: Please select at least one character set.");
            return null;
        }

        // Generate the password
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allChars.length());
            password.append(allChars.charAt(randomIndex));
        }

        return password.toString();
    }
}
