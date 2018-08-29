package dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * greedy的Hierholzer's algorithm
 * 
 * First keep going forward until you get stuck. Put the stuck element always at
 * the front of the result list（stuck element将会在我们最后的eulerian
 * path的尾部，也就是最后走到这里去，因为先走到这里去会stuck）. Try if it is possible to travel to other
 * places from the airport on the way.
 * 
 * JDK1.8的 map.computeIfAbsent(paire[0], x->new ArrayList<String>()).add(paire[1]); 写法运行慢
 * @author Administrator
 *
 */
public class ReconstructItineraryV2 {
	public List<String> findItinerary(String[][] tickets) {
		if (tickets==null || tickets.length==0) return new LinkedList<String>();
		LinkedList<String> itinerary = new LinkedList<String>();
		Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
		for (String[] paire : tickets) {
			if(map.get(paire[0])==null){
				map.put(paire[0], new PriorityQueue<String>());
			}
			map.get(paire[0]).offer(paire[1]);
		}
		dfs(map, itinerary, "JFK");
		return itinerary;
	}

	private void dfs(Map<String, PriorityQueue<String>> map, LinkedList<String> itinerary, String departure) {
		while (map.containsKey(departure) && !map.get(departure).isEmpty()) {
			dfs(map, itinerary, map.get(departure).poll());
		}
		itinerary.addFirst(departure);
	}
}
