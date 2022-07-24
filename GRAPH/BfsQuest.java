import java.util.*;
public class BfsQuest {
    // Rotted orange leetcode
    // in this question we have to find toatal time to rotted all orange
    public int orangesRotting(int[][] grid) {
        int[][] dir = {{0,-1}, {-1,0}, {0, 1}, {1, 0}};
        int rows = grid.length;
        int cols = grid[0].length;
        LinkedList<Integer> que = new LinkedList<Integer>();
        int orange = 0;

        //count number of fresh orange
        //add rotted orange in queue
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == 1)
                    orange++;
                if(grid[i][j] == 2) {
                    grid[i][j] = 2;
                    que.add(cols * i + j);
                }    
            }
        }
        int time = 0;
        if(orange == 0)
            return time;
        while(que.size() > 0) {
            int size = que.size();
            while(size-- > 0) {
                int rvtx = que.removeFirst();
                int sr = rvtx / cols;
                int sc = rvtx % cols;
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r < rows && c < cols && grid[r][c] == 1) {
                        // when fresh orange become zero
                        //it means all orange is rotted
                        //add 1 in time 
                        if(--orange == 0)
                            return time + 1;
                        grid[r][c] = 2;
                        que.addLast(cols * r + c);    

                    }
                }
            }
            time++;
        }    
        return -1;
    }


    // shortest path in binary matrix
    // we have to start from 00 index
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] dir = {{0,-1}, {-1,0}, {0, 1}, {1, 0}};
        int rows = grid.length;
        int cols = grid[0].length;
        if(grid[0][0] == 1 || grid[rows-1][cols-1] == 1) {
            return -1;
        }
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.add(0);
        int time = 1;
       
        while(que.size() > 0) {
            int size = que.size();
            while(size-- > 0) {
                int rvtx = que.removeFirst();
                int sr = rvtx / cols;
                int sc = rvtx % cols;
                // we are returning answer when we remove the idx
                // so we will return the time
                if(sr == rows - 1 && sc == cols - 1)
                    return time;
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r < rows && c < cols && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        que.addLast(cols * r + c);    
                    }
                }
            }
            time++;
        }    
        return -1;
    }

    //Update matrix 
    public int[][] updateMatrix(int[][] grid) {
        int[][] dir = {{0,-1}, {-1,0}, {0, 1}, {1, 0}};
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] newMat = new int[rows][cols];
        boolean[][] vis = new boolean[rows][cols];
        LinkedList<Integer> que = new LinkedList<Integer>();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == 0) {
                    que.add(i * cols + j);
                    vis[i][j] = true;
                    newMat[i][j] = 0;
                }
            }
        }
       
        while(que.size() > 0) {
            int size = que.size();
            while(size-- > 0) {
                int rvtx = que.removeFirst();
                int sr = rvtx / cols;
                int sc = rvtx % cols;
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r < rows && c < cols && grid[r][c] == 1 && !vis[r][c]) {
                        vis[r][c] = true;
                        newMat[r][c] = newMat[sr][sc] + 1;
                        que.addLast(cols * r + c);    

                    }
                }
            }
        }    
        return newMat;
    }
     
    //longest increasing path leetcode: 329
    //https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dir = {{0,-1}, {-1,0}, {0, 1}, {1, 0}};
        LinkedList<Integer> que = new LinkedList<Integer>();
        int[][] inorder = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int d = 0; d < dir.length; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    if(r >= 0 && c >= 0 && r < n && c < m && matrix[i][j] > matrix[r][c]) {
                        inorder[i][j]++;
                    }
                }
                if(inorder[i][j] == 0) que.addLast(i * m + j);
            }
        }
        int level = 0;
        while(que.size() > 0) {
            int size = que.size();
            while(size-- != 0) {
                int idx = que.removeFirst();
                int i = idx / m;
                int j = idx % m;
                for(int d = 0; d < dir.length; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    if(r >= 0 && c >= 0 && r < n && c < m && matrix[i][j] < matrix[r][c] && --inorder[r][c] == 0) {
                        que.add(r * m + c);
                    }
                }
            }
            level++;
        }

        return level;
    }


    public static void main(String[] args) {
        
    }
}
