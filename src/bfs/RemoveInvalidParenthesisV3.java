package bfs;

import java.util.ArrayList;
import java.util.List;
/**
 * 未完成
 * https://leetcode.com/problems/remove-invalid-parentheses/discuss/75032/Share-my-Java-BFS-solution
 * @author Administrator
 *
 */
public class RemoveInvalidParenthesisV3 {
	/**
	 * 使用BFS解决 字符串s长度为n；第一层字符串长度为n；第二层字符串长度为n-1；第三层字符串长度为n-2....
	 * 
	 * @param s
	 * @return
	 */
	public List<String> removeInvalidParentheses(String s) {
		List<String> validList = new ArrayList<String>();
		return validList;
	}

	public void bfs(String s, List<String> validList) {
		if (valid(s)) {
			validList.add(s);
			return;
		}
		int n = s.length();
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == ')'){
				
			}else{
				
			}
		}
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
}
