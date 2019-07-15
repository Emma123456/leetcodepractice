package book.graph;

public class UnDirectedGraphTest {
    public static void main(String[] args){
        UnDirectedGraph ugraph = new UnDirectedGraph(8);
        ugraph.addEdge(0,1);
        ugraph.addEdge(0,3);
        ugraph.addEdge(1,2);
        ugraph.addEdge(1,4);
        ugraph.addEdge(3,4);
        ugraph.addEdge(2,5);
        ugraph.addEdge(4,5);
        ugraph.addEdge(4,6);
        ugraph.addEdge(5,7);
        ugraph.addEdge(6,7);

        ugraph.bfs(0,7);
    }
}
