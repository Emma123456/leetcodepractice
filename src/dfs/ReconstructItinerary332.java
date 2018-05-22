package dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReconstructItinerary332 {
	/**
	 * 问题1：所有的tickets是需要用完的。有些情况下先使用顺序小的票，不一定能走完。解决方法：dfs函数返回true，如果能走完的情况。
	 * 问题2：超时.解决：逻辑写错了。
	 * @param tickets
	 * @return
	 */
	public List<String> findItinerary(String[][] tickets) {
		Map<String,List<String>> map = new HashMap<String,List<String>>();
        for(String[] paire : tickets){
        	map.computeIfAbsent(paire[0], x->new ArrayList<String>()).add(paire[1]);
        }
        for(String departure : map.keySet()){
            Collections.sort(map.get(departure));
        }
        List<String> itinerary = new ArrayList<String>();
        dfs(map,"JFK",itinerary,tickets.length+1);
        return itinerary;
    }

	private boolean dfs(Map<String, List<String>> map, String departure, List<String> itinerary,int N) {
		itinerary.add(departure);
		if(itinerary.size()==N) return true;
		if(map.get(departure)==null || map.get(departure).isEmpty()){
			return false;
		} 
		//首先尝试顺序在前面的
		int size = map.get(departure).size();
		int count = 0;
		while(count < size){
			String next = map.get(departure).remove(0);
			if(dfs(map,next,itinerary,N)){
				return true;
			}else{
				itinerary.remove(itinerary.size()-1);
				map.get(departure).add(next);
			}
			count++;
		}
		return false;
	}
	
	public static void main(String[] args) {
		//String[][] tickets = new String[][]{new String[]{"JFK","SFO"},new String[]{"JFK","ATL"},new String[]{"SFO","ATL"},new String[]{"ATL","JFK"},new String[]{"ATL","SFO"}};
		String[][] tickets = new String[][]{new String[]{"JFK","KUL"},new String[]{"JFK","NRT"},new String[]{"NRT","JFK"}};
		tickets = new String[][]{new String[]{"EZE","TIA"},new String[]{"EZE","HBA"},new String[]{"AXA","TIA"},new String[]{"JFK","AXA"},new String[]{"ANU","JFK"},new String[]{"ADL","ANU"},new String[]{"TIA","AUA"},new String[]{"ANU","AUA"},new String[]{"ADL","EZE"},new String[]{"ADL","EZE"},new String[]{"EZE","ADL"},new String[]{"AXA","EZE"},new String[]{"AUA","AXA"},new String[]{"JFK","AXA"},new String[]{"AXA","AUA"},new String[]{"AUA","ADL"},new String[]{"ANU","EZE"},new String[]{"TIA","ADL"},new String[]{"EZE","ANU"},new String[]{"AUA","ANU"}};
		List<String> r = new ReconstructItinerary332().findItinerary(tickets);
		System.out.println(r);
	}
}
