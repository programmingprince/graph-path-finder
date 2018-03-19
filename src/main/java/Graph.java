/**
 * Given a directed graph, a source vertex ‘s’ and a destination vertex ‘d’, print all paths from given ‘s’ to ‘d’.
 **/
/* package whatever; // don't place package name! */

/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Graph
{	private int vNumber;
    private ArrayList[] adj;
    int count = 0;

    public Graph(int V){
        this.vNumber = V;
        adj = new ArrayList[V];
        for(int i=0; i<V; i++)
            adj[i] = new ArrayList<Integer>();
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
    }

    public void DFSUtil(int v, Boolean[] visited){
        visited[v] = true;
        System.out.println(v+" ");

        ArrayList<Integer> list = adj[v];
        for(Integer i: list){
            if(!visited[i])
                DFSUtil(i,visited);
        }
    }

    public void DFS(int v){
        Boolean[] visited = new Boolean[vNumber];
        for(int i=0; i<vNumber; i++)
            visited[i] = false;

        DFSUtil(v,visited);
    }

    public void printPaths(int s, int d){
        Boolean[] visited = new Boolean[vNumber];
        int[] path = new int[vNumber];
        int path_index = 0;

        for(int i=0; i<vNumber; i++){
            visited[i] =false;
        }

        printPathsUtil(s,d,visited,path,path_index);
    }

    public int getCount() {
        return this.count;
    }

    public void printPathsUtil(int s, int d, Boolean[] visited, int[] path, int path_index){
        visited[s] = true;
        path[path_index] = s;
        path_index++;

        if(s==d){
//            for(int i=0; i<path_index; i++)
//                System.out.print(path[i] + " ");
//            System.out.println();
            count++;

        }
        else{
            ArrayList<Integer> list = adj[s];
            for(Integer i: list){
                if(!visited[i])
                    printPathsUtil(i,d,visited,path,path_index);
            }
        }

        path_index--;
        visited[s]=false;
    }
    public static void main (String[] args) throws java.lang.Exception
    {
        int vertices = 20;
        Graph g = new Graph(vertices);
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (i == j)
                    continue;
                g.addEdge(i, j);
            }
        }

        int s = 0, d = 19;
        System.out.println("Print all paths from " + s + " to " + d);
        g.printPaths(s,d);
        System.out.println("Total count: " + g.getCount());
    }
}