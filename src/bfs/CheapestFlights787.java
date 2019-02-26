package bfs;

import java.util.*;

public class CheapestFlights787 {
    //https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/229422/Java-BFS-5ms-BUT-my-DFS-100ms
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //转graph
        Map<Integer,City> graph = new HashMap<Integer,City>();
        for(int i=1;i<=n;i++){
            graph.put(i,new City());
        }
        for(int i =0;i<flights.length;i++){
            int s = flights[i][0];
            graph.get(s).flightList.add(new Flight(flights[i][1],flights[i][2]));
        }

        Queue<City> queue = new LinkedList<City>();
        graph.get(src).cost = 0;
        queue.offer(graph.get(src));

        while(!queue.isEmpty()){
            int size = queue.size();
            int[] costs = new int[n];
            Arrays.fill(costs,-1);

            for(int i=0;i<size;i++){
                City city = queue.poll();
                for(Flight flight : city.flightList){
                    City toCity = graph.get(flight.dest);
                    int newCost = city.cost+flight.cost;
                    if(toCity.cost > newCost){
                        //考虑到可能多个节点同时达到节点flight.dest
                        costs[flight.dest]  = costs[flight.dest]==-1?newCost:Math.min(costs[flight.dest],newCost);
                        if(K>0){
                            queue.offer(toCity);
                        }

                    }

                }
            }
            for(int i=0;i<n;i++){
                if(costs[i]!=-1){
                    graph.get(i).cost = costs[i];
                }
            }
            K--;
        }
        return graph.get(dst).cost == Integer.MAX_VALUE?-1:graph.get(dst).cost;
    }

    class Flight{
        int dest;
        int cost;
        Flight(int city, int cost) {
            this.dest = city;
            this.cost = cost;
        }
    }

    class City{
        int cost = Integer.MAX_VALUE;
        List<Flight> flightList = new ArrayList<Flight>();

    }
}
