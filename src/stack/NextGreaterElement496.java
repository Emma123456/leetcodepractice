package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement496 {
	/**
	 * 对于nums1中的每个元素，从自己的起始位置开始在nums2中查找值更大的元素。题目理解错误
	 * 对于nums1中的每个元素，先查找num2中自己所在位置，从右侧开始查找，最近的一个比当前元素大的元素
	 * 这个应该是O(n^3)
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		int[] nextNums = new int[nums1.length];
		for (int i = 0; i < nums1.length; i++) {
			nextNums[i] = -1;
			int idx = findIdx(nums2, nums1[i]);
			if (idx > -1) {
				for (int j = idx + 1; j < nums2.length; j++) {
					if (nums2[j] > nums1[i]) {
						nextNums[i] = nums2[j];
						break;
					}
				}
			}

		}
		return nextNums;
	}

	/**
	 * 太聪明了，O(n)级别的
	 * 但是在leetcode时间更长，？？
	 * Key observation:
Suppose we have a decreasing sequence followed by a greater number
For example [5, 4, 3, 2, 1, 6] then the greater number 6 is the next greater element for all previous numbers in the sequence

	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] nextGreaterElementV2(int[] nums1, int[] nums2) {
		int[] nextNums = new int[nums1.length];
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		for (Integer num : nums2) {
			while (!stack.isEmpty() && stack.peek() < num) {
				map.put(stack.pop(), num);
			}
			stack.push(num);
		}
		for (int i = 0; i < nums1.length; i++) {
			nextNums[i] = map.get(nums1[i]) != null ? map.get(nums1[i]) : -1;
		}
		return nextNums;
	}

	private int findIdx(int[] nums2, int val) {
		for (int i = 0; i < nums2.length; i++) {
			if (nums2[i] == val) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] nums1 = { 2, 4 };
		int[] nums2 = { 1, 2, 3, 4 };

		int[] r = new NextGreaterElement496().nextGreaterElementV2(nums1, nums2);

		for (int v : r) {
			System.out.print(v + "\t");
		}
	}
}
