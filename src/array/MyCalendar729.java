package array;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import array.MyCalendarTwoV2.Node;

public class MyCalendar729 {
	

	public static void main(String[] args) {
		MyCalendarV3 c = new MyCalendarV3();
		System.out.println(c.book(10, 20));
		System.out.println(c.book(15,25));
		System.out.println(c.book(20, 30));
	}

}
class MyCalendar {
	private List<Integer> startList = new ArrayList<Integer>();
	private List<Integer> endList = new ArrayList<Integer>();

	public MyCalendar() {

	}

	public boolean book(int start, int end) {
		for (int i = 0; i < startList.size(); i++) {
			if (start <= startList.get(i) && end > startList.get(i)) {
				return false;
			}
			if (start >= startList.get(i) && start < endList.get(i)) {
				return false;
			}
		}
		startList.add(start);
		endList.add(end);
		return true;
	}
}

class MyCalendarV2 {
	TreeMap<Integer, Integer> calendar;

	public MyCalendarV2() {
		calendar = new TreeMap<>();
	}

	public boolean book(int start, int end) {
		Integer floorKey = calendar.floorKey(start);
		if (floorKey != null && calendar.get(floorKey) > start)
			return false;
		Integer ceilingKey = calendar.ceilingKey(start);
		if (ceilingKey != null && ceilingKey < end)
			return false;

		calendar.put(start, end);
		return true;
	}
}

class MyCalendarV3 {
	class Node{
		int start,end;
		Node left,right;
		Node(int start,int end){
			this.start  = start;
			this.end = end;
		}
	}
	Node root;
	public boolean book(int start, int end) {
		if(insertable(start,end,root)){
			root = insert(start,end,root);
			return true;
		}
		return false;
	}
	private Node insert(int start, int end, Node cur) {
		if(cur==null) return new Node(start,end);
		if(start>=cur.end) {
			cur.right = insert(start, end, cur.right);
		}else if(end <= cur.start) {
			cur.left = insert(start, end, cur.left);
		}
		return cur;
	}

	private boolean insertable(int start, int end, Node cur) {
		if(cur==null) return true;
		if(start>=cur.end) {
			return insertable(start, end, cur.right);
		}else if(end <= cur.start) {
			return insertable(start, end, cur.left);
		}
		return false;
	}
}