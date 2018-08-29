package dfs;

import java.util.ArrayList;
import java.util.List;

public class ConcatenatedWords {
	class TrieNode {
		boolean isWord;
		TrieNode[] children = new TrieNode[26];
	}

	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		TrieNode root = buildTrieTree(words);
		List<String> concatenatedWords = new ArrayList<String>();
		for (String word : words) {
			if(isConcatenatedWord(word,root,0,0)){
				concatenatedWords.add(word);
			}
		}
		return concatenatedWords;
	}

	
	/**
	 * 深度优先查找word的TrieTree里面是否有多个词
	 * @param word
	 * @param root
	 * @param charIdx
	 * @return
	 */
	private boolean isConcatenatedWord(String word, TrieNode root, int startWordIdx,int wordCount) {
		TrieNode currentNode = root;
		int wordLen = word.length();
		for(int i=startWordIdx;i<wordLen;i++){
			int idx = word.charAt(i)-'a';
			if(currentNode.children[idx]==null){
				return false;
			}
			currentNode = currentNode.children[idx];
			if(currentNode.isWord && isConcatenatedWord(word,root,i+1,wordCount+1)){
				return true;
			}
		}
		return currentNode.isWord && wordCount>=1;
	}
	

	private TrieNode buildTrieTree(String[] words) {
		TrieNode root = new TrieNode();
		for (String word : words) {
			if (word == null || word.length() == 0) continue;
			TrieNode parent = root;
			int len = word.length();
			for (int i = 0; i < len; i++) {
				int idx = word.charAt(i) - 'a';
				if (parent.children[idx] == null) {
					parent.children[idx] = new TrieNode();
				}
				parent = parent.children[idx];
			}
			parent.isWord = true;
		}
		return root;
	}
	public static void main(String[] args) {
		ConcatenatedWords c = new ConcatenatedWords();
		List<String> l = c.findAllConcatenatedWordsInADict(new String[]{"rfkqyuqfjkx","rfuyp","catdog"});
		l = c.findAllConcatenatedWordsInADict(new String[]{});
		System.out.println(l);
	}
}
