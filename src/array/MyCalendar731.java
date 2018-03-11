package array;

import java.util.ArrayList;
import java.util.List;

class MyCalendarTwo {
	private List<int[]> bookList = new ArrayList<int[]>();
	private List<int[]> overlaps = new ArrayList<int[]>();

	public MyCalendarTwo() {

	}

	/**
	 * https://leetcode.com/problems/my-calendar-ii/discuss/109519/JavaC++-Clean-Code-with-Explanation
	 * 先判断是否重复，再判断在重复区域是否再重复
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean book(int start, int end) {
		for (int[] b : bookList) {
			if (Math.max(b[0], start) < Math.min(b[1], end)) {
				if (!bookOverlaps(Math.max(b[0], start), Math.min(b[1], end))){
					overlaps.clear();
					return false;
				}
			}
		}
		bookList.add(new int[] { start, end });
		return true;
	}

	private boolean bookOverlaps(int start, int end) {
		for (int[] b : overlaps) {
			if (Math.max(b[0], start) < Math.min(b[1], end))
				return false;
		}
		overlaps.add(new int[] { start, end });
		return true;
	}
}

/**
 * 二叉查找树版本
 * https://leetcode.com/problems/my-calendar-ii/discuss/114882/Java-Binary-Search-Tree-method-clear-and-easy-to-undertand-beats-99
 * @author Administrator
 *
 */
class MyCalendarTwoV2 {
	class Node{
		int start,end;
		boolean overlap;
		Node left,right;
		Node(int start,int end){
			this.start  = start;
			this.end = end;
		}
	}
	Node root;
	public MyCalendarTwoV2() {

	}
	
	 public boolean book(int start, int end) {
		 if(!insertable(start,end,root)){
			 return false;
		 }
		 root = insert(start,end,root);
		 return true;
	 }

	 private Node insert(int start, int end, Node cur) {
		 if(start>=end) return cur;
		 if(cur==null){
			 return new Node(start,end);
		 }
		 if(start >= cur.end) {
			 cur.right = insert(start,end,cur.right);
		 }else if(end <= cur.start){
			 cur.left = insert(start,end,cur.left);
		 }else{
			 cur.overlap = true;
			 int a = Math.min(start, cur.start);
			 int b = Math.max(start, cur.start);
			 int c = Math.min(end, cur.end);
			 int d = Math.max(end, cur.end);
			 cur.left = insert(a,b,cur.left);
			 cur.right = insert(c,d,cur.right);
			 cur.start = b;
			 cur.end = c;
		 }
		return cur;
	}

	/**
	  * 判断start，end 是否可插入
	  * @param start
	  * @param end
	  * @param curNode
	  * @return
	  */
	private boolean insertable(int start, int end, Node cur) {
		if(start>=end) return true;
		if(cur==null) return true;
		if(start >= cur.end) {
			return insertable(start,end,cur.right);
		}else if(end <= cur.start){
			return insertable(start,end,cur.left);
		}else{
			if(cur.overlap){
				return false;
			}else{
				if(start>=cur.start && end <= cur.end){
					return true;
				}else{
					return insertable(start,cur.start,cur.left) && insertable(cur.end,end,cur.right);
				}
			}
		}
	}
}
public class MyCalendar731 {

	public static void main(String[] args) {
		MyCalendarTwoV2 ct = new MyCalendarTwoV2();
		boolean param_1 = ct.book(10,20);
		System.out.println(param_1);
		param_1 = ct.book(10,20);
		System.out.println(param_1);
		param_1 = ct.book(50,60);
		System.out.println(param_1);
		param_1 = ct.book(10,40);
		System.out.println(param_1);
		param_1 = ct.book(5,10);
		System.out.println(param_1);
		param_1 = ct.book(25, 55);
		System.out.println(param_1);
		
	}

}
