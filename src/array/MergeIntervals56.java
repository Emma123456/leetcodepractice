package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 测试数据
 * [[1,3],[2,6],[8,10],[15,18]]
[[2,3],[4,5],[6,7],[8,9],[1,10]]
[[2,3],[4,5],[6,7],[8,9]]
[[1,4],[5,6]]
 * @author Administrator
 *
 */
public class MergeIntervals56 {
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals == null || intervals.size() == 0)
			return intervals;
		int idx1 = 0;
		while (idx1 < intervals.size()) {
			int idx2 = idx1 + 1;
			while (idx2 < intervals.size()) {
				Interval interval1 = intervals.get(idx1);
				Interval interval2 = intervals.get(idx2);
				if (interval1.start < interval2.start && interval2.start > interval1.end || interval2.start < interval1.start && interval1.start > interval2.end) {
					idx2++;
				} else {
					interval1 = new Interval(Math.min(interval1.start, interval2.start), Math.max(interval1.end, interval2.end));
					intervals.set(idx1, interval1);
					intervals.remove(idx2);
					idx2 = idx1 + 1;
				}
			}
			idx1++;
		}
		return intervals;
	}

	public List<Interval> mergeV2(List<Interval> intervals) {
		if(intervals.size()<=1){
			return intervals;
		}
		Collections.sort(intervals, new Comparator<Interval>(){
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
			
		});
		List<Interval> result = new LinkedList<Interval>();
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;
		for(Interval interval : intervals){
			if (interval.start <= end) {
				end = Math.max(end, interval.end);
			}else{
				result.add(new Interval(start,end));
				start = interval.start;
				end = interval.end;
			}
		}
		result.add(new Interval(start,end));
		return result;
	}
	public static void main(String[] args) {
		List<Interval> intervals = Arrays.asList(new Interval(2,3),new Interval(4,5),new Interval(6,7),new Interval(8,9),new Interval(1,10));
		new MergeIntervals56().mergeV2(new ArrayList<Interval>(intervals));
	}

}

 class Interval {
	      int start;
	      int end;
	      Interval() { start = 0; end = 0; }
	      Interval(int s, int e) { start = s; end = e; }
 }
