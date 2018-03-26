package com.tripnia.graph.graph_components;

import java.util.List;

public class Path {
    private List<Node> nodes;
    private List<Edge> edges;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
}
