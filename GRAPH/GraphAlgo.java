import java.util.*;
public class GraphAlgo {

    public static class Edge{
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    static int[] par, size;
    public static int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }
    
    public static void union(int p1, int p2) {
        if(size[p1] < size[p2]) {
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    public static void unionFind(int[][] edges, ArrayList<Edge>[] graph, int n) {  // n = no of vertix
        par = new int[n];
        size = new int[n];

        for(int i = 0; i < n; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for(int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u);
            int p2 = findPar(v);

            if(p1 != p2) {
                union(p1, p2);
                addEdge(graph, u, v, w);
            }
        }
    }

    public static void kruskalAlg(int[][] edges, int n) {
        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });

        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        unionFind(edges, graph, n);
    }


    // Ariticlation Point - Algorithm
    static int[] disc,  low; // disc : discovery time, low : lowest time
    static boolean[] articulation, vis;
    static int time = 0, rootCall = 0;
    
    public static void dfs(ArrayList<Edge>[] graph, int src, int par) {
        disc[src] = low[src] = time++;

        for(Edge e : graph[src]) {
            if(!vis[e.v]) {
                if(par == -1)
                    rootCall++;
                dfs(graph, e.v, src);
                
                if(disc[src] <= low[src])
                    articulation[src] = true;
                if(disc[src] < low[src])
                    System.out.println("Edges" + "src" + "e.v");
                low[src] = Math.min(low[src], low[e.v]);        
            } else if(e.v != par) {
                low[src] = Math.min(low[src], disc[e.v]);
            }
        }
    }

    public static void articlationPointAndBridge(ArrayList<Edge>[] graph) {
        int N = graph.length;
        disc = new int[N];
        low = new int[N];
        articulation = new boolean[N];
        vis = new boolean[N];
        
        for(int i = 0; i < N; i++) {
            if(!vis[i])
                dfs(graph, i, -1);
        }
    }


    //Dijkstra Algorithm for shortest path from src to destination
    public static class Pair {
        int vtx, par, w, wsf;
        Pair(int vtx, int par, int w, int wsf) {
            this.vtx = vtx;
            this.par = par;
            this.w = w;
            this.wsf = w;
        }

        Pair(int vtx, int wsf) {
            this.vtx = vtx;
            this.wsf = wsf;
        }
    }

    
    public static void dijkstraSecond(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for(int i = 0; i < N; i++) 
            ngraph[i] = new ArrayList<>();
        boolean[] vis = new boolean[N];
        int[] par = new int[N];
        int[] dis = new int[N]; 
          
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf; // for prims : return a.w - b.w;
        });

        pq.add(new Pair(src, -1, 0, 0));
        while(pq.size() > 0) {
            Pair rp = pq.remove();
            if(vis[rp.vtx])
                continue;
            if(rp.par != -1) {
                addEdge(ngraph, rp.par, rp.vtx, rp.wsf);
            }
            vis[rp.vtx] = true;
            par[rp.vtx] = rp.par;
            dis[rp.vtx] = rp.wsf;

            for(Edge e : graph[rp.vtx]) {
                if(!vis[e.v]) {
                    pq.add(new Pair(e.v, rp.vtx, e.w, e.w + rp.wsf));
                }
            }
        }
    }

    public static void dijkstra(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        int[] par = new int[N];
        int[] dis = new int[N]; 
        Arrays.fill(par, -1);
        Arrays.fill(dis, (int)1e9);
          
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf; // for prims : return a.w - b.w;
        });

        pq.add(new Pair(src,0));
        dis[src] = 0;
        while(pq.size() > 0) {
            Pair rp = pq.remove();
            if(rp.wsf > dis[rp.vtx])
                continue;

            par[rp.vtx] = rp.par;
            dis[rp.vtx] = rp.wsf;

            for(Edge e : graph[rp.vtx]) {
                if(e.w + rp.wsf < dis[e.v]) {
                    disc[e.v] = e.w + rp.wsf;
                    par[e.v] = rp.vtx;
                    pq.add(new Pair(e.v, e.w + rp.wsf));
                }
            }
        }
    }

    // Bellman-ford algorithm
    // Single source shortest path
    // Distance from the Source (Bellman-Ford Algorithm)
    // Negative weight cycle

    public int isNegativeWeightCycle(int n, int[][] edges) {
        int[] dis = new int[n];
        int edge = edges.length;
        Arrays.fill(dis, 100000000);
        dis[0] = 0;
        
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < edge; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                int w = edges[j][2];
                
                if(dis[u] != 100000000 && dis[u] + w < dis[v]) {
                    dis[v] = dis[u] + w;
                }
            }
        }

        // in dis[] array shortest distance will be stored from the src 0 
        
        for(int j = 0; j < edge; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                int w = edges[j][2];
                
                if(dis[u] != 100000000 && dis[u] + w < dis[v]) {
                    return 1; // it means it has negative weight cycle
                }
        }
        
        return 0;
    }


    // Strongly connected component 
    // kosaraju algorithm
     //Function to find number of strongly connected components in the graph.
     public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        Stack<Integer> stk = new Stack<>();
        
        //calling dfs like topo sort
        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                dfs(i, adj, stk, vis);
            }
        }
        
        //transpose the matrix
        ArrayList<ArrayList<Integer>> transpose = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < V; i++) {
            transpose.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < V; i++) {
            for(Integer nbr : adj.get(i)) {
                transpose.get(nbr).add(i);
            }
        }
        
        //dfs according to the finishing time
        Arrays.fill(vis, false);
        int count = 0;
        while(stk.size() > 0 ) {
            Integer vtx = stk.peek();
            stk.pop();
            if(vis[vtx] == false) {
                revDfs(vtx, transpose, vis);
                count++;
            }
        }
        
        return count;
    }
    public void dfs(int src, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stk, boolean[] vis) {
        vis[src] = true;
        for(int nbr : adj.get(src)) {
            if(vis[nbr] == false) {
                dfs(nbr, adj, stk, vis);
            }
        }
        stk.push(src);
    }
    
    public void revDfs(int src, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[src] = true;
        for(int nbr : adj.get(src)) {
            if(vis[nbr] == false) {
                revDfs(nbr, adj, vis);
            }
        }
    }

    
    public static void main(String[] args) {

    }
}
