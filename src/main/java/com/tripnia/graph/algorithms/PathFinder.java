package com.tripnia.graph.algorithms;

import com.tripnia.graph.graph_components.Graph;
import com.tripnia.graph.graph_components.Node;
import com.tripnia.graph.utils.GraphDBService;
import sun.security.krb5.internal.crypto.Des;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PathFinder {
    private Graph graph;
    private GraphDBService graphDBService;
    private SourceToDestinationPathFinder sourceToDestinationPathFinder;
    private DestinationToSourcePathFinder destinationToSourcePathFinder;

    public PathFinder() {
        graphDBService = new GraphDBService();
        this.graph = graphDBService.getGraph();
    }

    public List<Node> getTrip(String startNodeName, float budget, float duration, List<String> tags) {
        sourceToDestinationPathFinder = new SourceToDestinationPathFinder(this.graph);
        destinationToSourcePathFinder = new DestinationToSourcePathFinder(this.graph);

        Node startNode = graph.getNodes().stream().filter(node ->
                node.getName().equals(startNodeName)).collect(Collectors.toList()).get(0);



        return new ArrayList<>();
    }

}
