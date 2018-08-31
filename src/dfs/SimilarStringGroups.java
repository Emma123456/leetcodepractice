package dfs;

public class SimilarStringGroups {
	public int numSimilarGroups(String[] A) {
		if (A == null)
			return 0;
		if (A.length < 2)
			return A.length;
		int groupCount = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == null)
				continue;
			String str = A[i];
			A[i] = null;
			groupCount++;
			dfs(str,A);
		}
		return groupCount;

	}

	private void dfs(String str, String[] A) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] == null)
				continue;
			if(isSimilar(str,A[i])){
				String s = A[i];
				A[i] = null;
				dfs(s,A);
			}
		}
	}

	/**
	 * 因为题目中提示a和b具有相同的长度，组成的字母也是相同的，只是字母顺序不同
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean isSimilar(String a, String b) {
		int dis = 0;
		for (int i = 0; i < a.length() && dis <= 2; i++) {
			if (a.charAt(i) != b.charAt(i))
				dis++;
		}
		return dis == 2 || dis == 0;
	}
	public static void main(String[] args) {
		int r = new SimilarStringGroups().numSimilarGroups(new String[]{"tars","rats","arts","star"});
		System.out.println(r);
	}
}
