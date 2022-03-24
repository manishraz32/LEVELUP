import java.util.*;

public class GrraphQuest {

    // quest: we have to find number of isLand in the graph
    public static int Islands(char[][] grid) {
        int[][] dir = {{0,-1}, {-1,0}, {0, 1}, {1, 0}};
        int count = 0;
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(!visited[i][j] && grid[i][j] == 1) {
                    dfs(grid, i, j, visited, dir);
                    count++;
                }
            }
        }
        return count;
    }
    public static void dfs(char[][] grid, int sr, int sc, boolean[][] vis, int[][] dir) {
        
        vis[sr][sc] = true;
        for(int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1 && vis[r][c] == false) {
                dfs(grid, r, c, vis, dir);
            }
        }
    }
    // quest: find which area has maximum 1 
    public static int maxAreaOfIsland(char[][] grid) { 
        int[][] dir = {{0,-1}, {-1,0}, {0, 1}, {1, 0}};
        int maxArea = Integer.MIN_VALUE;
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(!visited[i][j] && grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, maxArea_dfs(grid, i, j, visited, dir));
                }
            }
        }
        return maxArea;
    }

    public static int maxArea_dfs(char[][] grid, int sr, int sc, boolean[][] vis, int[][] dir) {
        
        vis[sr][sc] = true;
        int count = 0;
        for(int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1 && vis[r][c] == false) {
                count = count + maxArea_dfs(grid, r, c, vis, dir);
                System.out.println(count);
            }
        }
        return count + 1;
    }

    // 463
    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dir = { { 1, 0 }, { 0, 1 } };

        int onceCount = 0, nbrCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    onceCount++;
                    for (int d = 0; d < 2; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r < n && c < m && grid[r][c] == 1)
                            nbrCount++;
                    }
                }
            }
        }
        return 4 * onceCount - 2 * nbrCount;
    }

    // surrounded_dfs
    //mark the cell from where water can not pass
    public static void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if( (i == 0 || j == 0 || i == (rows - 1) || j == (cols - 1)) && 
                  board[i][j] == 'O') {
                    surrounded_dfs(board, i, j, dir);
                }
            }
        }

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                if(board[i][j] == '#') 
                    board[i][j] = 'O';
                    
            }
        }
    }

    public static void surrounded_dfs(char[][] board, int sr, int sc, int[][] dir) {
        board[sr][sc] = '#';
        
        for(int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r < board.length && c < board[0].length &&  board[r][c] == 'O') {
                surrounded_dfs(board, r, c, dir);
            }
        }
        
    }

    // Count the number of distinct Island 
    static String[] dirS = {"D", "U", "R", "L"};
    static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    static StringBuilder sb;
    static int m, n;
    
    public static void distinctIsland_dfs(int[][] grid, int sr, int sc) {
        grid[sr][sc] = 0;
        for(int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if( r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == 1) {
                sb.append(dirS[d]);
                distinctIsland_dfs(grid, r, c);
                sb.append('B');
            }
        }
    }

    public static int distinctIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    sb = new StringBuilder();
                    distinctIsland_dfs(grid, i, j);
                    System.out.print(sb.toString() + " ");
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
    //leetcode 1905
    // SubIsland
    public static boolean countSubIslands(int[][] grid1, int[][] grid2, int sr, int sc, int[][] dir) {
        grid2[sr][sc] = 0;
        boolean res = true;
        for(int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r < grid2.length && c < grid2[0].length && grid2[r][c] == 1)  {
                res = countSubIslands(grid1, grid2, r, c, dir) && res;
                // it will traverse whole island even after getting false result
            }
        }

        return res && (grid1[sr][sc] == 1 ? true : false);
    }

    public static int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid2.length;
        int m = grid2[0].length;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid2[i][j] == 1) {
                    count = countSubIslands(grid1, grid2, i, j, dir) ? 1 : 0;
                }
            }
        }
        return count;
    }

    public static void IslandsQuest() {
        int[][] grid = {
            {1,1,0,0,0},
            {1,1,0,0,0},
            {0,0,0,1,1},
            {1,0,0,1,1} };
            // System.out.println(Islands(grid));
            // System.out.println(maxAreaOfIsland(grid));
            System.out.println(distinctIsland(grid));
    }
    public static void main(String[] args) {
        IslandsQuest();
    }
}
