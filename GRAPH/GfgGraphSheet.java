import java.util.*;
public class GfgGraphSheet {
  //Steps by Knight 
    // Example 1:
    // Input:
    // N=6
    // knightPos[ ] = {4, 5}
    // targetPos[ ] = {1, 1}
    // Output:
    // 3
    // Explanation:

    // Knight takes 3 step to reach from 
    // (4, 5) to (1, 1):
    // (4, 5) -> (5, 3) -> (3, 2) -> (1, 1).
    public class Pair {
        int row;
        int col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public boolean isSafe(int r, int c, boolean[][] vis, int n) {
        if(r >= 1 && c >= 1 && r <= n && c <= n && vis[r-1][c-1] == false) {
            return true;
        }
        return false;
    }
    
    public int minStepToReachTarget(int KnightPos[], int TargetPos[], int N) {
        int sc = KnightPos[0];
        int sr = KnightPos[1];
        
        int dc = TargetPos[0];
        int dr = TargetPos[1];
        
        boolean[][] vis = new boolean[N][N];
         int dir[][]={{-1,-2},{-1,2},{1,-2},{1,2},{-2,-1},{-2,1},{2,-1},{2,1}};
        
        LinkedList<Pair> que = new LinkedList<>();
        que.add(new Pair(sr, sc));
        
        int step = 0;
        
        while(que.size() > 0) {
            int size = que.size();
            
            while(size-- > 0) {
                Pair remPair = que.removeFirst();
                int row = remPair.row;
                int col = remPair.col;
                if(row == dr && col == dc) return step;
                if(vis[row - 1][col -1]) continue;
                vis[row-1][col-1] = true;
                
                for(int d = 0; d < dir.length; d++) {
                    int r = row + dir[d][0];
                    int c = col + dir[d][1];
                    if(isSafe(r, c, vis, N)) {
                        que.add(new Pair(r, c));   
                    }
                }
            }
            step++;
        }
        
        return -1;
    }

    //2. Minimum Swaps to Sort
    // Input:
    // nums = {2, 8, 5, 4}
    // Output:
    // 1
    // Explaination:
    // swap 8 with 4.
    public class Pair implements Comparable<Pair> {
        int val;
        int idx;
        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
        
        public int compareTo(Pair o) {
            return this.val - o.val;
        }
    }
    
    public int minSwaps(int nums[]) {
        int n = nums.length;
        Pair[] arr = new Pair[n];
        
        for(int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);                    
        }
        
        Arrays.sort(arr);
       // for(Pair p : arr) System.out.println(p.val + " " + p.idx);
        boolean[] vis = new boolean[n];
        int ans = 0;
        
        for(int i = 0; i < n; i++) {
            
            if(vis[i] == true || arr[i].idx == i) {
                continue;
            } 
            int cycle = 0;
            int j = i;
             while(vis[j] == false) {
                // System.out.println(arr[i].idx);
                vis[j] = true;
                cycle++;
                j = arr[j].idx;
            }
            ans += (cycle - 1);
            
        }
        return ans;
    }


    //3 -> Euler Path and circuit

    public void dfs( ArrayList<ArrayList<Integer>> adj, int src, boolean[] vis) {
        vis[src] = true;
        for(int nbr : adj.get(src)) {
            if(vis[nbr] == false) {
                dfs(adj, nbr, vis);
            }
        }
    }
    
    public boolean isConnected( ArrayList<ArrayList<Integer>> adj) {
        int v = adj.size();
        boolean[] visited = new boolean[v];
        int i = 0;
        
        // checking if any edge is present
        for(i = 0; i < v; i++) {
            if(adj.get(i).size() != 0) {
                break;
            }
        }
        // i == v means all vertex are seperated
        if(i == v) return true;

        dfs(adj, i, visited);
        
        for(i = 0; i < v; i++) {
            if(visited[i] == false && adj.get(i).size() > 0)
                return false;
        }
        
        return true;
    }
    
    public boolean isEularCircuitExist(int V, ArrayList<ArrayList<Integer>> adj) {

        // if graph is not connected, then
        // Euler Circuit is not possible
        if(isConnected(adj) == false) {
            return false;
        }
        
        int odd = 0;
        
        for(int i = 0; i < V; i++) {
            if(adj.get(i).size() % 2 != 0)  {
                odd++;
            }
        }
        
        return (odd == 0) ? true : false;
    }


    public static void main(String[] args) {

    }
    
}
