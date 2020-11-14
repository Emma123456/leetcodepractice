package homework.graph;

public class DirectedGraphTest {
    public static void main(String[] args){
        DirectedGraph graph = new DirectedGraph(8);
        graph.addEdge(1,2,7);
        graph.addEdge(1,3,9);
        graph.addEdge(2,3,1);
        graph.addEdge(3,4,11);
        graph.addEdge(3,6,2);
        graph.addEdge(4,5,6);
        graph.addEdge(1,6,14);

        graph.dijkstrala(1,5);
        graph.topSortByKahn();
    }
}
