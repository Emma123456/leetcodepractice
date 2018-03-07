package array;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MyCalendar729 {
	

	public static void main(String[] args) {
		MyCalendarV2 c = new MyCalendarV2();
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