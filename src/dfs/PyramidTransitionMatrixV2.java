package dfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PyramidTransitionMatrixV2 {
	int[][] T;
	Set<Long> seen;

	/**
	 * 因为是二进制操作，这一版本更快
	 * @param bottom
	 * @param allowed
	 * @return
	 */
	public boolean pyramidTransition(String bottom, List<String> allowed) {
		T = new int[7][7];// 题目中说字母只有A-G
		for (String str : allowed) {
			T[str.charAt(0) - 'A'][str.charAt(1) - 'A'] |= (1 << str.charAt(2) - 'A');// 前缀相同的，可能转到1个或者多个字母，所以用异或存储
		}
		seen = new HashSet<Long>();
		int n = bottom.length();
		int[][] A = new int[n][n];// 用来存放金字塔
		for (int i = 0; i < n; i++) {
			A[n - 1][i] = bottom.charAt(i) - 'A';
		}
		return solve(A, n - 1, 0, 0);
	}

	/**
	 * 
	 * @param A
	 *            用来存放金字塔
	 * @param len
	 *            当前需要解决的字符串长度
	 * @param curIdx
	 *            当前需要解决第几个数值
	 * @param curVal
	 *            上一步已经处理得到的数值
	 * @return
	 */
	private boolean solve(int[][] A, int len, int curIdx, long curVal) {
		if (len == 1 && curIdx == 1) {
			return true;
		} else if (len == curIdx) {
			//
			if (seen.contains(curVal))
				return false;
			seen.add(curVal);
			return solve(A, len - 1, 0, 0);
		} else {
			int w = T[A[len][curIdx]][A[len][curIdx + 1]];//A[len][curIdx] A[len][curIdx + 1]是下一层金字塔的对应坐标的值
			for (int b = 0; b < 7; b++) {// 因为是A-G，所以最多移动7位
				if(((w>>b) &1)!=0){
					A[len-1][curIdx] = b;
					if(solve(A,len,curIdx+1,curVal*8+(b+1))){
						return true;
					}
				}
			}
		}
		return false;
	}
	public static void main(String[] args) {
		boolean r = new PyramidTransitionMatrixV2().pyramidTransition("AAAA", Arrays.asList("AAA"));
		System.out.println(r);
	}
}
