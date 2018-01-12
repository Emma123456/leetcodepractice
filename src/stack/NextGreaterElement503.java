package stack;

import java.util.Stack;

public class NextGreaterElement503 {
	/**
	 * 可以返回来再次比较
	 * 
	 * 解决循环数组的思路有两种： 第一：将原始数组扩大两倍，当做普通问题处理 第二：借助栈
	 * 
	 * @param nums
	 * @return
	 */
	public int[] nextGreaterElements(int[] nums) {
		int[] result = new int[nums.length];
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < nums.length; i++) {
			result[i] = -1;
			while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
				result[stack.pop()] = nums[i];
			}
			stack.push(i);
		}
		for (int i = 0; i < nums.length - 1; i++) {
			while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
				result[stack.pop()] = nums[i];
			}
		}
		return result;
	}

	/**
	 * nextGreaterElements更优雅的方式
	 * @param nums
	 * @return
	 */
	public int[] nextGreaterElementsV3(int[] nums) {
		int[] result = new int[nums.length];
		int doubleLength = 2 * nums.length;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < doubleLength; i++) {
			int num = nums[i % nums.length];
			if (i < nums.length) {
				result[i] = -1;
			}
			while (!stack.isEmpty() && num > nums[stack.peek()]) {
				result[stack.pop()] = num;
			}
			if (i < nums.length) {
				stack.push(i);
			}
		}
		return result;
	}

	public int[] nextGreaterElementsV2(int[] nums) {
		int max = Integer.MIN_VALUE;
		for (int num : nums) {
			max = Math.max(max, num);
		}

		int n = nums.length;
		int[] result = new int[n];
		int[] temp = new int[n * 2];

		for (int i = 0; i < n * 2; i++) {
			temp[i] = nums[i % n];
		}

		for (int i = 0; i < n; i++) {
			result[i] = -1;
			if (nums[i] == max)
				continue;

			for (int j = i + 1; j < n * 2; j++) {
				if (temp[j] > nums[i]) {
					result[i] = temp[j];
					break;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// int[] nums = new int[] { 5, 4, 3, 2, 1 };
		int[] nums = new int[] { 1, 2, 1 };
		int[] r = new NextGreaterElement503().nextGreaterElementsV3(nums);
		for (int v : r) {
			System.out.print(v + "\t");
		}
	}

}
