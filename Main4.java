import java.util.*;
public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        GraphList g = new GraphList(v);

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        System.out.println("Enter each edge in format: src dest weight");
        for (int i = 0; i < e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            g.addEdge(src, dest, weight);
        }

        g.printGraph();

        System.out.print("Enter starting node for BFS/DFS/Dijkstra: ");
        int start = sc.nextInt();

        g.BFS(start);
        g.DFS(start);
        g.dijkstra(start);

        sc.close();
    }
}