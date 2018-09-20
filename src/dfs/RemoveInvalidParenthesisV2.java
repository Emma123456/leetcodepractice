package dfs;

import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParenthesisV2 {
	/**
	 * 改进1：不再需要判断是否是有效字符串。有效字符的特征是：左括号在前，所以open>0；左右括号个数相同=>open=0；多余的左右括号都去掉了
	 * O(2^n)
	 * @param s
	 * @return
	 */
	public List<String> removeInvalidParentheses(String s) {
		int n = s.length();
		int leftCount = 0;
		int rightCount = 0;
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '(')
				leftCount++;
			if (s.charAt(i) == ')') {
				if (leftCount > 0) {
					leftCount--;
				} else {
					rightCount++;
				}
			}
		}
		List<String> validList = new ArrayList<String>();
		dfs(s, leftCount, rightCount, 0,validList, 0, new StringBuilder());
		return validList;
	}
	/**
	 * 如果没有 open<0，则会出现()())(这样的字符串
	 * @param s
	 * @param leftCount
	 * @param rightCount
	 * @param open
	 * @param validList
	 * @param idx
	 * @param strb
	 */
	private void dfs(String s, int leftCount, int rightCount, int open, List<String> validList, int idx, StringBuilder strb) {
		if (leftCount < 0 || rightCount < 0 || open < 0)
			return;
		
		if(idx == s.length()){
			if (leftCount == 0 && rightCount == 0 && open==0) {
				String newString = strb.toString();
				if (!validList.contains(newString)) {
					validList.add(newString);
				}
			}
			return;
		}
		
		int len = strb.length();
		if (s.charAt(idx) == '(') {
			// 去掉左括号
			dfs(s, leftCount - 1, rightCount, open, validList, idx + 1, strb);
			// 保留左括号
			strb.append(s.charAt(idx));
			dfs(s, leftCount, rightCount, open + 1, validList, idx + 1, strb);
		} else if (s.charAt(idx) == ')') {
			// 去掉右括号
			dfs(s, leftCount, rightCount - 1, open, validList, idx + 1, strb);
			// 保留右括号
			strb.append(s.charAt(idx));
			dfs(s, leftCount, rightCount, open - 1, validList, idx + 1, strb);
		} else {
			strb.append(s.charAt(idx));
			dfs(s, leftCount, rightCount,open, validList, idx + 1, strb);
		}
		strb.setLength(len);
	}

	public static void main(String[] args) {
		String s = "()())()";
		List<String> validList = new RemoveInvalidParenthesisV2().removeInvalidParentheses(s);
		System.out.println(validList);
	}
}
