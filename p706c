import java.io.*;
import java.util.*;

public class p706c {
    private static int n;
    private static int[] energy;
    private static String[] words;
    private static String[] reversed;
    private static long[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        energy = new int[n];
        words = new String[n];
        reversed = new String[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            energy[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            reversed[i] = new StringBuilder(words[i]).reverse().toString();
        }

        dp = new long[n][2];
        // Initialize dp to an arbitrarily large value
        long errorVal = Long.MAX_VALUE/2;
        for (long[] row : dp) {
            Arrays.fill(row, errorVal);
        }

        // Base case
        dp[n - 1][0] = 0;
        dp[n - 1][1] = energy[n - 1];

        // For every index, for every possible flip of the last two
        for (int i = n - 2; i >= 0; i--) {
            for (int k = 0; k < 2; k++) {
                for (int l = 0; l < 2; l++) {
                    String cur = k > 0 ? reversed[i] : words[i];
                    String next = l > 0 ? reversed[i + 1] : words[i + 1];

                    // cur <= next
                    if (cur.compareTo(next) <= 0) {
                        boolean isFlipped = k > 0;
                        long cost = dp[i + 1][l] + (isFlipped? energy[i] : 0);
                        dp[i][k] = Math.min(dp[i][k], cost);
                    }
                }
            }
        }

        if (dp[0][0] == errorVal)
            System.out.println(dp[0][1] == errorVal ? "-1" : dp[0][1]);
        else if (dp[0][1] == errorVal)
            System.out.println(dp[0][0] == errorVal ? "-1" : dp[0][0]);
        else
            System.out.println(Math.min(dp[0][0], dp[0][1]));
    }
}
