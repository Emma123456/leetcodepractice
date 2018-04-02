package array;

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

	public static void main(String[] args) {

	}

}
