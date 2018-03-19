import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class DFS {
    public void performDFS(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.add(node);
        node.setVisited(true);

        while(!stack.isEmpty()) {
            Node element = stack.pop();
            System.out.print(element.getData() + "\t");
            List<Node> neighbours = element.getNeighbours();

            for(int i=0;i<neighbours.size(); i++) {
                Node neighbour = neighbours.get(i);
                if(neighbour != null && !neighbour.isVisited()) {
                    stack.add(neighbour);
                    neighbour.setVisited(true);
                }
            }
        }
    }

    public static void main(String[] args) {

        DFS dfs = new DFS();

        int totalNumberOfNodes = 5;
        List<Node> nodes = new ArrayList<>();
        for (int i = 1; i <= totalNumberOfNodes; i++) {
            Node node = new Node(i);
            nodes.add(node);
        }

        for (int i = 1; i <= totalNumberOfNodes; i++) {
            Node node1 = nodes.get(i-1);
            for (int j = 1; j <= totalNumberOfNodes; j++) {
                if (i == j)
                    continue;
                Node node2 = nodes.get(j-1);
                node1.addNeighbour(node2);
            }
        }

        dfs.performDFS(nodes.get(0));
//        DFS dfs = new DFS();
//
//        Node node1 = new Node(1);
//        Node node2 = new Node(2);
//        Node node3 = new Node(3);
//        Node node4 = new Node(4);
//        Node node5 = new Node(5);
//        Node node6 = new Node(6);
//        Node node7 = new Node(7);
//        Node node8 = new Node(8);
//
//        node1.addNeighbour(node2);
//        node1.addNeighbour(node3);
//
//        node2.addNeighbour(node4);
//        node2.addNeighbour(node5);
//
//        node3.addNeighbour(node6);
//        node6.addNeighbour(node7);
//
//        node4.addNeighbour(node8);
//
//        dfs.performDFS(node1);
    }
}
