import java.util.*;

class Edge {
    int source, destination, weight;

    Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class BellmanFord {

    static void bellmanFord(List<Edge> edges, int vertices, int source) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.source] != Integer.MAX_VALUE && distance[edge.source] + edge.weight < distance[edge.destination]) {
                    distance[edge.destination] = distance[edge.source] + edge.weight;
                }
            }
        }

        for (Edge edge : edges) {
            if (distance[edge.source] != Integer.MAX_VALUE && distance[edge.source] + edge.weight < distance[edge.destination]) {
                System.out.println("Negative cycle detected");
                return;
            }
        }

        System.out.println("Vertex Distance");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " \t " + distance[i]);
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
            new Edge(0, 1, -1),
            new Edge(0, 2, 4),
            new Edge(1, 2, 3),
            new Edge(1, 3, 2),
            new Edge(1, 4, 2),
            new Edge(3, 2, 5),
            new Edge(3, 1, 1),
            new Edge(4, 3, -3)
        );

        int vertices = 5;
        int source = 0;

        bellmanFord(edges, vertices, source);
    }
}
