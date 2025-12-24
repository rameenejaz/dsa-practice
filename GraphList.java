//graph representation using adjacency list with BFS, DFS, and Dijkstra's algorithm
import java.util.*;

class GraphList {
    private int vertices;
    private LinkedList<Edge>[] adjList;

    class Edge {
        int dest;
        int weight;

        Edge(int d, int w) {
            dest = d;
            weight = w;
        }
    }

    // Constructor
    public GraphList(int v) {
        vertices = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Add edge (undirected)
    public void addEdge(int src, int dest, int weight) {
        adjList[src].add(new Edge(dest, weight));
        adjList[dest].add(new Edge(src, weight)); 
    }

    public void printGraph() {
        System.out.println("\nAdjacency List:");
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + " -> ");
            for (Edge e : adjList[i]) {
                System.out.print("(" + e.dest + ", w=" + e.weight + ") ");
            }
            System.out.println();
        }
    }

    // BFS
    public void BFS(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("\nBFS Traversal: ");

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (Edge e : adjList[node]) {
                if (!visited[e.dest]) {
                    visited[e.dest] = true;
                    queue.add(e.dest);
                }
            }
        }
        System.out.println();
    }

    // DFS
    public void DFS(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.print("\nDFS Traversal: ");
        DFS_Helper(start, visited);
        System.out.println();
    }

    private void DFS_Helper(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (Edge e : adjList[node]) {
            if (!visited[e.dest]) {
                DFS_Helper(e.dest, visited);
            }
        }
    }

    public void dijkstra(int start) {
        int[] dist = new int[vertices];
        boolean[] visited = new boolean[vertices];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            int u = getMinVertex(dist, visited);
            visited[u] = true;

            for (Edge e : adjList[u]) {
                if (!visited[e.dest] &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + e.weight < dist[e.dest]) {

                    dist[e.dest] = dist[u] + e.weight;
                }
            }
        }

        System.out.println("\nDijkstra Shortest Paths from node " + start + ":");
        for (int i = 0; i < vertices; i++) {
            System.out.println(start + " → " + i + " = " + dist[i]);
        }
    }

    private int getMinVertex(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < vertices; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                index = i;
            }
        }
        return index;
    }
}
