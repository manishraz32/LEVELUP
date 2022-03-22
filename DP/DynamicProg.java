import java.util.*;
public class DynamicProg {
   
    //for print
    public static void print(int[] dp) {
        for(int num: dp) {
            System.out.print(num + " ");
        }
    }
    
    public static void printTwoD(int[][] dp) {
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

   // Memoization fo fibonacci series
    public static int fibo_memo(int n, int[] dp) {
        if(n <= 1) {
            return dp[n] = n;
        }
        if(dp[n] != 0) {
            return dp[n];
        }
        int ans = fibo_memo(n - 1, dp) + fibo_memo(n -2, dp);
        return dp[n] = ans;

    }
    // Tabulation of fibonacci series
    public static int fibo_Tab(int N, int[] dp) {

        for(int n = 0; n <= N; n++) {
            if(n <= 1) {
                dp[n] = n;
                continue;
            }
            
            int ans = dp[n - 1] + dp[n - 2];
            dp[n] = ans;
        }
        return dp[N];
    }

    // optimize version of fibonacci series

    public static int fibo_Opti(int n) {
        int a = 0;
        int b = 1;
        for(int i = 1; i <= n; i++) {
            int sum = a + b; 
            a = b;
            b = sum; 
        }
        return a;
    }

    public static void dpSeries() {
        int n = 5;
        int[] dp = new int[6];
        // fibo_memo(n, dp);
        System.out.println(fibo_Opti(n));
        //fibo_Tab(n);
        for(int num : dp) {
            System.out.print(num + " ");
        }
    }
    
    //find the ways to reach destination with dice
    //jump with dice memo
    public static int jumpWithDice(int n, int[] dp) {
        if(n == 0) {
            return dp[n] = 1;
        }
        
        if(dp[n] != 0)
            return dp[n];

        int count = 0;
        for(int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
            count += jumpWithDice(n - dice, dp);
        }
        return dp[n] = count;
    }
    
    //jump with dice Tabulation
    public static int jumpWithDiceTab(int N, int[] dp) {
        for(int n = 0; n <= N; n++) {
            if(n == 0) {
                dp[n] = 1;
                continue;
            }
    
            int count = 0;
            for(int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
                count += dp[n - dice];
            }
            dp[n] = count;
            continue;
        }
        return dp[N];
    }

    // jump with dice optimization

    public static int jumpWithDiceOpti(int n) {
        LinkedList<Integer> dp = new LinkedList<Integer>();
        dp.addLast(1);
        dp.addLast(1);
        for(int i = 2; i <= n; i++) {
            if(dp.size() <= 6) {
                dp.addLast(dp.getLast() * 2);
            } else {
                dp.addLast(dp.getLast() * 2 - dp.removeFirst());
            }
        }
        return dp.getLast();
    }


    public static void jumpPathSeries() {
        int n = 10;
        int[] dp = new int[10 + 1];
        // System.out.println(jumpWithDice(n, dp));
        // System.out.println(jumpWithDiceTab(n, dp));
        System.out.println(jumpWithDiceOpti(n));
        print(dp);
    }
    

    // Maze Path Series
    public static int mazePathMemo(int sr, int sc, int dr, int dc, int[][] dp) {
        if(sr == dr && sc == dc) {
            return dp[sr][sc] = 1;
        }
        
        if(dp[sr][sc] != 0) return dp[sr][sc];
        int count = 0;
        if(sc + 1 <= dc)
            count += mazePathMemo(sr, sc + 1, dr, dc, dp);
        if(sr + 1 <= dr && sc + 1 <= dc)    
            count += mazePathMemo(sr + 1, sc + 1, dr, dc, dp);
        if(sr + 1 <= dr)    
            count += mazePathMemo(sr + 1, sc, dr, dc, dp);

        return dp[sr][sc] = count;
    }

    public static int mazePathTab(int SR, int SC, int dr, int dc, int[][] dp) {
        for(int sr = dr; sr >= 0; sr--) {
            for(int sc = dc; sc >= 0; sc--) {
                if(sr == dr && sc == dc) {
                    dp[sr][sc] = 1;
                    continue;
                }
                
                int count = 0;
                if(sc + 1 <= dc)
                    count += dp[sr][sc + 1];
                if(sr + 1 <= dr && sc + 1 <= dc)    
                    count += dp[sr + 1][sc + 1];
                if(sr + 1 <= dr)    
                    count += dp[sr + 1][sc];
        
                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];
    }

    public static void mazePathSeries() {
        int row = 3;
        int col = 3;
        int[][] dp = new int[row][col];
        System.out.println(mazePathMemo(0, 0, row - 1, col - 1, dp));
        System.out.println(mazePathTab(0, 0, row - 1, col - 1, dp));
        printTwoD(dp);
    }

    
    public static int mazePathJump_HDV(int sr, int sc, int er, int ec, int[][] dir, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];
                if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                    count += mazePathJump_HDV(r, c, er, ec, dir, dp);
                } else
                    break;
            }
        }

        return dp[sr][sc] = count;
    }

    public static int mazePathJump_HDV_DP(int SR, int SC, int er, int ec, int[][] dir, int[][] dp) {

        for (int sr = er; sr >= 0; sr--) {
            for (int sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                        int r = sr + rad * dir[d][0];
                        int c = sc + rad * dir[d][1];
                        if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                            count += dp[r][c];// mazePathJump_HDV(r, c, er, ec, dir, dp);
                        } else
                            break;
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public static void mazePath() {
        int n = 4, m = 5;
        int[][] dp = new int[n][m];
        int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 } };

        // System.out.println(mazePath_HDV(0, 0, n - 1, m - 1, dir, dp));
        // System.out.println(mazePath_HDV_DP(0, 0, n - 1, m - 1, dir, dp));
        System.out.println(mazePathJump_HDV(0, 0, n - 1, m - 1, dir, dp));
        printTwoD(dp);

    }
    
    public static int friendsPairing_memo(int n, int[] dp) {
        if(n <= 1) return dp[n] = 1;

        if(dp[n] != 0) return dp[n];

        int single = friendsPairing_memo(n - 1, dp);
        int pair = friendsPairing_memo(n - 2, dp) * (n - 1);
        
        return dp[n] = single + pair;
    }

    public static int friendsPairing_tab(int N, int[] dp) {
        for(int n = 0; n <= N; n++) {
            if(n <= 1) {
                dp[n] = 1;
                continue;
            }
    
            int single = dp[n -1];
            int pair = dp[n-2] * (n - 1);
            
            dp[n] = single + pair;
        }
        return dp[N];
    }

    public static void friendsPairing() {
        int n = 5;
        int[] dp = new int[n + 1];
        System.out.println(friendsPairing_memo(n, dp));
        System.out.println(friendsPairing_tab(n, dp));
        print(dp);
    }

    public static int goldMine_memo(int sr, int sc, int[][] mine, int[][]dp, int[][] dir) {
        if(sc == mine[0].length - 1) {
            return dp[sr][sc] = mine[sr][sc];
        }
        
        if(dp[sr][sc] != -1) {
            return dp[sr][sc];
        }
        
        int max = Integer.MIN_VALUE;
        for(int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];
            
            if(r >= 0 && c >= 0 && r < mine.length && c < mine[0].length) {
                max = Math.max(max, goldMine_memo(r, c, mine, dp, dir) + mine[sr][sc]);
            }
        }
        return dp[sr][sc] = max;
    }
    static int maxGold(int n, int m, int M[][])
    {
        int[][] dir = {
                        {-1,1},
                        {1,1}, 
                        {0, 1}
                      };
                      
        int[][] dp = new int[n][m];
        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int maxGold = 0;
        for(int r = 0; r < n; r++) {
            maxGold = Math.max(maxGold, goldMine_memo(r, 0, M, dp, dir));
        }
        return maxGold;
    }
    
    public static void main(String[] arg) {

    //     int gold[][]= { {1, 3, 1, 5},
    //                     {2, 2, 4, 1},
    //                     {5, 0, 2, 3},
    //                     {0, 6, 1, 2} };
                         
    //     int m = 4, n = 4;
         
    //     System.out.print(maxGold(m, n,gold));
    // }

    // dpSeries();
    // jumpPathSeries();
    //mazePathSeries();
    friendsPairing();
    }
}