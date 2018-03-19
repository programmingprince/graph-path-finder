import java.util.ArrayList;
import java.util.List;

public class Node {
    private int data;
    private boolean visited;
    private List<Node> neighbours;

    public Node(int data) {
        this.data = data;
        this.visited = false;
        neighbours = new ArrayList<>();
    }

    public int getData() {
        return data;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Node node) {
        this.neighbours.add(node);
    }
}
