package passwordanalyzer;

import java.util.List;
import java.util.Scanner;

public class PasswordStrengthApp {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Password Strength Analyzer ===");
        System.out.println("Type a password to analyze its strength.");
        System.out.println("Type 'q' and press Enter to quit");
        System.out.println();

        while (true) {
            System.out.println("Enter a password (or 'q' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")){
                System.out.println("Goodbye!");
                break;
            }

            PasswordStrengthResult result = PasswordStrengthChecker.evaluate(input);

            System.out.println();
            System.out.println("Score: " + result.getScore() + "/100");
            System.out.println("Strength: " + result.getLabel());

            List<String> feedback = result.getFeedback();
            if (feedback.isEmpty()) {
                System.out.println("Feedback: This is a solid password!");
            } else {
                System.out.println("How to improve:");
                for (String message : feedback) {
                    System.out.println(" - " + message);
                }
            }

            System.out.println();
        }

        scanner.close();
    }
}
