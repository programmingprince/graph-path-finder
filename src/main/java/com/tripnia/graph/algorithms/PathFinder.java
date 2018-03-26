package com.tripnia.graph.algorithms;

import com.tripnia.graph.graph_components.Graph;
import com.tripnia.graph.graph_components.Node;
import com.tripnia.graph.graph_components.Path;
import com.tripnia.graph.utils.GraphDBService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PathFinder {
    private Graph graph;
    private GraphDBService graphDBService;
    private SourceToDestinationPathFinder sourceToDestinationPathFinder;
    private DestinationToSourcePathFinder destinationToSourcePathFinder;

    public PathFinder(Graph graph) {
        graphDBService = new GraphDBService();
//        this.graph = graphDBService.getGraph();
        this.graph = graph;
    }

    public Map<String, Path> getTrip(String startNodeName, String targetNodeName, float budget, float duration, List<String> tags) {
        sourceToDestinationPathFinder = new SourceToDestinationPathFinder(this.graph);
        destinationToSourcePathFinder = new DestinationToSourcePathFinder(this.graph);

        Node startNode = graph.getNodes().stream().filter(node ->
                node.getName().equals(startNodeName)).collect(Collectors.toList()).get(0);

        Node targetNode = graph.getNodes().stream().filter(node ->
                node.getName().equals(targetNodeName)).collect(Collectors.toList()).get(0);

        sourceToDestinationPathFinder.execute(startNode, budget, duration, tags);
        Path sourceToDestinationPath = sourceToDestinationPathFinder.getPath(targetNode);

        destinationToSourcePathFinder.execute(targetNode);
        Path destinationToSourcePath = destinationToSourcePathFinder.getPath(startNode);

        Map<String, Path> completePath = new HashMap<>();
        completePath.put("sourceToDestination", sourceToDestinationPath);
        completePath.put("destinationToSource", destinationToSourcePath);

        return completePath;
    }
}
