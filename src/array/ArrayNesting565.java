package array;

import java.util.ArrayList;
import java.util.List;

public class ArrayNesting565 {
	/**
	 * 像环的检测 Floyd环 最简单：把每个i遍历一次，比较长度 ；但是因为一个环内的元素无论从哪个元素开始，得到的长度都相同，所以需要去重，访问过的就不再访问了。
	 * 超时
	 * 这其实是DFS的思路，只是写法与之前的dfs写法不太一样.
	 * @param nums
	 * @return
	 */
	public int arrayNesting(int[] nums) {
		int maxLength = 0;
		List<Integer> visitedIndexList = new ArrayList<Integer>();
		for (int start = 0; start < nums.length; start++) {
			if (visitedIndexList.contains(start)) {
				continue;
			}
			int startVal = nums[start];
			int len = 1;
			int val = startVal;
			visitedIndexList.add(start);
			visitedIndexList.add(val);
			while (nums[val] != startVal) {
				val = nums[val];
				visitedIndexList.add(val);
				len++;
			}
			maxLength = Math.max(maxLength, len);
		}
		return maxLength;
	}

	/**
	 * 改进之处是：不需要用额外的空间存储是否访问过；访问过的可以标记为-1，因为初始每个元素是在[0,N-1]之间的。
	 * 改进的时候有两种：第一计算方式需要改进，这个时候需要重新观察数据。例如238。第二种是去重方式可以优化。例如本题。
	 * @param nums
	 * @return
	 */
	public int arrayNestingV2(int[] nums) {
		int maxLength = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i]==-1) {
				continue;
			}
			int len = 1;
			int val = nums[i];
			nums[i] = -1;
			while (val>=0) {
				int newVal = nums[val];
				nums[val] = -1;
				val = newVal;
				len++;
			}
			maxLength = Math.max(maxLength, len);
		}
		return maxLength-1;
	}
	/**
	 * 代码风格修改
	 * @param nums
	 * @return
	 */
	public int arrayNestingV3(int[] nums) {
		int maxLength = 0;
		for (int i = 0; i < nums.length; i++) {
			int len = 0;
			int idx = i;
			while (idx>=0) {
				int newVal = nums[idx];
				nums[idx] = -1;
				idx = newVal;
				len++;
			}
			maxLength = Math.max(maxLength, len);
		}
		return maxLength-1;
	}
	public static void main(String[] args) {
		int[] nums = new int[] { 5, 4, 0, 3, 1, 6, 2 };
		int r = new ArrayNesting565().arrayNestingV3(nums);
		System.out.println(r);
	}

}
