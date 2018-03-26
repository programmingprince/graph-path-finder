package com.tripnia.graph.algorithms;

import com.tripnia.graph.graph_components.Edge;
import com.tripnia.graph.graph_components.Graph;
import com.tripnia.graph.graph_components.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DestinationToSourcePathFinder {
    private final List<Node> nodes;
    private final List<Edge> edges;
    private Set<Node> unVisitedNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Float> distance;

    public DestinationToSourcePathFinder(Graph graph) {
        this.nodes = new ArrayList<Node>(graph.getNodes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

}
