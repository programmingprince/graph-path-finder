package com.tripnia.graph.algorithms;

import com.tripnia.graph.graph_components.Edge;
import com.tripnia.graph.graph_components.Graph;
import com.tripnia.graph.graph_components.Node;
import com.tripnia.graph.graph_components.Path;

import java.util.*;
import java.util.stream.Collectors;

public class SourceToDestinationPathFinder extends PathUtilities {

    private List<Node> nodes;
    private List<Edge> edges;
    private Set<Node> unVisitedNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Float> distance;
    float budget;
    float duration;
    List<String> tags;

    public SourceToDestinationPathFinder(Graph graph) {
        this.nodes = new ArrayList<Node>(graph.getNodes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(Node source, float budget, float duration, List<String> tags) {
        this.budget = budget;
        this.duration = duration;
        this.tags = tags;
        unVisitedNodes = new HashSet<Node>();
        distance = new HashMap<Node, Float>();
        predecessors = new HashMap<Node, Node>();
        distance.put(source, 0f);
        unVisitedNodes = this.nodes.stream().filter(node -> !node.getVisited()).collect(Collectors.toSet());
        Node vertexWithMinimumDistance = source;
        while (unVisitedNodes.size() > 0) {
            vertexWithMinimumDistance.setVisited(true);
            findMinimalDistances(vertexWithMinimumDistance);
            unVisitedNodes = this.nodes.stream().filter(node -> !node.getVisited()).collect(Collectors.toSet());
            vertexWithMinimumDistance = getMinimum(unVisitedNodes);
        }
    }

    private void findMinimalDistances(Node node) {
        List<Node> adjacentNodes = getNeighbors(node);
        for (Node target : adjacentNodes) {
            if ((getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) || (node.getTags().contains("Lake")
                    && target.getTags().contains("Lake") && (getShortestDistance(node)
                    + getDistance(node, target)) < budget)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                target.setVisited(false);
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
        throw new RuntimeException("Unexpected error.");
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

    private Node getMinimum(Set<Node> nodes) {
        Node minimum = null;
        for (Node node : nodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (minimum.getTags().contains("Lake") && node.getTags().contains("Lake") &&
                        getShortestDistance(node) < budget) {
                    minimum = node;
                } else if (
                        getShortestDistance(minimum) > budget &&
                                getShortestDistance(node) < budget) {
                    minimum = node;
                } else if (getShortestDistance(node) < getShortestDistance(minimum)) {
                    minimum = node;
                }
            }
        }

        return minimum;
    }

    private boolean isSettled(Node node) {
        return node.getVisited();
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
