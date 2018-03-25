package com.tripnia.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AlgorithmTest {

    private static List<Vertex> nodes;
    private static List<Edge> edges;

    @Before
    public void initialize() {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
    }

    @Test
    public void Should_Return_Path_Graph_One() {

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


        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        Vertex startVertex = nodes.get(0);
        dijkstra.execute(startVertex);

        Vertex targetVertex = nodes.stream().filter(node -> node.getName().equals("Node_2"))
                .collect(Collectors.toList()).get(0);
        List<String> actualPath = dijkstra.getPath(targetVertex).stream()
                .map(node -> node.getName()).collect(Collectors.toList());

        List<String> expectedPath = Arrays.asList("Node_0", "Node_1", "Node_2");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void Should_Return_Path_Graph_Two() {

        for (int i = 0; i < 7; i++) {
            if (i == 3 || i == 5) {
                List<String> tags = Arrays.asList("Lake");
                Vertex location = new Vertex("Node_" + i, "Node_" + i, tags);
                nodes.add(location);
            } else {
                List<String> tags = Arrays.asList("Temple");
                Vertex location = new Vertex("Node_" + i, "Node_" + i, tags);
                nodes.add(location);
            }
        }

        addLane("Edge_0", 0, 1, 2);
        addLane("Edge_1", 0, 2, 5);
        addLane("Edge_2", 0, 3, 9);
        addLane("Edge_3", 0, 4, 3);
        addLane("Edge_4", 0, 5, 4);
        addLane("Edge_5", 0, 6, 2);
        addLane("Edge_6", 1, 2, 3);
        addLane("Edge_7", 1, 4, 4);
        addLane("Edge_8", 1, 5, 6);
        addLane("Edge_9", 2, 3, 4);
        addLane("Edge_10", 3, 6, 8);
        addLane("Edge_11", 4, 3, 8);
        addLane("Edge_12", 5, 4, 1);
        addLane("Edge_13", 6, 5, 1);

        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        Vertex startVertex = nodes.get(0);
        dijkstra.execute(startVertex);

        Vertex targetVertex = nodes.stream().filter(node -> node.getName().equals("Node_5"))
                .collect(Collectors.toList()).get(0);
        List<String> actualPath = dijkstra.getPath(targetVertex).stream()
                .map(node -> node.getName()).collect(Collectors.toList());

        List<String> expectedPath = Arrays.asList("Node_0", "Node_6", "Node_5");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void Should_Return_Path_Graph_Three() {

        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                List<String> tags = Arrays.asList("Lake");
                Vertex location = new Vertex("Node_" + i, "Node_" + i, tags);
                nodes.add(location);
            } else {
                List<String> tags = Arrays.asList("Temple");
                Vertex location = new Vertex("Node_" + i, "Node_" + i, tags);
                nodes.add(location);
            }
        }

        addLane("Edge_0", 0, 1, 4);
        addLane("Edge_1", 0, 2, 2);
        addLane("Edge_2", 2, 1, 1);

        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        Vertex startVertex = nodes.get(0);
        dijkstra.execute(startVertex);

        Vertex targetVertex = nodes.stream().filter(node -> node.getName().equals("Node_1"))
                .collect(Collectors.toList()).get(0);
        List<String> actualPath = dijkstra.getPath(targetVertex).stream()
                .map(node -> node.getName()).collect(Collectors.toList());

        List<String> expectedPath = Arrays.asList("Node_0", "Node_2", "Node_1");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void Should_Return_Path_Graph_Four() {

        for (int i = 0; i < 4; i++) {
            if (i == 1) {
                List<String> tags = Arrays.asList("Lake");
                Vertex location = new Vertex("Node_" + i, "Node_" + i, tags);
                nodes.add(location);
            } else {
                List<String> tags = Arrays.asList("Temple");
                Vertex location = new Vertex("Node_" + i, "Node_" + i, tags);
                nodes.add(location);
            }
        }

        addLane("Edge_0", 0, 1, 4);
        addLane("Edge_1", 0, 2, 1);
        addLane("Edge_2", 2, 3, 1);
        addLane("Edge_2", 3, 1, 1);

        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        Vertex startVertex = nodes.get(0);
        dijkstra.execute(startVertex);

        Vertex targetVertex = nodes.stream().filter(node -> node.getName().equals("Node_1"))
                .collect(Collectors.toList()).get(0);
        List<String> actualPath = dijkstra.getPath(targetVertex).stream()
                .map(node -> node.getName()).collect(Collectors.toList());

        List<String> expectedPath = Arrays.asList("Node_0", "Node_2", "Node_3", "Node_1");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void Should_Return_Path_Graph_Five() {

        for (int i = 0; i < 4; i++) {
            if (i == 1) {
                List<String> tags = Arrays.asList("Lake");
                Vertex location = new Vertex("Node_" + i, "Node_" + i, tags);
                nodes.add(location);
            } else {
                List<String> tags = Arrays.asList("Temple");
                Vertex location = new Vertex("Node_" + i, "Node_" + i, tags);
                nodes.add(location);
            }
        }

        addLane("Edge_0", 0, 1, 4);
        addLane("Edge_1", 0, 2, 1);
        addLane("Edge_2", 2, 3, 1);
        addLane("Edge_2", 3, 1, 6);

        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        Vertex startVertex = nodes.get(0);
        dijkstra.execute(startVertex);

        Vertex targetVertex = nodes.stream().filter(node -> node.getName().equals("Node_1"))
                .collect(Collectors.toList()).get(0);
        List<String> actualPath = dijkstra.getPath(targetVertex).stream()
                .map(node -> node.getName()).collect(Collectors.toList());

        List<String> expectedPath = Arrays.asList("Node_0", "Node_1");
        assertEquals(expectedPath, actualPath);
    }

    private static void addLane(String laneId, int sourceLocNo, int destLocNo,
                                int duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }
}
