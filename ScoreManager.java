import java.io.*;
import java.util.*;

public class ScoreManager {
    private static final String SCORE_FILE = "scores.txt";
    private List<ScoreEntry> scores = new ArrayList<>();

    public ScoreManager() {
        loadScores();
    }

    private void loadScores() {
        scores.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    scores.add(new ScoreEntry(name, score));
                }
            }
        } catch (IOException e) {
            // File might not exist yet
        }
    }

    //adds to existing score or creates a new entry
    public void addToScore(String playerName, int amount) {
        boolean found = false;
        for (ScoreEntry entry : scores) {
            if (entry.getName().equals(playerName)) {
                entry.addScore(amount);
                found = true;
                break;
            }
        }

        if (!found) {
            scores.add(new ScoreEntry(playerName, amount));
        }

        sortAndSaveTopScores();
    }

    //Deducts score for healing
    public boolean deductFromScore(String playerName, int amount) {
        for (ScoreEntry entry : scores) {
            if (entry.getName().equals(playerName)) {
                if (entry.getScore() >= amount) {
                    entry.deductScore(amount);
                    sortAndSaveTopScores();
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    //saveScore with this helper
    private void sortAndSaveTopScores() {
        scores.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());
        if (scores.size() > 5) {
            scores = scores.subList(0, 5);
        }
        saveScores();
    }

    private void saveScores() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            for (ScoreEntry entry : scores) {
                bw.write(entry.getName() + "," + entry.getScore() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving scores: " + e.getMessage());
        }
    }

    public void displayTopScores() {
        System.out.println("=== Top 5 Scores ===");
        for (int i = 0; i < scores.size(); i++) {
            ScoreEntry entry = scores.get(i);
            System.out.printf("%d) %s - %d\n", i + 1, entry.getName(), entry.getScore());
        }
        System.out.println();
    }

    public int getScore(String playerName) {
        for (ScoreEntry entry : scores) {
            if (entry.getName().equals(playerName)) {
                return entry.getScore();
            }
        }
        return 0;
    }

    //ScoreEntry to allow updating score
    private static class ScoreEntry {
        private final String name;
        private int score;

        public ScoreEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public void addScore(int amount) {
            this.score += amount;
        }

        public void deductScore(int amount) {
            this.score -= amount;
            if (this.score < 0) this.score = 0;
        }
    }
}
