package passwordanalyzer;

import java.util.List;
import java.util.Scanner;

public class PasswordStrengthApp {

    // ANSI color codes for console
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_BOLD = "\u001B[1m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Colored title
        System.out.println(ANSI_CYAN + ANSI_BOLD + "=== Password Strength Analyzer ===" + ANSI_RESET);

        while (true) {
            System.out.print("Enter a password (or 'q' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Goodbye!");
                break;
            }

            PasswordStrengthResult result = PasswordStrengthChecker.evaluate(input);

            System.out.println();
            System.out.println("Score: " + result.getScore() + "/100");

            String strengthLabel = result.getLabel();
            String color = getColorForLabel(strengthLabel);

            System.out.println("Strength: " + color + strengthLabel + ANSI_RESET);

            List<String> feedback = result.getFeedback();
            if (feedback.isEmpty()) {
                System.out.println(ANSI_GREEN + "Feedback: This is a solid password!" + ANSI_RESET);
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

    private static String getColorForLabel(String label) {
        if (label.equalsIgnoreCase("Very Weak") || label.equalsIgnoreCase("Weak")) {
            return ANSI_RED;
        } else if (label.equalsIgnoreCase("Medium")) {
            return ANSI_YELLOW;
        } else if (label.equalsIgnoreCase("Strong") || label.equalsIgnoreCase("Very Strong")) {
            return ANSI_GREEN;
        }
        return ANSI_RESET;
    }
}
