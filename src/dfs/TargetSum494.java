package dfs;

import java.util.HashMap;
import java.util.Map;

public class TargetSum494 {
	private int cnt;
	/**
	 * dfs
	 * O(2^n)
	 * @param nums
	 * @param S
	 * @return
	 */
	public int findTargetSumWays(int[] nums, int S) {
		cnt = 0;
		dfs(nums, S, 0);
		return cnt;
	}

	private void dfs(int[] nums, int S, int idx) {
		if (idx == nums.length) {
			if (S == 0) {
				cnt++;
			}
			return;
		}
		dfs(nums, S + nums[idx], idx + 1);
		dfs(nums, S - nums[idx], idx + 1);
	}

	/**
	 * dp
	 * @param nums
	 * @param S
	 * @return
	 */
	public int findTargetSumWaysV2(int[] nums, int S) {
        return dp(nums,S,nums.length-1);
    }
    private int dp(int[] nums,int S,int idx){
        if(idx==0){
            if(nums[idx]==S && nums[idx]==-S) return 2;
            return nums[idx]==S || nums[idx]==-S ? 1:0;
        }else{
            return dp(nums,S+nums[idx],idx-1)+dp(nums,S-nums[idx],idx-1);    
        }
    }
    
    /**
     * dp+cache
     * @param nums
     * @param S
     * @return
     */
	public int findTargetSumWaysV3(int[] nums, int S) {
		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
		return dp(nums, S, nums.length - 1, map);
	}

	private int dp(int[] nums, int S, int idx, Map<Integer, Map<Integer, Integer>> map) {
		if (map.get(idx) != null && map.get(idx).get(S) != null) {
			return map.get(idx).get(S);
		}
		if (idx == 0) {
			if (nums[idx] == S && nums[idx] == -S)
				return 2;
			return nums[idx] == S || nums[idx] == -S ? 1 : 0;
		} else {

			int ans = dp(nums, S + nums[idx], idx - 1, map) + dp(nums, S - nums[idx], idx - 1, map);
			if (map.get(idx) == null) {
				map.put(idx, new HashMap<Integer, Integer>());
			}
			map.get(idx).put(S, ans);
			return ans;
		}
	}
	
	/**
	 * O(n*l)  l=2000
	 * @param nums
	 * @param S
	 * @return
	 */
	public int findTargetSumWaysV4(int[] nums, int S) {
		int[][] dp = new int[nums.length][2001];
		dp[0][nums[0] + 1000] = 1;
		dp[0][-nums[0] + 1000] = 1;
		for (int i = 1; i < nums.length; i++) {
			for (int sum = -1000; sum <= 1000; sum++) {
				if (dp[i - 1][sum + 1000] > 0) {
					dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
					dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
				}
			}
		}
		return S>1000?0:dp[nums.length-1][S+1000];
	}
	
	/**
	 * 滚动数组
	 * @param nums
	 * @param S
	 * @return
	 */
	public int findTargetSumWaysV5(int[] nums, int S) {
		int[] dp = new int[2001];
		dp[nums[0] + 1000] = 1;
		dp[-nums[0] + 1000] += 1;
		for (int i = 1; i < nums.length; i++) {
			int[] next = new int[2001];
			for (int sum = -1000; sum <= 1000; sum++) {
				if (dp[sum + 1000] > 0) {
					next[sum + nums[i] + 1000] += dp[sum + 1000];
					next[sum - nums[i] + 1000] += dp[sum + 1000];
				}
			}
			dp = next;
		}
		return S>1000?0:dp[S+1000];
	}
}
