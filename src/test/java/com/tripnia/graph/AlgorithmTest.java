package com.tripnia.graph;

import com.tripnia.graph.graph_components.Edge;
import com.tripnia.graph.graph_components.Graph;
import com.tripnia.graph.graph_components.Node;
import com.tripnia.graph.algorithms.SourceToDestinationPathFinder;
import com.tripnia.graph.graph_components.Path;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class AlgorithmTest {

    private static List<Node> nodes;
    private static List<Edge> edges;

    @Before
    public void initialize() {
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }

    @Test
    public void Should_Return_Path_Graph_One() {

        for (int i = 0; i < 4; i++) {
            if (i == 1 || i == 2) {
                List<String> tags = Arrays.asList("Lake");
                Node location = new Node("Node_" + i, "Node_" + i, tags, 0);
                nodes.add(location);
            } else {
                List<String> tags = Arrays.asList("Temple");
                Node location = new Node("Node_" + i, "Node_" + i, tags, 0);
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
        SourceToDestinationPathFinder pathFinder = new SourceToDestinationPathFinder(graph);
        Node startNode = nodes.get(0);
        pathFinder.execute(startNode);

        Node targetNode = nodes.stream().filter(node -> node.getName().equals("Node_2"))
                .collect(Collectors.toList()).get(0);
        List<String> actualPath = pathFinder.getPath(targetNode).getNodes().stream()
                .map(node -> node.getName()).collect(Collectors.toList());

        List<String> expectedPath = Arrays.asList("Node_0", "Node_1", "Node_2");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void Should_Return_Path_Graph_Two() {

        for (int i = 0; i < 7; i++) {
            if (i == 3 || i == 5) {
                List<String> tags = Arrays.asList("Lake");
                Node location = new Node("Node_" + i, "Node_" + i, tags, 0);
                nodes.add(location);
            } else {
                List<String> tags = Arrays.asList("Temple");
                Node location = new Node("Node_" + i, "Node_" + i, tags, 0);
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
        SourceToDestinationPathFinder pathFinder = new SourceToDestinationPathFinder(graph);
        Node startNode = nodes.get(0);
        pathFinder.execute(startNode);

        Node targetNode = nodes.stream().filter(node -> node.getName().equals("Node_5"))
                .collect(Collectors.toList()).get(0);
        Path path = pathFinder.getPath(targetNode);
        List<String> actualPath = path.getNodes().stream()
                .map(node -> node.getName()).collect(Collectors.toList());

        List<String> expectedPath = Arrays.asList("Node_0", "Node_6", "Node_5");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void Should_Return_Path_Graph_Three() {

        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                List<String> tags = Arrays.asList("Lake");
                Node location = new Node("Node_" + i, "Node_" + i, tags, 0);
                nodes.add(location);
            } else {
                List<String> tags = Arrays.asList("Temple");
                Node location = new Node("Node_" + i, "Node_" + i, tags, 0);
                nodes.add(location);
            }
        }

        addLane("Edge_0", 0, 1, 4);
        addLane("Edge_1", 0, 2, 2);
        addLane("Edge_2", 2, 1, 1);

        Graph graph = new Graph(nodes, edges);
        SourceToDestinationPathFinder pathFinder = new SourceToDestinationPathFinder(graph);
        Node startNode = nodes.get(0);
        pathFinder.execute(startNode);

        Node targetNode = nodes.stream().filter(node -> node.getName().equals("Node_1"))
                .collect(Collectors.toList()).get(0);
        List<String> actualPath = pathFinder.getPath(targetNode).getNodes().stream()
                .map(node -> node.getName()).collect(Collectors.toList());

        List<String> expectedPath = Arrays.asList("Node_0", "Node_2", "Node_1");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void Should_Return_Path_Graph_Four() {

        for (int i = 0; i < 4; i++) {
            if (i == 1) {
                List<String> tags = Arrays.asList("Lake");
                Node location = new Node("Node_" + i, "Node_" + i, tags, 0);
                nodes.add(location);
            } else {
                List<String> tags = Arrays.asList("Temple");
                Node location = new Node("Node_" + i, "Node_" + i, tags, 0);
                nodes.add(location);
            }
        }

        addLane("Edge_0", 0, 1, 4);
        addLane("Edge_1", 0, 2, 1);
        addLane("Edge_2", 2, 3, 1);
        addLane("Edge_2", 3, 1, 1);

        Graph graph = new Graph(nodes, edges);
        SourceToDestinationPathFinder pathFinder = new SourceToDestinationPathFinder(graph);
        Node startNode = nodes.get(0);
        pathFinder.execute(startNode);

        Node targetNode = nodes.stream().filter(node -> node.getName().equals("Node_1"))
                .collect(Collectors.toList()).get(0);
        List<String> actualPath = pathFinder.getPath(targetNode).getNodes().stream()
                .map(node -> node.getName()).collect(Collectors.toList());

        List<String> expectedPath = Arrays.asList("Node_0", "Node_2", "Node_3", "Node_1");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void Should_Return_Path_Graph_Five() {

        for (int i = 0; i < 4; i++) {
            if (i == 1) {
                List<String> tags = Arrays.asList("Lake");
                Node location = new Node("Node_" + i, "Node_" + i, tags, 0);
                nodes.add(location);
            } else {
                List<String> tags = Arrays.asList("Temple");
                Node location = new Node("Node_" + i, "Node_" + i, tags, 0);
                nodes.add(location);
            }
        }

        addLane("Edge_0", 0, 1, 4);
        addLane("Edge_1", 0, 2, 1);
        addLane("Edge_2", 2, 3, 1);
        addLane("Edge_2", 3, 1, 6);

        Graph graph = new Graph(nodes, edges);
        SourceToDestinationPathFinder pathFinder = new SourceToDestinationPathFinder(graph);
        Node startNode = nodes.get(0);
        pathFinder.execute(startNode);

        Node targetNode = nodes.stream().filter(node -> node.getName().equals("Node_1"))
                .collect(Collectors.toList()).get(0);
        List<String> actualPath = pathFinder.getPath(targetNode).getNodes().stream()
                .map(node -> node.getName()).collect(Collectors.toList());

        List<String> expectedPath = Arrays.asList("Node_0", "Node_1");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void Should_Return_Path_Graph_Sixth() {

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            if (i == 1 || i == 6) {
                List<String> tags = Arrays.asList("Lake");
                Node location = new Node("Node_" + i, "Node_" + i, tags, 0);
                nodes.add(location);
            } else {
                List<String> tags = Arrays.asList("Temple");
                Node location = new Node("Node_" + i, "Node_" + i, tags, 0);
                nodes.add(location);
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (i == j)
                    continue;
                addLane("Edge_" + i + j, i, j, random.nextInt(10));
            }
        }

        Graph graph = new Graph(nodes, edges);
        SourceToDestinationPathFinder pathFinder = new SourceToDestinationPathFinder(graph);
        Node startNode = nodes.get(0);
        pathFinder.execute(startNode);

        Node targetNode = nodes.stream().filter(node -> node.getName().equals("Node_1"))
                .collect(Collectors.toList()).get(0);
        List<String> actualPath = pathFinder.getPath(targetNode).getNodes().stream()
                .map(node -> node.getName()).collect(Collectors.toList());

        List<String> expectedPath = Arrays.asList("Node_0", "Node_1");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void Should_Return_Path_Graph_Seventh() {

        Map<String, List<String>> cityMap = new HashMap<>();

        cityMap.put("simara", Arrays.asList("terai"));
        cityMap.put("lukla", Arrays.asList("trekking", "himalayas"));
        cityMap.put("jomsom", Arrays.asList("trekking", "temples"));
        cityMap.put("dhangadhi", Arrays.asList("national park", "remote area"));
        cityMap.put("kathmandu", Arrays.asList("temples", "city", "himalayas"));
        cityMap.put("nepalgunj", Arrays.asList("terai"));
        cityMap.put("jumla", Arrays.asList("trekking", "national park", "lakes"));
        cityMap.put("bhairawa", Arrays.asList("temples", "history", "religious", "terai"));
        cityMap.put("tumlingtar", Arrays.asList());
        cityMap.put("humla", Arrays.asList("trekking", "remote area"));
        cityMap.put("bhadrapur", Arrays.asList("trekking", "national park", "terai"));
        cityMap.put("pokhara", Arrays.asList("lakes"));
        cityMap.put("bharatpur", Arrays.asList("safari", "national park"));
        cityMap.put("janakpur", Arrays.asList("temples", "religious", "terai"));
        cityMap.put("biratnagar", Arrays.asList("industrial area", "terai"));

        Random random = new Random();

        for (Map.Entry entry : cityMap.entrySet()) {
            Node location = new Node((String) entry.getKey(), (String) entry.getKey(), (List) entry.getValue(), 0);
            nodes.add(location);
        }

        addLane2("Edge_0", "kathmandu", "bhadrapur", 182);
        addLane2("Edge_1", "biratnagar", "kathmandu", 148);
        addLane2("Edge_2", "kathmandu", "janakpur", 117);
        addLane2("Edge_3", "nepalgunj", "kathmandu", 150);
        addLane2("Edge_4", "nepalgunj", "humla", 185);
        addLane2("Edge_5", "kathmandu", "tumlingtar", 135);
        addLane2("Edge_6", "kathmandu", "nepalgunj", 150);
        addLane2("Edge_7", "bharatpur", "kathmandu", 107);
        addLane2("Edge_8", "kathmandu", "bharatpur", 107);
        addLane2("Edge_9", "dhangadhi", "kathmandu", 120);
        addLane2("Edge_10", "kathmandu", "bhairawa", 138);
        addLane2("Edge_11", "simara", "kathmandu", 99);
        addLane2("Edge_12", "jumla", "nepalgunj", 125);
        addLane2("Edge_13", "humla", "nepalgunj", 185);
        addLane2("Edge_14", "kathmandu", "lukla", 160);
        addLane2("Edge_15", "nepalgunj", "jumla", 125);
        addLane2("Edge_16", "kathmandu", "biratnagar", 148);
        addLane2("Edge_17", "tumlingtar", "kathmandu", 135);
        addLane2("Edge_18", "kathmandu", "dhangadhi", 120);
        addLane2("Edge_19", "lukla", "kathmandu", 160);
        addLane2("Edge_20", "kathmandu", "simara", 99);
        addLane2("Edge_21", "pokhara", "jomsom", 110);
        addLane2("Edge_22", "janakpur", "kathmandu", 117);
        addLane2("Edge_23", "kathmandu", "pokhara", 110);
        addLane2("Edge_24", "bhadrapur", "kathmandu", 182);
        addLane2("Edge_25", "jomsom", "pokhara", 110);
        addLane2("Edge_26", "pokhara", "kathmandu", 110);
        addLane2("Edge_27", "bhairawa", "kathmandu", 138);
        addLane2("Edge_27", "pokhara", "jumla", 138);

        Graph graph = new Graph(nodes, edges);
        SourceToDestinationPathFinder pathFinder = new SourceToDestinationPathFinder(graph);
        Node startNode = nodes.stream().filter(node ->
                node.getName().equals("kathmandu")).collect(Collectors.toList()).get(0);

        pathFinder.execute(startNode);

        Node targetNode = nodes.stream().filter(node -> node.getName().equals("jumla"))
                .collect(Collectors.toList()).get(0);
        List<String> actualPath = pathFinder.getPath(targetNode).getNodes().stream()
                .map(node -> node.getName()).collect(Collectors.toList());

        List<String> expectedPath = Arrays.asList("kathmandu", "pokhara", "jumla");
        assertEquals(expectedPath, actualPath);
    }

    private static void addLane(String laneId, int sourceLocNo, int destLocNo,
                                int duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }

    private static void addLane2(String laneId, String source, String destination,
                                 int duration) {
        Node from = nodes.stream().filter(node -> node.getName()
                .equals(source)).collect(Collectors.toList()).get(0);
        Node to = nodes.stream().filter(node -> node.getName()
                .equals(destination)).collect(Collectors.toList()).get(0);
        Edge lane = new Edge(laneId, from, to, duration);
        edges.add(lane);
    }
}
