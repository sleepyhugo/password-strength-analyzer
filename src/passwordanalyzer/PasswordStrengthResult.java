package passwordanalyzer;

import java.util.List;

public class PasswordStrengthResult {

    private final int score;
    private final String label;
    private final List<String> feedback;

    public PasswordStrengthResult(int score, String label, List<String> feedback){
        this.score = score;
        this.label = label;
        this.feedback = feedback;
    }

    public int getScore() {
        return score;
    }

    public String getLabel() {
        return label;
    }

    public List<String> getFeedback() {
        return feedback;
    }
}
