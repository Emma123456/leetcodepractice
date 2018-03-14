package array;

import java.util.ArrayList;
import java.util.List;
/**
 * 我没有找到规律是因为只是想到差值最小是1，最大是n-1.
 * 如果 k<n-1，想到随机取k个数。而别人想到的是取n-1,n-2,... 取k-1个，最后一个是1
 * @author Administrator
 *
 */
public class BeautifulArrangement667 {
	/**
	 * 差值最小是1，最大是n-1
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public int[] constructArrayV2(int n, int k) {
		int[] res = new int[n];
		for (int i = 0, l = 1, r = n; l <= r; i++) {
			res[i] = (k > 1 ? (k-- % 2 != 0 ? l++ : r--) : l++);
		}
		return res;
	}

	/**
	 * 1,n,2,n-1,3,n-2,4… ==> Diff: n-1, n-2, n-3, n-4, n-5… k个数有k-1个不同的差值
	 * 其余数字差值为1即可
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public int[] constructArrayV3(int n, int k) {
		int[] res = new int[n];
		int l = 1, r = n;
		int i = 0;
		// 1 9 2 8
		for (; i < k; i++) {
			res[i] = i % 2 == 0 ? l++ : r--;
		}
		if (k % 2 == 0) {//k=2  1 9 8 7 ....
			while(i<res.length){
				res[i++] = r--;
			}
		}else{//k=3 1 9 2 3 4 ...
			while(i<res.length){
				res[i++] = l++;
			}
		}
		return res;
	}

	private boolean find;

	public int[] constructArray(int n, int k) {
		int[] nums = new int[n];
		boolean[] visited = new boolean[n + 1];
		find = false;
		List<Integer> disList = new ArrayList<Integer>();
		dfs(n, k, nums, visited, 0, disList);
		return nums;
	}

	/**
	 * nums 使用下标 1-n 超时
	 * 
	 * @param n
	 * @param k
	 * @param nums
	 * @param idx
	 */
	private void dfs(int n, int k, int[] nums, boolean[] visited, int idx, List<Integer> disList) {
		if (idx >= n) {
			if (k == 0) {
				find = true;
			}
			return;
		}
		for (int i = 1; !find && i <= n; i++) {
			if (!visited[i]) {
				nums[idx] = i;
				int newk = k;
				if (idx > 0) {
					int dis = Math.abs(nums[idx] - nums[idx - 1]);
					if (!disList.contains(dis)) {
						if (k > 0) {
							disList.add(dis);
							newk = k - 1;
						} else {
							continue;
						}
					}
				}
				visited[i] = true;
				dfs(n, newk, nums, visited, idx + 1, disList);
				visited[i] = false;
				if (newk != k) {
					disList.remove(disList.size() - 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		int n = 9, k = 6;
		int[] nums = new BeautifulArrangement667().constructArrayV3(n, k);
		for (int num : nums) {
			System.out.print(num + "\t");
		}
	}

}
