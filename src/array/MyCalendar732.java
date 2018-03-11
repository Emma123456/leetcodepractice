package array;

import java.util.TreeMap;

class MyCalendarThree {
	class Node{
		int start,end;
		int k = 1;
		Node left,right;
		Node(int start,int end,int k){
			this.start  = start;
			this.end = end;
			this.k = k;
		}
	}
	Node root;
	int maxK=1;
    public MyCalendarThree() {
        
    }
    /**
     * 
     * @param start
     * @param end
     * @return
     */
    public int book(int start, int end) {
    	root = insert(start,end,root,1);
		return maxK;
    }
    
	
	private Node insert(int start, int end, Node cur,int val) {
		if (start >= end)
			return cur;
		if (cur == null) {
			return new Node(start, end,val);
		}
		if (start >= cur.end) {
			cur.right = insert(start, end, cur.right,val);
		} else if (end <= cur.start) {
			cur.left = insert(start, end, cur.left,val);
		} else {
			
			int a = Math.min(start, cur.start);
			int b = Math.max(start, cur.start);
			int c = Math.min(end, cur.end);
			int d = Math.max(end, cur.end);
			//细节是这里
			cur.left = insert(a, b, cur.left,start<=cur.start?val:cur.k);
			cur.right = insert(c, d, cur.right,end>=cur.end?val:cur.k);
			cur.start = b;
			cur.end = c;
			cur.k +=val;
		}
		maxK = Math.max(maxK, cur.k);
		return cur;
	}
}

/**
 * 时间线
 * https://leetcode.com/problems/my-calendar-iii/discuss/109556/JavaC++-Clean-Code
 * @author Administrator
 *
 */
class MyCalendarThreeV2 {
	private TreeMap<Integer, Integer> timeline = new TreeMap<>();

	public int book(int s, int e) {
		timeline.put(s, (timeline.get(s)!=null?timeline.get(s):0) + 1); // 1 new event will be
															// starting at [s]
		timeline.put(e, (timeline.get(e)!=null?timeline.get(e):0) - 1); // 1 new event will be
															// ending at [e];
		int ongoing = 0, k = 0;
		for (int v : timeline.values())
			k = Math.max(k, ongoing += v);
		return k;
	}
}
public class MyCalendar732 {
	
	public static void main(String[] args) {
		MyCalendarThreeV2 ct = new MyCalendarThreeV2();
		int param_1= ct.book(10,20);
		System.out.println(param_1);
		param_1 = ct.book(50,60);
		System.out.println(param_1);
		param_1 = ct.book(10,40);
		System.out.println(param_1);
		param_1 = ct.book(90,100);
		System.out.println(param_1);
		param_1 = ct.book(20,32);
		System.out.println(param_1);
		
		
	}

}
