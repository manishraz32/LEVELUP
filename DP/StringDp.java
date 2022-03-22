import java.util.*;

public class StringDp {
    // for print
    public static void print(int[] dp) {
        for (int num : dp) {
            System.out.print(num + " ");
        }
    }

    public static void printTwoD(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Find length of longest Palindromic subsequence--Memo
    //
    public static int Lpss(String s, int i, int j, int[][] dp) {
        if (i >= j) {
            return dp[i][j] = (i == j) ? 1 : 0;
        }

        int maxSs = Integer.MIN_VALUE;
        if (s.charAt(i) == s.charAt(j)) {
            maxSs = Lpss(s, i + 1, j - 1, dp) + 2;
        } else {
            maxSs = Math.max(Lpss(s, i + 1, j, dp), Lpss(s, i, j - 1, dp));
        }

        return dp[i][j] = maxSs;
    }

    // Find length of longest Palindromic subsequence--Tabulation
    public static int LpssDp(String s, int I, int J, int[][] dp) {
        for (int g = 0; g < dp[0].length; g++) {
            for (int i = 0, j = g; j < dp[0].length; i++, j++) {

                if (i >= j) {
                    dp[i][j] = (i == j) ? 1 : 0;
                    continue;
                }

                int maxSs = Integer.MIN_VALUE;
                if (s.charAt(i) == s.charAt(j)) {
                    maxSs = dp[i + 1][j - 1] + 2; // Lpss(s, i + 1, j - 1, dp) + 2;
                } else {
                    maxSs = Math.max(dp[i + 1][j], dp[i][j - 1]); // Math.max(Lpss(s, i + 1, j, dp), Lpss(s, i, j - 1,
                                                                  // dp));
                }

                dp[i][j] = maxSs;

            }
        }
        return dp[I][J];

    }

    public static void longestPalindromeSubseq() {
        String s = "bbbab";
        int n = s.length();
        int dp[][] = new int[n][n];

        for (int[] rowDp : dp) {
            Arrays.fill(rowDp, 0);
        }

        int i = 0;
        int j = n - 1;
        // System.out.println(Lpss(s, i, j, dp));
        System.out.println(LpssDp(s, i, j, dp));
    }

    // find Longest palindromic subsequence
    public static String LpSsDp(String s, int I, int J, String[][] dp) {
        for (int g = 0; g < dp[0].length; g++) {
            for (int i = 0, j = g; j < dp[0].length; i++, j++) {

                if (i >= j) {
                    dp[i][j] = (i == j) ? "" + s.charAt(i) : "";
                    continue;
                }

                String str = "";
                if (s.charAt(i) == s.charAt(j)) {
                    str = s.charAt(i) + dp[i + 1][j - 1] + s.charAt(j); // Lpss(s, i + 1, j - 1, dp) + 2;
                } else {
                    str = (dp[i + 1][j].length() > dp[i][j - 1].length()) ? dp[i + 1][j] : dp[i][j - 1]; // Math.max(Lpss(s,
                                                                                                         // i + 1, j,
                                                                                                         // dp), Lpss(s,
                                                                                                         // i, j - 1,
                                                                                                         // dp));
                }

                dp[i][j] = str;

            }
        }
        return dp[I][J];

    }

    public static void longestPalindromeSubsequence() {
        String s = "bbbab";
        int n = s.length();
        String dp[][] = new String[n][n];

        for (String[] rowDp : dp) {
            Arrays.fill(rowDp, "");
        }

        int i = 0;
        int j = n - 1;
        // System.out.println(Lpss(s, i, j, dp));
        System.out.println(LpSsDp(s, i, j, dp));
    }

    // Longest palidromic substring
    static int maxValue = Integer.MIN_VALUE;
    static String ans = "";

    public static void palindromeSubString(String str, int[][] dp) {

        for (int g = 0; g < dp[0].length; g++) {
            for (int i = 0, j = g; j < dp[0].length; i++, j++) {

                if (g == 0) {
                    dp[i][j] = 1;
                }
                if (g == 1) {
                    dp[i][j] = (str.charAt(i) == str.charAt(j)) ? 2 : 0;
                } else {
                    if (str.charAt(i) == str.charAt(j) && dp[i + 1][i - 1] != 0)
                        dp[i][j] = dp[i + 1][i - 1] + 2;
                    else
                        dp[i][j] = 0;

                }

                if (dp[i][j] > maxValue) {
                    maxValue = dp[i][j];
                    ans = str.substring(i, j + 1);
                }

            }
        }
    }

    public static void longestPalindrome() {
        String str = "cbbd";
        int n = str.length();
        int dp[][] = new int[n][n];
        for (int[] rowDp : dp) {
            Arrays.fill(rowDp, 0);
        }
        palindromeSubString(str, dp);
        printTwoD(dp);
        System.out.println(ans);
        System.out.println(maxValue);
    }

    // longest common palindromic subsequence

    public static int LCSS(String str1, String str2, int n, int m, int[][] dp) {
        if (m == 0 || n == 0) {
            return dp[n][m] = 0;
        }
        if (dp[n][m] != -1)
            return dp[n][m];
        if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
            return dp[n][m] = LCSS(str1, str2, n - 1, m - 1, dp) + 1;
        } else {
            return dp[n][m] = Math.max(LCSS(str1, str2, n - 1, m, dp), LCSS(str1, str2, n, m - 1, dp));
        }
    }

    public static int LCSS_DP(String str1, String str2, int N, int M, int[][] dp) {

        for(int n = 0; n < N; n++) {
            for(int m = 0; m < M; m++) {
                if (m == 0 || n == 0) {
                    dp[n][m] = 0;
                    continue;
                }
                if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
                    dp[n][m] = dp[n-1][m-1]; //LCSS(str1, str2, n - 1, m - 1, dp) + 1;
                } else {
                    dp[n][m] = Math.max(dp[n-1][m], dp[n][m-1]);
                    //Math.max(LCSS(str1, str2, n - 1, m, dp), LCSS(str1, str2, n, m - 1, dp));
                }
            }
        }
        return dp[N][M];
    }

    public static void longestCommonSubsequence() {
        String text1 = "abcde";
        String text2 = "ace";
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] dpRow : dp) {
            Arrays.fill(dpRow, -1);
        }
        System.out.println(LCSS(text1, text2, n, m, dp));
        System.out.println(LCSS_DP(text1, text2, n, m, dp));
    }

    public static void main(String[] args) {
        // longestPalindromeSubseq();
        // longestPalindromeSubsequence();
        // longestPalindrome();
        longestCommonSubsequence();
    }
}
