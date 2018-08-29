package dfs;

import java.util.HashSet;
import java.util.Set;
/**
 * 看不明白的版本
 * http://www.cnblogs.com/grandyang/p/8452361.html
 * @author Administrator
 *
 */
public class CrackingTheSafe753 {
	private Set<String> seen;
	private StringBuilder answer;

	public String crackSafe(int n, int k) {
		if (n == 1 && k == 1)
			return "0";
		seen = new HashSet<String>();
		answer = new StringBuilder();

		StringBuilder strb = new StringBuilder();
		for (int i = 0; i < n - 1; i++) {
			strb.append("0");
		}

		String start = strb.toString();
		dfs(start, k);
		answer.append(start);
		return answer.toString();
	}

	private void dfs(String node, int k) {
		for (int x = 0; x < k; x++) {
			String nei = node +x;
			if(!seen.contains(nei)){
				seen.add(nei);
				dfs(nei.substring(1),k);
				answer.append(x);
			}
		}
	}
	
	public String crackSafeVLyndonWord(int n, int k) {
		int M = (int) Math.pow(k, n-1);
        int[] P = new int[M * k];
        for (int i = 0; i < k; ++i)
            for (int q = 0; q < M; ++q)
                P[i*M + q] = q*k + i;

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < M*k; ++i) {
            int j = i;
            while (P[j] >= 0) {
                ans.append(String.valueOf(j / M));
                int v = P[j];
                P[j] = -1;
                j = v;
            }
        }

        for (int i = 0; i < n-1; ++i)
            ans.append("0");
        return new String(ans);
	}
	public static void main(String[] args) {
		String str = new CrackingTheSafe753().crackSafe(3, 2);
		System.err.println(str);
	}
}
