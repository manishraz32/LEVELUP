import java.util.*;
public class GraphSet1 {

    // Creating of edge of graph 
    //v-> vertix
    //w-> weight
    public static class Edge {
        int v;
        int w;
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    //method for connecting two vertix 
    // connecting two vertix to each other
    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }
    
    // To display the nodes of graph
    // traverse of the array
    // print element of arraylist which is present on the index of array
    public static void display(ArrayList<Edge>[] graph) {
        int N = graph.length;
        for(int i = 0; i < N; i++) {
            System.out.print(i + "->");
            for(Edge e : graph[i]) {
                System.out.print("(" + e.v + "," + e.w + ")");
            }
            System.out.println();
        }
    }
    // find edge 
    public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {
        ArrayList<Edge> list = graph[u];
        for(int i = 0; i < list.size(); i++) {
            Edge e = list.get(i);
            if(e.v == v) return i;
        }
        return -1;
    }
    // for removing any edge
    // we have to remove 
    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
        int idx = findEdge(graph, u, v);
        graph[u].remove(idx);
        idx = findEdge(graph, v, u);
        graph[v].remove(idx);
    }
    
    //Dfs for the graph
    public static boolean dfs_findPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if(src == dest) 
            return true;
        //mark vtx as visited
        //Because  we can visit each vtx more than once  
        vis[src] = true;
        boolean res = false;
        for(Edge e : graph[src]) {
            if(!vis[e.v]) 
                // whenever result will become true
                //it will not check further 
                res = res || dfs_findPath(graph, e.v, dest, vis);
        }
        return res;
    }

    // print all path
    // for printing all path
    // we also need to unmark when we backtrack from any vertix
    public static int printAllPath(ArrayList<Edge>[] graph, int src, int dest, String psf, int wsf, boolean[] vis ) {
        if(src == dest) {
            System.out.println(psf + dest + "@" + wsf);
            return 1;
        }

        vis[src] = true;
        int count = 0;
        for(Edge e : graph[src]) {
            if(!vis[e.v]) {
                count += printAllPath(graph, e.v, dest, psf + src, wsf + e.w, vis);
            }
        }    
        vis[src] = false;
        return count;

    }

    // program for get connected component
    public static void dfs(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        vis[src] = true;
        for(Edge e : graph[src]) {
            if(!vis[e.v])  {
                dfs(graph, e.v, vis);
            }
        }
    }
    
    public static int getConnectedComp(ArrayList<Edge>[] graph) {
        int n = graph.length;
        boolean[] vis = new boolean[n];
        int count = 0;
        // we have to call from every unvisited component
        // Because in a single dfs we can not traverse whole graph
        // Because we have different component
        for(int i = 0; i < n; i++) {
            if(!vis[i]) {
                dfs(graph, i, vis);
                count++;
            }
        }
        return count;
    }

/**************************************************************************** */
    // Breath first Search in graph with cycle

    public static void BFS_forCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;
        boolean isCycle = false;
        que.add(src);
        
        while(que.size() != 0) {
            int size = que.size();
            System.out.print("Min No Edges" + level + "-> ");
            while(size-- > 0) {
                int removeVtx = que.removeFirst();
                
                //if vis array is already true then
                //it represent cycle in the graph
                // so dont add its children
                // because that is was already added in the queu 
                if(vis[removeVtx]) {
                    isCycle = true;
                    continue;
                }

                vis[removeVtx] = true;

                for(Edge e : graph[removeVtx]) {
                    if(!vis[e.v]) {
                        que.add(e.v);
                    }
                }

            }
            level++;
        }
    }

    //

    // Breath First Search without cycle 
    public static void BfsWithoutCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;
        que.add(src);
        vis[src] = true;
        
        while(que.size() != 0) {
            int size = que.size();
            System.out.print("Min No Edges" + level + "-> ");
            while(size-- > 0) {
                int removeVtx = que.removeFirst();
                 

                // if graph has not cycle then
                // this condition will never come
                // so we can remove this

                // if(vis[removeVtx]) {
                //     continue;
                // }

                for(Edge e : graph[removeVtx]) {
                    if(!vis[e.v]) {
                        que.add(e.v);
                        vis[e.v] = true;
                    }
                }

            }
            level++;
        }
    }
    
    // Is graph bipartitie
    public static boolean isBipartite(int[][] graph, int src,  int[] vis) {
        int m = graph.length;
        int n = graph[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        int color = 0;
        que.add(src);
        // by default bipartite is given as true
        // because if it has not no cycle then it is isBipartite
        boolean isCycle = false, bipartite = true;
        
        while(que.size() > 0) {
            int size = que.size();

            while(size-- > 0) {
                int rvtx = que.removeFirst();

                if(vis[rvtx] != -1) {
                    isCycle = true;
                    if(vis[rvtx] != color) {
                        bipartite = false;
                        break;
                    }
                    continue;
                }

                vis[rvtx] = color;

                for(int v : graph[rvtx]) {
                    if(vis[v] == -1) {
                        que.add(v);
                    }
                }

            }
            color = (color + 1) % 2;
            if(!bipartite) break;
        }


        if(isCycle) {
            if(bipartite) {
                System.out.println("Bipartite with even node in  cycle ");
            } else {
                System.out.println("Not bipartite, odd node in cycle");
            }
        } else {
          System.out.println("graph is bipartite");
        }
        return bipartite;
        }
        
        public static boolean isBipartite(int[][] graph) {
        int v = graph.length;
        int[] vis = new int[v];
        Arrays.fill(vis, - 1);
        boolean res = true;
        for(int i = 0; i < vis.length; i++) {
            if(vis[i] == -1) {
                res = res && isBipartite(graph, i, vis);
            }
        } 

        return res;
    }
    public static ArrayList<Edge>[] ConstructGraph() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 0, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 4, 8);
        return graph;
    }

    public static void graphSet1() {
        ArrayList<Edge>[] graph = ConstructGraph();
        // display(graph);
        // System.out.println(findEdge(graph, 2, 3));
        // removeEdge(graph,2, 3);
        // System.out.println(findEdge(graph, 2, 3));
        int N = graph.length;
        boolean[] vis = new boolean[N]; 
        //System.out.println(dfs_findPath(graph, 0, 6, vis));
        //System.out.println(printAllPath(graph, 0, 6, "", 0, vis));
        System.out.println(getConnectedComp(graph));
    }

    public static void main(String[] args) {
        graphSet1();
    }    
}