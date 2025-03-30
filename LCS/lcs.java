package LCS;

public class lcs {
    public static void main(String[] args) {
        String text1 = "abcdefghi";
        String text2 = "acfh";
        System.out.println("\nLength of LCS: " + longestCommonSubsequence(text1, text2));
    }

    private static int longestCommonSubsequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
}