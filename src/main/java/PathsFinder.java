// JAVA program to print all 
// paths from a source to
// destination.

import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

// A directed graph using
// adjacency list representation
public class PathsFinder {

    // No. of vertices in graph
    private int v;
    // adjacency list
    private ArrayList<Integer>[] adjList;
    int count;
    Map<Integer, List<Deque<Integer>>> globalPathMap;

    //Constructor
    public PathsFinder(int vertices) {

        //initialise vertex count
        this.v = vertices;

        // initialise adjacency list
        initAdjList();
        count = 0;
        globalPathMap = new HashMap<>();
    }

    // utility method to initialise
    // adjacency list
    @SuppressWarnings("unchecked")
    private void initAdjList() {
        adjList = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    // add edge from u to v
    public void addEdge(int u, int v) {
        // Add v to u's list.
        adjList[u].add(v);
    }

    // Prints all paths from
    // 's' to 'd'
    public void printAllPaths(int s, int d) {
        boolean[] isVisited = new boolean[v];
        ArrayDeque<Integer> pathList = new ArrayDeque<>();

        //add source to path[]
        pathList.add(s);

        //Call recursive utility
        printAllPathsUtil(s, d, s, isVisited, pathList);
    }

    // A recursive function to print
    // all paths from 'u' to 'd'.
    // isVisited[] keeps track of
    // vertices in current path.
    // localPathList<> stores actual
    // vertices in the current path
    private void printAllPathsUtil(Integer u, Integer d, Integer parent,
                                   boolean[] isVisited,
                                   Deque<Integer> localPathList) {

        // Mark the current node
        isVisited[u] = true;

        if (u.equals(d)) {
            System.out.println(localPathList);
//            List<Deque<Integer>> pathsListParent = globalPathMap.get(parent);
//            if (pathsListParent == null)
//                pathsListParent = new ArrayList<>();
//            pathsListParent.add(localPathList);
//            globalPathMap.put(parent, pathsListParent);
            count++;
        }

        // Recur for all the vertices
        // adjacent to current vertex
        for (Integer i : adjList[u]) {
            if (!isVisited[i]) {
                // store current node 
                // in path[]
//                List<Deque<Integer>> pathsListI = globalPathMap.get(i);
//                if (pathsListI != null) {
//                    List<Deque<Integer>> pathsListU = globalPathMap.get(u);
//                    if (pathsListU == null)
//                        pathsListU = new ArrayList<>();
//                    List<Deque<Integer>> pathsListTemp = pathsListI.stream().map(list -> {
//                        list.addFirst(u);
//                        return list;
//                    }).collect(Collectors.toList());
//
//                    pathsListU.addAll(pathsListTemp);
//                    globalPathMap.put(u, pathsListU);
//                } else {
                localPathList.add(i);
                printAllPathsUtil(i, d, u, isVisited, localPathList);
//                }

                // remove current node
                // in path[]
                localPathList.remove(i);
            }
        }

        // Mark the current node
        isVisited[u] = false;
    }

    public int getCount() {
        return this.count;
    }

    public List<Deque<Integer>> getGlobalPath(Integer node) {
        return this.globalPathMap.get(node);
    }

    // Driver program
    public static void main(String[] args) {
        // Create a sample graph
        int vertices = 4;
        PathsFinder g = new PathsFinder(vertices);
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (i == j)
                    continue;
                g.addEdge(i, j);
            }
        }

        Instant start = Instant.now();
        g.printAllPaths(0, 1);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Total time taken: " + duration.toMillis());
        System.out.println("Total count: " + g.getCount());
        System.out.println("global path map: " + g.getGlobalPath(0));
    }
}