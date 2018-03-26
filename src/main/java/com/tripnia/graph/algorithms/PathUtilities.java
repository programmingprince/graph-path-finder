package com.tripnia.graph.algorithms;

import com.tripnia.graph.graph_components.Edge;
import com.tripnia.graph.graph_components.Node;
import com.tripnia.graph.graph_components.Path;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PathUtilities {

    private Map<Node, Node> predecessors;
    private List<Edge> edges;

    public Map<Node, Node> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(Map<Node, Node> predecessors) {
        this.predecessors = predecessors;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public Path getPath(Node target) {
        Path path = new Path();
        LinkedList<Node> nodesInPath = new LinkedList<>();
        LinkedList<Edge> edgesInPath = new LinkedList<>();
        Node step = target;
        if (predecessors.get(step) != null) {
            nodesInPath.add(step);
            while (predecessors.get(step) != null) {
                step = predecessors.get(step);
                nodesInPath.add(step);
            }
            Collections.reverse(nodesInPath);
        }
        path.setNodes(nodesInPath);

        for (int i = 0; i < nodesInPath.size() - 1; i++) {
            for (Edge edge : this.edges) {
                if (edge.getSource().getName().equals(nodesInPath.get(i).getName())
                        && edge.getDestination().getName().equals(nodesInPath.get(i + 1).getName())) {
                    edgesInPath.add(edge);
                }
            }
        }
        path.setEdges(edgesInPath);
        return path;
    }
}
