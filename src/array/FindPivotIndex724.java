package array;

public class FindPivotIndex724 {
	/**
	 * 从左向右一次选择pivot的index 当选择pivot index 为i的时候leftsum=sum(0,i-1)
	 * rightsum=sum(i+1,n-1) 当选择pinot index 为(i+1)的时候
	 * leftsum=sum(0,i)=sum(0,i-1)+a[i] rigtsum=sum(i+2,n-1)=sum(i+1,n-1)-a[i+1]
	 * 注意：左边可以是0个元素，右边也可以是0个元素
	 * @param nums
	 * @return
	 */
	public int pivotIndex(int[] nums) {
		int n = nums.length;
		if (n == 0)
			return -1;
		if (n == 1)
			return 0;
		int possibleIdx = 0;
		int leftsum = 0;
		int rightsum = 0;
		for (int i = 1; i < n; i++) {
			rightsum += nums[i];
		}
		while (leftsum != rightsum && possibleIdx < n - 1) {
			leftsum = leftsum + nums[possibleIdx];
			rightsum = rightsum - nums[possibleIdx + 1];
			possibleIdx++;
		}
		return leftsum == rightsum ? possibleIdx : -1;
	}

	/**
	 * 更加简洁版本
	 * leftsum + rightsum + nums[i] = sum，所以leftsum和rightsum只要计算一个就可以。
	 * 没有想明白为什么更慢
	 * @param nums
	 * @return
	 */
	public int pivotIndexV2(int[] nums) {
		int left = 0;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		for (int i = 0; i < nums.length; i++) {
			if(i>0) left += nums[i-1];
			if(sum-left-nums[i] == left) return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { -1, -1, 0, 1, 1, 0 };
		// int[] nums = new int[] { -1,-1};
		int r = new FindPivotIndex724().pivotIndexV2(nums);
		System.out.println(r);
	}

}
