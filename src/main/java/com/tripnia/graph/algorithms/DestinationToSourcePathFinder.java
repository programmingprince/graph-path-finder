package com.tripnia.graph.algorithms;

import com.tripnia.graph.graph_components.Edge;
import com.tripnia.graph.graph_components.Graph;
import com.tripnia.graph.graph_components.Node;
import com.tripnia.graph.graph_components.Path;

import java.util.*;

public class DestinationToSourcePathFinder extends PathUtilities {

    private List<Node> nodes;
    private List<Edge> edges;
    private Set<Node> unVisitedNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Float> distance;

    public DestinationToSourcePathFinder(Graph graph) {
        this.nodes = new ArrayList<Node>(graph.getNodes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(Node source) {
        unVisitedNodes = new HashSet<Node>();
        distance = new HashMap<Node, Float>();
        predecessors = new HashMap<Node, Node>();
        distance.put(source, 0f);
        unVisitedNodes.add(source);
        while (unVisitedNodes.size() > 0) {
            Node node = getMinimum(unVisitedNodes);
            unVisitedNodes.add(node);
            unVisitedNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Node node) {
        List<Node> adjacentNodes = getNeighbors(node);
        for (Node target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unVisitedNodes.add(target);
            }
        }

    }

    private float getDistance(Node node, Node target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getCost();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<Node>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Node getMinimum(Set<Node> vertexes) {
        Node minimum = null;
        for (Node Node : vertexes) {
            if (minimum == null) {
                minimum = Node;
            } else {
                if (getShortestDistance(Node) < getShortestDistance(minimum)) {
                    minimum = Node;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Node Node) {
        return unVisitedNodes.contains(Node);
    }

    private float getShortestDistance(Node destination) {
        Float d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    public Path getPath(Node target) {
        super.setPredecessors(this.predecessors);
        super.setEdges(this.edges);
        return super.getPath(target);
    }
}
