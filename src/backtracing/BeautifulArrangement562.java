package backtracing;
/**

 * @author Administrator
 *
 */
public class BeautifulArrangement562 {
	private int count;
	public int countArrangement(int N) {
		boolean[] unused = new boolean[N + 1];
		count = 0;
		dfs(unused, N, 1);
		return count;
	}

	/**
	 * 深度优先搜索，对于第i个位置，枚举可能使用的数值。可能使用的数值需要满足两个条件
	 * 1 还未被使用
	 * 2 能够使得 数值%i=0或者 i%数值=0
	 * @param unused
	 * 			unused[i]=false，表示数值i还未没使用
	 * @param N
	 * 			当前还有几个数未安排
	 * @param i
	 * 			第几个位置
	 * 			
	 */
	private void dfs(boolean[] unused, int N, int i) {
		if (N == 0) {
			count++;
			return;
		}
		if (i >= unused.length) {
			return;
		}
		for (int j = 1; j < unused.length; j++) {//检查从1到N哪个数字还没有使用
			if (unused[j] == false) {
				if (j % i == 0 || i % j == 0) {
					unused[j] = true;
					dfs(unused, N - 1, i + 1);
					unused[j] = false;
				}
			}
		}
	}

	/**
	 * 调用次数更少
	 * @param N
	 * @return
	 */
	public int countArrangementV2(int N) {
		if (N == 0)
			return 0;
		int[] nums = new int[N + 1];
		for (int i = 0; i <= N; i++)
			nums[i] = i;
		helper(nums, N);
		return count;
	}
	/**
	 * backtracing从后面开始，这样每个搜索在失败之前都不会太深，因为较小的数字有更大的机会在他们自己之间被分割。
	 * 如果start从小到大，假如 start=1,nums[1]=4 ，那么4%1=0成立；start=4,nums[4]=3，那么4%3=0 或者 3%3=0都不成立；
	 * 如果start从大到小，假如start=4， nums[4]=3，那么4%3=0 或者 3%3=0都不成立;则就不可能再去判断 nums[4]=3情况下： start=3,2,1的情况了，这样就会减少调用次数。
	 * Also I don’t use “visited” boolean array but use swap of an array of 1~N to avoid duplication.
	 * 第start位能够选择的数字在nums[1~start]这些位置上的数字选择，每当选择一个，就把这个数字放在nums[start]位置上。这样别的位置就不能再选择这个数字了。聪明。
	 * @param nums
	 * @param start
	 * 			处理第start位
	 */
	private void helper(int[] nums, int start) {
		if (start == 0) {
			count++;
			return;
		}
		for (int i = start; i > 0; i--) {
			swap(nums, start, i);
			if (nums[start] % start == 0 || start % nums[start] == 0)
				helper(nums, start - 1);
			swap(nums, i, start);
		}
	}
	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	
	public static void main(String[] args) {
		int N = 10;
		int r = new BeautifulArrangement562().countArrangement(N);
		r = new BeautifulArrangement562().countArrangementV2(N);

		System.out.println(r);
	}
}
