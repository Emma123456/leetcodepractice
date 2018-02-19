package array;

public class RotateArray189 {
	/**
	 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
	 * 首先我的思路是循环队列，一次移动一个位置，移动k次：分别为[7,1,2,3,4,5,6]  [6,7,1,2,3,4,5] [5,6,7,1,2,3,4]
	 * 接着查找规律一次移动k个，需要借助长度为k的数组
	 * @param nums
	 * @param k
	 */
	public void rotate(int[] nums, int k) {
		int n = nums.length;
		k = k % n;
		if (k == 0)
			return;
		int[] tmp = new int[k];
		int tmpIdx = 0;

		for (int i = n - k; i < n; i++) {
			tmp[tmpIdx++] = nums[i];
		}
		for (int i = n - 1; i >= k; i--) {
			nums[i] = nums[i - k];
		}
		for (int i = 0; i < k; i++) {
			nums[i] = tmp[i];
		}
	}

	/**
	 * 倒转思路
	 * let a= [1,2,3,4,5,6,7]
k = 3.

we have to first reverse the whole array by swapping first element with the last one and so on…
you will get[7,6,5,4,3,2,1]

reverse the elements from 0 to k-1
reverse the elements 7,6,5
you will get [5,6,7,4,3,2,1]

reverse the elements from k to n-1
reverse the elements 4,3,2,1
you will get[5,6,7,1,2,3,4]
	 * @param nums
	 * @param k
	 */
	public void rotateV2(int[] nums, int k) {
		k = nums.length % k;
		reverse(nums, 0, nums.length);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length);
	}

	private void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int tmp = nums[start];
			nums[start] = nums[end];
			nums[end] = tmp;
			start++;
			end--;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
