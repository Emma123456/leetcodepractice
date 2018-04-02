package array;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class NumberMatchingSubsequences792 {
	/**
	 * 为每个词建一个词典
	 * @param s
	 * @param words
	 * @return
	 */
	public int numMatchingSubseq(String s, String[] words) {
		Map<Character, Deque<String>> wordMap = new HashMap<Character, Deque<String>>();
		for(char ch = 'a';ch<='z';ch++){
			wordMap.put(ch, new LinkedList<String>());
		}
		for (String word : words) {
			wordMap.get(word.charAt(0)).add(word);
		}
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			Deque<String> list = wordMap.get(ch);
			int size = list.size();
			for (int j = 0; j < size; j++) {
				String word = list.removeFirst();
				if(word.length()==1){
					count++;
				}else{
					wordMap.get(word.charAt(1)).add(word.substring(1));
				}
			}
		}
		return count;
	}

	/**
	 * 第二种思路是：s的长度为n，计算每个位置i之后 [a-z]首次出现的位置。这样，当检查每一个word的时候，只要查看位置就可以
	 * @param s
	 * @param words
	 * @return
	 */
	public int numMatchingSubseqV2(String s, String[] words) {
		int n = s.length();
		int[][] dp = new int[n + 1][26];
		Arrays.fill(dp[n], -1);
		char[] chars = s.toCharArray();
		for (int i = n - 1; i >= 0; i--) {
			for(int j=0;j<26;j++){
				dp[i][j] = dp[i+1][j];
			}
			dp[i][chars[i]-'a'] = i+1;//???
		}
		int count = 0;
		for(String word : words){
			char[] chs = word.toCharArray();
			int j = 0;
			for(char ch : chs){
				j = dp[j][ch-'a'];
				if(j==-1){
					break;
				}
			}
			if(j!=-1){
				count++;
			}
		}
		return count;
	}
	public static void main(String[] args) {
		String s = "abcde";
		String[] words = new String[]{"a", "bb", "acd", "ace"};
		int r  = new NumberMatchingSubsequences792().numMatchingSubseqV2(s, words);
		System.out.println(r);
	}

}
