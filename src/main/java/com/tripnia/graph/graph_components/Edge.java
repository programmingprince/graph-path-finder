package com.tripnia.graph.graph_components;

public class Edge {
    private final String id;
    private final Node source;
    private final Node destination;
    private final float cost;

    public Edge(String id, Node source, Node destination, float cost) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public Node getDestination() {
        return destination;
    }

    public Node getSource() {
        return source;
    }

    public float getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}

