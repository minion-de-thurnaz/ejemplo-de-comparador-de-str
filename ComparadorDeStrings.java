import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComparadorDeStrings {
    public static void main(String[] args) {
        String[] headers = {"Título del libro", "Subtitulo", "Serie"};
        List<String[]> data = new ArrayList<>();
        data.add(new String[] {"Juancho", "Juanita", "0"});

        for (String[] row : data) {
            String title = row[0].toLowerCase();     // Convertir a minúsculas
            String subtitle = row[1].toLowerCase();  // Convertir a minúsculas
            double similarity = similarityPercentage(title, subtitle);
            row[2] = String.valueOf(similarity);
        }

        System.out.println(Arrays.toString(headers));
        for (String[] row : data) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("Archivo modificado con éxito");
    }

    public static double similarityPercentage(String a, String b) {
        int distance = levenshteinDistance(a, b);
        int maxLen = Math.max(a.length(), b.length());
        return (1 - (double) distance / maxLen) * 100;
    }

    public static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                    );
                }
            }
        }
        return dp[a.length()][b.length()];
    }
}
