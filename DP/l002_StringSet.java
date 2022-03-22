import java.util.Arrays;

public class l002_StringSet {

    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] ar : dp) {
            display(ar);
        }
        System.out.println();
    }

    // longest Plaindromic Subsequence
    public static int lpss(String s, int si, int ei, int[][] dp) {
        if (si >= ei) {
            return dp[si][ei] = (si == ei) ? 1 : 0;
        }

        if (dp[si][ei] != 0)
            return dp[si][ei];

        if (s.charAt(si) != s.charAt(ei))
            dp[si][ei] = Math.max(lpss(s, si + 1, ei, dp), lpss(s, si, ei - 1, dp));
        else
            dp[si][ei] = lpss(s, si + 1, ei - 1, dp) + 2;

        return dp[si][ei];

    }

    public static int lpss_DP(String s, int SI, int EI, int[][] dp) {
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (si >= ei) {
                    dp[si][ei] = (si == ei) ? 1 : 0;
                    continue;
                }

                if (s.charAt(si) != s.charAt(ei))
                    dp[si][ei] = Math.max(dp[si + 1][ei], dp[si][ei - 1]);
                else
                    dp[si][ei] = dp[si + 1][ei - 1] + 2;
            }
        }

        return dp[SI][EI];
    }

    public static String lpss_ReverseEngi(String s, int si, int ei, int[][] dp) {
        if (si >= ei) {
            return si == ei ? (s.charAt(si) + "") : "";
        }

        if (s.charAt(si) == s.charAt(ei))
            return s.charAt(si) + lpss_ReverseEngi(s, si + 1, ei - 1, dp) + s.charAt(si);
        else if (dp[si + 1][ei] > dp[si][ei - 1])
            return lpss_ReverseEngi(s, si + 1, ei, dp);
        else
            return lpss_ReverseEngi(s, si, ei - 1, dp);
    }

    public static void longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int ans = lpss_DP(s, 0, n - 1, dp);

        display2D(dp);
        System.out.println(lpss_ReverseEngi(s, 0, n - 1, dp));
    }

    // longest Common Subsequence
    // 1143
    public static int lcss(String str1, int n, String str2, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (str1.charAt(n - 1) == str2.charAt(m - 1))
            return dp[n][m] = lcss(str1, n - 1, str2, m - 1, dp) + 1;
        else
            return dp[n][m] = Math.max(lcss(str1, n, str2, m - 1, dp), lcss(str1, n - 1, str2, m, dp));
    }

    public static int lcss_Dp(String str1, int N, String str2, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (str1.charAt(n - 1) == str2.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                else
                    dp[n][m] = Math.max(dp[n][m - 1], dp[n - 1][m]);
            }
        }

        return dp[N][M];
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        // for (int[] d : dp)
        // Arrays.fill(d, -1);
        int ans = lcss_Dp(text1, n, text2, m, dp);
        display2D(dp);
        return ans;
    }

    // longest Palindromic Substring
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int len = 0, si = 0;

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; j++, i++) {
                if (gap == 0)
                    dp[i][j] = true;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    dp[i][j] = true;
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];

                if (dp[i][j] && j - i + 1 > len) {
                    len = j - i + 1;
                    si = i;
                }
            }
        }

        return s.substring(si, si + len);
    }

    // longest Common Substring

    //1458. Max Dot Product of Two Subsequences
    public int maxDotProduct(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if(n == 0 || m == 0) {
            dp[n][m] = -(int)1e8;
        }
        if(dp[n][m] != -(int)1e9) {
            return dp[n][m];
        }
        int val = nums1[n - 1] * nums2[m - 1];
        int acceptTwoNum = maxDotProduct(nums1, nums2, n - 1, m - 1, dp) + val; // select both number
        int lSelect = maxDotProduct(nums1, nums2, n - 1, m, dp); // select from nums1, not select from nums2
        int rSelect = maxDotProduct(nums1, nums2, n, m - 1, dp); // not select from nums1, select from nums2
        return dp[n][m] = Math.max(val, Math.max(lSelect, Math.max(rSelect, acceptTwoNum)));
    }
    
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        for(int[] arr : dp) {
            Arrays.fill(arr, -(int)1e9);
        }
        return maxDotProduct(nums1, nums2, n, m, dp);
    }
    
    //Max Uncrossed Lines 
    public int  maxUncrossed(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if(n == 0 || m == 0) {
            return dp[n][m] = 0;
        }
        if(dp[n][m] != -1) {
            return dp[n][m];
        }
        if(nums1[n-1] == nums2[m - 1]) {
            return dp[n][m] = maxUncrossed(nums1, nums2, n - 1, m - 1, dp) + 1;
        } else {
            return dp[n][m] = Math.max(maxUncrossed(nums1,nums2,n-1,m,dp), maxUncrossed(nums1, nums2,n, m-1,dp));
        }
    }
    
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n+1][m+1];
        for(int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return maxUncrossed(nums1, nums2, n, m, dp);
    }
    
    //edit distance leetcode-73
    public int minDistance(String word1, String word2, int n, int m, int[][] dp) {
        if(n == 0) dp[n][m] = m;
        if(m == 0) dp[n][m] = n;
        if(dp[n][m] != -1)
            return dp[n][m];
        if(word1.charAt(n-1) == word2.charAt(m-1)) {
            return dp[n][m] = minDistance(word1, word2, n-1, m-1, dp);
        }
        int insert = minDistance(word1, word2, n, m-1, dp);
        int delete = minDistance(word1, word2, n-1, m, dp);
        int remove = minDistance(word1, word2, n-1, m-1, dp);
        return dp[n][m] = Math.min(Math.min(insert,delete), remove) + 1;
    }
    
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int dp[][] = new int[n+1][m+1];
        for(int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return minDistance(word1, word2, n, m, dp);
    }
    //583. Delete Operation for Two Strings
    // Input: word1 = "sea", word2 = "eat"
    // Output: 2
    // Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
    public int minDistanceString(String word1, String word2, int n, int m, int[][] dp) {
        if(n == 0 || m == 0) {
            return dp[n][m] = 0;
        }
        if(dp[n][m] != -1) {
            return dp[n][m];
        }
        if(word1.charAt(n-1) == word2.charAt(m-1)) {
            return dp[n][m] = minDistanceString(word1,word2, n - 1,m - 1, dp) + 1;
        } else {
            return dp[n][m] = Math.max(minDistanceString(word1,word2,n-1,m,dp), minDistanceString(word1,word2, n,m-1,dp));
        }
    }
    
    public int minDistanceString(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for(int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        int len = minDistance(word1, word2, n, m, dp);
        return (n+m) - (len * 2);
    }
    
     // https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1
     public int fun(String s) {
        int emptyCount = 1;
        long aCount = 0, bCount = 0, cCount = 0;
        int mod = (int) 1e9 + 7;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == 'a')
                aCount = aCount + (aCount + emptyCount) % mod;
            else if (ch == 'b')
                bCount = bCount + (bCount + aCount) % mod;
            else if (ch == 'c')
                cCount = cCount + (cCount + bCount) % mod;
        }

        return (int) (cCount % mod);
    }
    //https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1
    long countPS(String str, int i, int j, long[][] dp) {
        if(i >= j) {
            return dp[i][j] = (i == j ? 1 : 0); 
        }

        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        long common = countPS(str, i - 1, j - 1, dp);
        long excludeFirst = countPS(str, i + 1, j, dp);
        long excludeLast = countPS(str, i, j - 1, dp);
        int mod = (int) 1e9 + 7;
        if(str.charAt(i) == str.charAt(j)) {
            dp[i][j] = (excludeFirst + excludeLast + 1) % mod;
        } else {
            dp[i][j] = (excludeFirst + excludeLast - common + mod) % mod;
        }
        return dp[i][j];
    }
    
    long countPS(String str)
    {
        int n = str.length();
        int m = str.length();
        long[][] dp = new long[n+1][m+1];
        for(long[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return countPS(str, 0, n - 1, dp);
    }

    ///leetcode :  // leetcode-> wildcard matching 
    public String removeStars(String str) {
        if (str.length() == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        int i = 1;
        while (i < str.length()) {
            while (i < str.length() && sb.charAt(sb.length() - 1) == '*' && str.charAt(i) == '*')
                i++;

            if (i < str.length())
                sb.append(str.charAt(i));
            i++;
        }

        return sb.toString();
    }
   
    public int isMatch(String s, String p, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 1;
            else if (m == 1 && p.charAt(m - 1) == '*')
                return dp[n][m] = 1;
            else
                return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = s.charAt(n - 1);
        char ch2 = p.charAt(m - 1);

        if (ch1 == ch2 || ch2 == '?')
            return dp[n][m] = isMatch(s, p, n - 1, m - 1, dp);
        else if (ch2 == '*') {
            boolean res = false;
            res = res || isMatch(s, p, n - 1, m, dp) == 1; // star matched with current character
            res = res || isMatch(s, p, n, m - 1, dp) == 1; // star matched with empty string

            return dp[n][m] = res ? 1 : 0;
        } else
            return dp[n][m] = 0;
    }

    public boolean isMatch(String s, String p) {
        p = removeStars(p);
        int n = s.length(), m = p.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return isMatch(s, p, n, m, dp) == 1;
    }


    // Palindromic partitioning leetcode
    public int minCut_memo(String s, int si, boolean[][] isPlaindromeDp, int[] dp) {
        if (isPlaindromeDp[si][s.length() - 1])
            return dp[si] = 0;

        if (dp[si] != -1)
            return dp[si];

        int minAns = (int) 1e8;
        for (int cut = si; cut < s.length(); cut++) {
            if (isPlaindromeDp[si][cut])
                minAns = Math.min(minAns, minCut_memo(s, cut + 1, isPlaindromeDp, dp) + 1);
        }

        return dp[si] = minAns;

    }

    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPlaindromeDp = new boolean[n][n];
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    isPlaindromeDp[i][j] = true;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    isPlaindromeDp[i][j] = true;
                else
                    isPlaindromeDp[i][j] = s.charAt(i) == s.charAt(j) && isPlaindromeDp[i + 1][j - 1];
            }
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return minCut_memo(s, 0, isPlaindromeDp, dp);
    }
    // leetcode 10 -> Regular expression matching
    public String removeStars_(String str) {
        if (str.length() == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        int i = 1;
        while (i < str.length()) {
            while (i < str.length() && sb.charAt(sb.length() - 1) == '*' && str.charAt(i) == '*')
                i++;

            if (i < str.length())
                sb.append(str.charAt(i));
            i++;
        }

        return sb.toString();
    }
    
    public int isMatch_(String s, String p, int n, int m, int[][] dp) {
        if (n == 0 && m == 0)
            return dp[n][m] = 1;
        if (m == 0)
            return dp[n][m] = 0;

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = n > 0 ? s.charAt(n - 1) : '$';
        char ch2 = p.charAt(m - 1);

        if (ch1 != '$' && (ch1 == ch2 || ch2 == '.'))
            return dp[n][m] = isMatch(s, p, n - 1, m - 1, dp);
        else if (ch2 == '*') {
            boolean res = false;
            if (m > 1 && n > 0 && (p.charAt(m - 2) == '.' || p.charAt(m - 2) == s.charAt(n - 1)))
                res = res || isMatch(s, p, n - 1, m, dp) == 1;
            res = res || isMatch(s, p, n, m - 2, dp) == 1;

            return dp[n][m] = res ? 1 : 0;
        } else
            return dp[n][m] = 0;

    }
    public boolean isMatch_(String s, String p) {
         p = removeStars_(p);
        int n = s.length(), m = p.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return isMatch_(s, p, n, m, dp) == 1;
    }

    public static void main(String[] args) {
        longestCommonSubsequence("abcde", "ace");
    }
}