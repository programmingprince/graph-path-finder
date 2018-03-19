package graph;

import java.util.*;
import java.util.stream.Collectors;

public class TestDijkstraAlgorithm {

    private static List<Vertex> nodes;
    private static List<Edge> edges;

    public static void main(String[] args) {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < 4; i++) {
            if (i == 1 || i == 2) {
                List<String> tags = Arrays.asList("Lake");
                Vertex location = new Vertex("Node_" + i, "Node_" + i, tags);
                nodes.add(location);
            } else {
                List<String> tags = Arrays.asList("Temple");
                Vertex location = new Vertex("Node_" + i, "Node_" + i, tags);
                nodes.add(location);
            }
        }

        addLane("Edge_0", 0, 1, 1);
        addLane("Edge_1", 0, 3, 2);
        addLane("Edge_1", 0, 2, 5);
        addLane("Edge_2", 1, 2, 5);
        addLane("Edge_2", 1, 3, 14);
        addLane("Edge_3", 2, 3, 3);


        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        Vertex startVertex = nodes.get(0);
        dijkstra.execute(startVertex);
        List<Vertex> interestedVertices = nodes.stream().filter(node -> node.getInterests().contains("Lake")).collect(Collectors.toList());
        for (Vertex v : interestedVertices) {
            LinkedList<Vertex> path = dijkstra.getPath(v);
            System.out.println("\nPrinting shortest path from " + startVertex.getId() + " to " + v.getId());
            for (Vertex vertex : path) {
                System.out.print(vertex + "-->");
            }
        }

    }

    private static void addLane(String laneId, int sourceLocNo, int destLocNo,
                                int duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }
}