//functions on Minimum Spanning Tree (MST) using Prim's and Kruskal's algorithms
import java.util.Scanner;

public class MSTDemo {  

    // A class to represent an edge.
    static class Edge {
        int src, dest, weight;
    }


    // Disjoint Set Union (Union-Find) for Kruskal
    static class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // path compression
            }
            return parent[x];
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return;

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    static class Graph {
        int V;              // number of vertices
        int E;              // number of edges
        Edge[] edges;       // edge list for Kruskal
        int[][] adjMatrix;  // adjacency matrix for Prim
        int edgeCount = 0;
        static final int INF = 1_000_000_000;

        Graph(int V, int E) {
            this.V = V;
            this.E = E;
            edges = new Edge[E];
            for (int i = 0; i < E; i++) {
                edges[i] = new Edge();
            }
            adjMatrix = new int[V][V];
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (i == j) {
                        adjMatrix[i][j] = 0;
                    } else {
                        adjMatrix[i][j] = INF;
                    }
                }
            }
        }

        void addEdge(int u, int v, int w) {
            // store in 0-based internally
            int i = edgeCount;
            edges[i].src = u;
            edges[i].dest = v;
            edges[i].weight = w;
            edgeCount++;

            adjMatrix[u][v] = w;
            adjMatrix[v][u] = w; // undirected
        }

        // Simple selection sort of edges by weight (no Arrays.sort)
        void sortEdgesByWeight() {
            for (int i = 0; i < edgeCount - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < edgeCount; j++) {
                    if (edges[j].weight < edges[minIndex].weight) {
                        minIndex = j;
                    }
                }
                if (minIndex != i) {
                    Edge temp = edges[i];
                    edges[i] = edges[minIndex];
                    edges[minIndex] = temp;
                }
            }
        }

        // Kruskal's MST
        void kruskalMST() {
            System.out.println("\n=== Kruskal's MST ===");
            sortEdgesByWeight();

            UnionFind uf = new UnionFind(V);
            Edge[] result = new Edge[V - 1];
            int mstEdges = 0;
            int mstCost = 0;

            for (int i = 0; i < V - 1; i++) {
                result[i] = new Edge();
            }

            int i = 0; // index for sorted edges

            while (mstEdges < V - 1 && i < edgeCount) {
                Edge e = edges[i++];
                int x = uf.find(e.src);
                int y = uf.find(e.dest);

                if (x != y) {
                    // include edge
                    result[mstEdges].src = e.src;
                    result[mstEdges].dest = e.dest;
                    result[mstEdges].weight = e.weight;
                    mstCost += e.weight;
                    mstEdges++;
                    uf.union(x, y);
                }
            }

            if (mstEdges != V - 1) {
                System.out.println("Graph is not connected, MST not possible.");
                return;
            }

            System.out.println("Edges in MST (u - v : w):");
            for (int j = 0; j < mstEdges; j++) {
                // +1 to make it 1-based for printing
                System.out.println((result[j].src + 1) + " - " +
                                   (result[j].dest + 1) + " : " +
                                   result[j].weight);
            }
            System.out.println("Total MST cost (Kruskal): " + mstCost);
        }

        // Prim's MST using adjacency matrix and O(V^2)
        void primMST() {
            System.out.println("\n=== Prim's MST ===");
            int[] key = new int[V];      // min weight to connect this vertex
            int[] parent = new int[V];   // parent in MST
            boolean[] inMST = new boolean[V];

            for (int i = 0; i < V; i++) {
                key[i] = INF;
                inMST[i] = false;
                parent[i] = -1;
            }

            // start from vertex 0 (you can change this)
            key[0] = 0;
            parent[0] = -1;

            for (int count = 0; count < V - 1; count++) {
                // pick min key vertex not in MST
                int u = -1;
                int minKey = INF;
                for (int v = 0; v < V; v++) {
                    if (!inMST[v] && key[v] < minKey) {
                        minKey = key[v];
                        u = v;
                    }
                }

                if (u == -1) {
                    // graph not connected
                    System.out.println("Graph is not connected, MST not possible.");
                    return;
                }

                inMST[u] = true;

                // update keys of adjacent vertices
                for (int v = 0; v < V; v++) {
                    if (adjMatrix[u][v] != INF && !inMST[v] && adjMatrix[u][v] < key[v]) {
                        key[v] = adjMatrix[u][v];
                        parent[v] = u;
                    }
                }
            }

            int mstCost = 0;
            System.out.println("Edges in MST (u - v : w):");
            for (int v = 1; v < V; v++) { // start from 1, 0 is root
                int u = parent[v];
                int w = adjMatrix[u][v];
                mstCost += w;
                System.out.println((u + 1) + " - " + (v + 1) + " : " + w);
            }

            System.out.println("Total MST cost (Prim): " + mstCost);
        }

        void printAdjMatrix() {
            System.out.println("\nAdjacency Matrix (INF means no direct edge):");
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (adjMatrix[i][j] == INF) {
                        System.out.print("INF ");
                    } else {
                        System.out.print(adjMatrix[i][j] + " ");
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Minimum Spanning Tree using Prim and Kruskal ===");
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        Graph g = new Graph(V, E);

        System.out.println("Enter edges in format: u v w");
        System.out.println("(Vertices are numbered from 1 to " + V + ")");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            // convert to 0-based
            g.addEdge(u - 1, v - 1, w);
        }

        g.printAdjMatrix();

        g.kruskalMST();
        g.primMST();

        sc.close();
    }
}
