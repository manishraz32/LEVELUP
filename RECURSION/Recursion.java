import java.util.*;

public class Recursion {

    public static int countFunCall(int num) {
        if (num == 0) {
            return 1;
        }
        return 1 + countFunCall(num - 1);

    }

    // Maze Path ->way up
    public static int printMazePaths(int sr, int sc, int dr, int dc, String psf) {
        if (sr > dr || sc > dc) {
            return 0;
        }

        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        count += printMazePaths(sr, sc + 1, dr, dc, psf + "h");
        count += printMazePaths(sr + 1, sc, dr, dc, psf + "v");
        return count;
    }

    // Maze Paths -> return type
    public static ArrayList<String> mazePath(int sr, int sc, int dr, int dc) {

        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<String>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<String>();

        if (sc + 1 <= dc) {
            ArrayList<String> recAns1 = mazePath(sr, sc + 1, dr, dc);
            for (String str : recAns1) {
                myAns.add("h" + str);
            }
        }

        if (sr + 1 <= dr) {
            ArrayList<String> recAns2 = mazePath(sr + 1, sc, dr, dc);
            for (String str : recAns2) {
                myAns.add("v" + str);
            }
        }

        return myAns;
    }

    // flood fill in all direction
    public static int floodFill(int sr, int sc, int er, int ec, int[][] dir, String[] Sdir, boolean[][] vis,
            String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        vis[sr][sc] = true;
        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            String ch = Sdir[i];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c]) {
                count += floodFill(r, c, er, ec, dir, Sdir, vis, psf + ch);
            }
        }
        vis[sr][sc] = false;
        return count;
    }

    // flood fill Longest Path
    public static class PathPair {
        int len = 0;
        String psf = "";
        int count = 0;

        PathPair(int len, String psf, int count) {
            this.len = len;
            this.psf = psf;
            this.count = count;
        }
    }

    public static PathPair longestPath_floodFill(int sr, int sc, int er, int ec, int[][] dir, String[] Sdir,
            boolean[][] vis, String psf) {
        if (sr == er && sc == ec) {
            return new PathPair(0, "", 1);
        }

        PathPair myAns = new PathPair(0, "", 0);
        vis[sr][sc] = true;
        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            String ch = Sdir[i];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c]) {
                PathPair recAns = longestPath_floodFill(r, c, er, ec, dir, Sdir, vis, psf + ch);
                if (recAns.len + 1 > myAns.len) {
                    myAns.len = recAns.len + 1;
                    myAns.psf = ch + recAns.psf;
                }
                myAns.count = recAns.count + 1;
            }
        }
        vis[sr][sc] = false;
        return myAns;
    }

    // flood fill Shortest Path

    public static PathPair shortestPath_floodFill(int sr, int sc, int er, int ec, int[][] dir, String[] Sdir,
            boolean[][] vis, String psf) {
        if (sr == er && sc == ec) {
            return new PathPair(0, "", 1);
        }

        PathPair myAns = new PathPair((int) 1e8, "", 0);
        vis[sr][sc] = true;
        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            String ch = Sdir[i];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c]) {
                PathPair recAns = shortestPath_floodFill(r, c, er, ec, dir, Sdir, vis, psf + ch);
                if (recAns.len + 1 < myAns.len) {
                    myAns.len = recAns.len + 1;
                    myAns.psf = ch + recAns.psf;
                }
                myAns.count = recAns.count + 1;
            }
        }
        vis[sr][sc] = false;
        return myAns;
    }

    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1#_=_
    //rat in maze path with obstacle

    public static int floodFill(int sr, int sc, int er, int ec, int[][] dir, String[] Sdir, int[][] vis, String psf,
            ArrayList<String> res) {

        if (sr == er && sc == ec) {
            res.add(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec && vis[r][c] == 1) {
                count += floodFill(r, c, er, ec, dir, Sdir, vis, psf + Sdir[d], res);
            }
        }

        vis[sr][sc] = 1;
        return count;
    }

    public static ArrayList<String> findPath(int[][] mat, int n) {
        int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
        String[] Sdir = { "L", "R", "U", "D" };

        // System.out.println(floodFill(0, 0, n - 1, m - 1, dir, Sdir, vis, ""));
        ArrayList<String> res = new ArrayList<>();
        if (mat[0][0] == 0 || mat[n - 1][n - 1] == 0)
            return res;
        int ans = floodFill(0, 0, n - 1, n - 1, dir, Sdir, mat, "", res);

        Collections.sort(res);
        return res;
    }


    // https://practice.geeksforgeeks.org/problems/special-matrix4201/1
    int mod = (int) 1e9 + 7;
    public int FindWays(int n, int m, int[][] blocked_cells)
    {
        n++;
        m++;
        boolean[][] block = new boolean[n][m];
        for(int[] b : blocked_cells)
            block[b[0]][b[1]] = true;
        
        if(block[1][1] || block[n - 1][m-1]) {
            return 0;
        }
        
            
        int[][] dp = new int[n][m];
        for(int[] d : dp)
            Arrays.fill(d, -1);
            
        return dfs(1, 1, n - 1, m - 1, block, dp);    
    }
    
    public int dfs(int sr, int sc, int er, int ec, boolean[][] block, int[][]dp) {
        if(sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }
        if(dp[sr][sc] != -1)
            return dp[sr][sc];
        int count = 0;
        if(sc + 1 <= ec && !block[sr][sc + 1]) {
            count = (count % mod + dfs(sr, sc + 1, er, ec, block, dp) % mod) % mod;
        }
         if(sr + 1 <= er && !block[sr + 1][sc]) {
            count = (count % mod + dfs(sr + 1, sc, er, ec, block, dp) % mod) % mod;
        }
        return dp[sr][sc] = count;
    }

    // https://leetcode.com/problems/path-with-maximum-gold/
    public static void main(String[] args) {
        // System.out.println(countFunCall(5));
        ArrayList<String> ans = mazePath(0, 0, 2, 2);
        System.out.println(ans.size());
        for (String str : ans) {
            System.out.println(str);
        }
        // int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1
        // }, { -1, -1 }, { -1, 1 } };
        // String[] Sdir = { "l", "r", "d", "u", "w", "s", "n", "e" };

        // int n = 4, m = 4;
        // boolean[][] vis = new boolean[n][m];

        // PathPair pair = longestPath_floodFill(0, 0, n - 1, m - 1, dir, Sdir, vis,
        // "");
        // System.out.println(pair.psf + " " + pair.len);
        // PathPair spair = shortestPath_floodFill(0, 0, n - 1, m - 1, dir, Sdir, vis,
        // "");
        // System.out.println(spair.psf + " " + spair.len);
    }
}