import java.util.*;
public class DirectedGraph {

    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
    }

    // O(2E)
    public static void display(ArrayList<Edge>[] graph) {
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    // toplogical sort in graph(gfg)
    public static void dfsTopo(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> list) {
        vis[src] = true;
        for(Edge e : graph[src]) {
            if(!vis[e.v])
                dfsTopo(graph, e.v, vis, list);
        }
        list.add(src);
    }

    public static void topologicalSort(ArrayList<Edge>[] graph) {
        int v = graph.length;
        boolean[] vis = new boolean[v];
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < v; i++) {
            if(!vis[i]) {
                dfsTopo(graph, i, vis, list);
            }
        }

        for(int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }

    }

    // khans algorithm to find topological order in acyclic directed graph

    public static ArrayList<Integer> kahnsAlgo(ArrayList<Edge>[] graph) {
        int n = graph.length;
        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int[] inorder = new int[n];

        //count the inorder
        for(int i = 0; i < inorder.length; i++) {
            for(Edge e : graph[i]) {
                inorder[e.v]++;/
            }
        }

        //add in queue when inorder of vertix is zero
        for(int i = 0; i < inorder.length; i++) {
            if(inorder[i] == 0)
                que.addLast(i);
        }

        // process the queue
        while(que.size() > 0) {
            int vrtx = que.removeFirst();
            ans.add(vrtx);

            for(Edge e : graph[vrtx]) {
                if(--inorder[e.v] == 0) {
                    que.addLast(vrtx);
                }
            }
        }

        if(ans.size() != n)
            ans.clear();
        
        return ans;
    }

    //Detect cycle in directed Direct(gfg)

    public boolean isCyclic(ArrayList<ArrayList<Integer>> graph, int src, boolean[] vis, boolean[] path) {
        if(path[src]) return true;
        if(vis[src]) return false;

        vis[src] = true;
        path[src] = true;
        boolean res = false;
        for(int nbr : graph.get(src)) {
            // if(!vis[nbr]) {
            //     res = res || isCyclic(graph, nbr, vis, path);
            // } 
            // No need two write above code 
            // because two vtx is not connected to each other
            res = res || isCyclic(graph, nbr, vis, path);

        }
        path[src] = false;
        return res;
    }
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        boolean[] path = new boolean[V];
        boolean res = false;

        // A single dfs from any source is not enough to visit all vtx
        // we have to call from every vtx because path can be terminate in middle of the graph
        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                res = res || isCyclic(adj, i, vis, path);
            }
        }
        return res;
    }


    public static void constructGraph() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 0, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 4, 8);

        display(graph);
    }


    public static void main(String[] args) {
        constructGraph();
    }
}
