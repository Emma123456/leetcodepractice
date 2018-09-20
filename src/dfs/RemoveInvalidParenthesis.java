package dfs;

import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParenthesis {
	/**
	 * 最直接的想法：把s中每一个(,)去掉检查，是不是有效字符串;这样不符合题意的要求：去掉最少的括号
	 * 
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
		dfs(s, leftCount, rightCount, validList, 0, new StringBuilder());
		return validList;
	}

	private void dfs(String s, int leftCount, int rightCount, List<String> validList, int idx, StringBuilder strb) {
		if (leftCount < 0 || rightCount < 0  )
			return;
		String newString = strb.toString();
		if (leftCount == 0 && rightCount == 0 && idx == s.length() && valid(newString)) {
			//有重复的
			if(!validList.contains(newString)){
				validList.add(newString);
			}
			return;
		}
		if(idx == s.length()) return;
		int len = strb.length();
		if (s.charAt(idx) == '(') {
			// 去掉左括号
			dfs(s, leftCount - 1, rightCount, validList, idx + 1, strb);
			// 保留左括号
			strb.append(s.charAt(idx));
			dfs(s, leftCount, rightCount, validList, idx + 1, strb);
		} else if (s.charAt(idx) == ')') {
			// 去掉右括号
			dfs(s, leftCount, rightCount - 1, validList, idx + 1, strb);
			// 保留右括号
			strb.append(s.charAt(idx));
			dfs(s, leftCount, rightCount, validList, idx + 1, strb);
		} else {
			strb.append(s.charAt(idx));
			dfs(s, leftCount, rightCount, validList, idx + 1, strb);
		}
		strb.setLength(len);
	}

	private boolean valid(String s) {
		int leftCount = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				leftCount++;
			if (s.charAt(i) == ')' && leftCount-- == 0)
				return false;
		}
		return leftCount > 0 ? false : true;
	}
	public static void main(String[] args) {
		String s = "(a)())()";
		List<String> validList = new RemoveInvalidParenthesis().removeInvalidParentheses(s);
		System.out.println(validList);
	}
}
