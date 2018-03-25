package com.tripnia.graph;

import java.util.*;
import java.util.stream.Collectors;

public class DijkstraAlgorithm {

    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> unVisitedNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;
    int budget = 7;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(Vertex source) {
        unVisitedNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(source, 0);
        System.out.println("vertices: " + nodes);
        int i = 0;
        unVisitedNodes = this.nodes.stream().filter(node -> !node.getVisited()).collect(Collectors.toSet());
        while (unVisitedNodes.size() > 0) {
            Vertex vertexWithMinimumDistance;
            if (i == 0) {
                vertexWithMinimumDistance = source;
            } else {
                vertexWithMinimumDistance = getMinimum(unVisitedNodes);
            }
            vertexWithMinimumDistance.setVisited(true);
            findMinimalDistances(vertexWithMinimumDistance);
            unVisitedNodes = this.nodes.stream().filter(node -> !node.getVisited()).collect(Collectors.toSet());
            i++;
        }
    }

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        System.out.println("neighbours of " + node + ": " + adjacentNodes);
        for (Vertex target : adjacentNodes) {
            if ((getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) || (node.getInterests().contains("Lake")
                    && target.getInterests().contains("Lake") && (getShortestDistance(node)
                    + getDistance(node, target)) < budget)) {
                System.out.println("Previous distance: " + target);
                System.out.println(getShortestDistance(node)
                        + getDistance(node, target));
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                target.setVisited(false);
            }
        }

    }

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        System.out.println("vertices: " + vertexes);
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            System.out.println("working for vertex: " + vertex);
            System.out.println("old minimum: " + minimum);
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (minimum.getInterests().contains("Lake") && vertex.getInterests().contains("Lake") &&
                        getShortestDistance(vertex) < budget) {
                    minimum = vertex;
                    System.out.println("inside if");
                } else if (
                        getShortestDistance(minimum) > budget &&
                                getShortestDistance(vertex) < budget) {
                    minimum = vertex;
                    System.out.println("inside 1 else if");
                } else if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                    System.out.println("inside 2 else if");
                }
            }
            System.out.println("new minimum: " + minimum);
        }
        System.out.println("returning minimum: " + minimum);
//
//        for (Vertex vertex : vertexes) {
//            System.out.println("vertex: " + vertex);
//            System.out.println("old minimum: " + minimum);
//            if (minimum == null) {
//                minimum = vertex;
//                System.out.println("inside if");
//
//            } else {
//                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
//                    minimum = vertex;
//                    System.out.println("inside else ");
//                }
//            }
//            System.out.println("new minimum: " + minimum);
//
//        }
//
//        System.out.println("returning minimum: " + minimum);

        return minimum;
    }

    private boolean isSettled(Vertex vertex) {
        return vertex.getVisited();
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        System.out.println("predecessors: " + predecessors);
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
