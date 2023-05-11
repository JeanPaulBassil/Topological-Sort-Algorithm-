package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private ArrayList<Integer> vertices;
    private ArrayList<LinkedList<Integer>> adj;

    public Graph(){
        vertices = new ArrayList<>();
        adj = new ArrayList<>();
    }

    public void addVertex(int c){
        vertices.add(c);
        adj.add(new LinkedList<>());
    }

    public void addEdge(int s, int d){
        adj.get(vertices.indexOf(s)).add(d);
    }

    public int[] topSort(){

//        initialize the values needed for the topSort
//        You will need to keep track of the i, in order to add vertices in a decreasing order.
        int i = vertices.size() - 1;
        int[] ordering = new int[vertices.size()];
        boolean[] isVisited = new boolean[vertices.size()];

//        loop over every vertex in the graph, if it's not visited then call the DFS on it while decrementing i.
        for (int curr = 0; curr < vertices.size(); curr++){
            if (!isVisited[curr]){
                i = topDfs(curr, i, isVisited, ordering);
            }
        }

        return ordering;
    }

//    Same as normal DFS but while adding the current index to the ordering array on the callback.
    public int topDfs(int curr, int i, boolean[] isVisited, int[] ordering){
        isVisited[curr] = true;
        for (int to = 0; to < adj.get(curr).size(); to++){
            int indexOfTo = vertices.indexOf(adj.get(curr).get(to));
            if (!isVisited[indexOfTo]) i = topDfs(indexOfTo, i, isVisited, ordering);
        }
        ordering[i] = curr;
        return i - 1;
    }
}
