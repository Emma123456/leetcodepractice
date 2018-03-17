package array;

import java.util.Arrays;
import java.util.List;

public class MaxChunksToMakeSorted769 {
	/**
	 * 思考了1个小时，思路还是错误的
	 * 一直在考虑到arr[i]为止，全局最大的数是多少
	 * 重点是：所有左边的元素 ＜ 右边元素的时候可以形成新的分区
	 * 如果对于i下标：左边元素的最大值 < (i+1)到(n-1)位置的最小元素  =>可以形成新的分区
	 * 新的思路就是：用maxOfLeft数组，记录当当前位置最大值；而不是拿一个变量找全局最大值
	 * @param arr
	 * @return
	 */
	public int maxChunksToSorted(int[] arr) {
		int n = arr.length;
		int[] maxOfLeft = new int[n];
		maxOfLeft[0] = arr[0];
		for (int i = 1; i < n; i++) {
			maxOfLeft[i] = Math.max(arr[i], maxOfLeft[i - 1]);
		}
		int[] minOfRight = new int[n];
		minOfRight[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			minOfRight[i] = Math.min(arr[i], minOfRight[i + 1]);
		}
		int chunksCount = 0;
		for (int i = 0; i < n - 1; i++) {
			if (maxOfLeft[i] <= minOfRight[i + 1])
				chunksCount++;
		}
		return chunksCount + 1;
	}
	
	/**
	 * 思路是：用一个max数组保存到当前位置最大的元素，每个位置的最大元素和已经排序好的数组中的元素比较，如果max[i] = sorted[i]，那在这个位置就可以分割。
	 * 在题目中说明arr[i] 在 [0,arr.length - 1]所以排序后的数组一定是0,1,2,....n-1
	 * @param arr
	 * @return
	 */
	public int maxChunksToSortedV2(int[] arr) {
		int count = 0;
		int max = -1;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
			if (max == i) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] arr0 = new int[] { 0, 1, 2, 3, 4 };
		int[] arr1 = new int[] { 4, 3, 2, 1, 0 };
		int[] arr2 = new int[] { 1, 0, 2, 3, 4 };
		int[] arr3 = new int[] { 1, 0, 2, 4, 3 };
		int[] arr4 = new int[] { 1, 4, 2, 0, 3 };
		int[] arr5 = new int[] { 1, 4, 3, 6, 0, 7, 8, 2, 5 };
		List<int[]> testList = Arrays.asList(arr0, arr1, arr2, arr3, arr4, arr5);
		MaxChunksToMakeSorted769 m = new MaxChunksToMakeSorted769();
		for (int[] arr : testList) {
			int r = m.maxChunksToSorted(arr);
			System.out.println(r);
		}
	}

}
