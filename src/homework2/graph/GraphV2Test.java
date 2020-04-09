package homework2.graph;


import java.util.List;

public class GraphV2Test {
    public static void main(String[] args) {
        int v = 14;
        GraphV2 graph = new GraphV2(v);
        graph.addVertex(0,320,600);
        graph.addVertex(1,300,630);
        graph.addVertex(2,280,625);
        graph.addVertex(3,270,630);
        graph.addVertex(4,320,700);
        graph.addVertex(5,360,620);
        graph.addVertex(6,320,590);
        graph.addVertex(7,370,580);
        graph.addVertex(8,350,730);
        graph.addVertex(9,390,690);
        graph.addVertex(10,400,620);
        graph.addVertex(11,420,580);
        graph.addVertex(12,270,670);
        graph.addVertex(13,270,600);

        graph.addEdge(0,1,20);
        graph.addEdge(0,4,60);
        graph.addEdge(0,5,60);
        graph.addEdge(0,6,60);

        graph.addEdge(1,2,20);
        graph.addEdge(2,3,10);
        graph.addEdge(3,12,40);
        graph.addEdge(3,13,30);

        graph.addEdge(12,4,40);
        graph.addEdge(4,8,50);
        graph.addEdge(5,8,70);
        graph.addEdge(5,9,80);
        graph.addEdge(5,10,50);

        graph.addEdge(8,9,50);
        graph.addEdge(9,10,60);
        graph.addEdge(13,6,50);
        graph.addEdge(6,7,70);
        graph.addEdge(7,11,50);


        List<Integer>  path = graph.astar(0,10);
        System.out.println(path);
    }
}
