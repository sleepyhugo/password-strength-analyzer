package passwordanalyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordStrengthChecker {

    private static final List<String> COMMON_PASSWORDS = Arrays.asList(
            "password", "123456", "123456789", "qwerty",
            "letmein", "admin", "welcome", "iloveyou"
    );

    public static PasswordStrengthResult evaluate(String password) {
        List<String> feedback = new ArrayList<>();

        if (password == null || password.isBlank()) {
            feedback.add("Password cannot be empty.");
            return new PasswordStrengthResult(0, "Very weak", feedback);
        }

        int score = 0;
        int length = password.length();

        if (length < 6) {
            feedback.add("Make sure your password is at least 8 characters long.");
        } else if (length < 8) {
            score += 10;
            feedback.add("Longer passwords (12+ characters) are more secure.");
        } else if (length < 12) {
            score += 25;
        } else {
            score += 40;
        }

        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSymbol = true;
            }
        }

        if (hasLower) score += 10;
        if (hasUpper) score += 10;
        if (hasDigit) score += 15;
        if (hasSymbol) score += 20;

        if (!hasDigit) feedback.add("Add at least one number.");
        if (!hasSymbol) feedback.add("Add at least one special character.");
        if (!hasUpper) feedback.add("Use uppercase letters to increase complexity.");
        if (!hasLower) feedback.add("Use lowercase letters as part of your mix.");

        // Common password check
        String lower = password.toLowerCase();
        if (COMMON_PASSWORDS.contains(lower)) {
            feedback.add("This is a very common password. Choose something more unique.");
            score = Math.min(score, 20);
        }

        // Repeated characters
        if (hasRepeatedCharacters(password, 3)) {
            feedback.add("Avoid repeating the same character many times.");
            score -= 10;
        }

        // No spaces
        if (password.contains(" ")) {
            feedback.add("Avoid spaces in your password.");
            score -= 5;
        }

        // Clamp score between 0 and 100
        if (score < 0) score = 0;
        if (score > 100) score = 100;

        String label = mapScoreToLabel(score);

        return new PasswordStrengthResult(score, label, feedback);
    }

    private static boolean hasRepeatedCharacters(String password, int repeatCount) {
        int streak = 1;
        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i) == password.charAt(i - 1)) {
                streak++;
                if (streak >= repeatCount) return true;
            } else {
                streak = 1;
            }
        }
        return false;
    }

    private static String mapScoreToLabel(int score) {
        if (score < 30) return "Very Weak";
        if (score < 50) return "Weak";
        if (score < 70) return "Medium";
        if (score < 85) return "Strong";
        return "Very Strong";
    }
}
