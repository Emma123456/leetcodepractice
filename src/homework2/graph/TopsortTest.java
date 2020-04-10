package homework2.graph;


import java.util.List;

public class TopsortTest {
    public static void main(String[] args){
        Graph graph = new Graph(7);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(3,6);
        graph.addEdge(4,5);
        graph.addEdge(1,6);
        graph.addEdge(6,0);

        List<Integer> path = graph.topSortByKahn();
        System.out.println(path);
        path = graph.topSortByDFS();
        System.out.println(path);
    }
}
