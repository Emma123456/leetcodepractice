package dp;


import java.util.List;

/**
 * 时间复杂度O(n^2)
 * 空间复杂度O(n+m),m表示词的长度
 */
public class WordBreak139V4 {
    public boolean wordBreak(String s, java.util.List<String> wordDict) {
        int n = s.length();
        Trie trie = new Trie();
        trie.initWord(wordDict);
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i=1;i<=n;i++){
            for(int j = 0;j<i;j++){
                if(dp[j] && trie.find(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
class Trie{
    private TrieNode root = new TrieNode('/');
    public void initWord(List<String> wordDict){
        for (String word : wordDict){
            addWord(word);
        }
    }

    public boolean find(String word){
        TrieNode node = root;
        for(int i=0;i<word.length();i++){
            int index = word.charAt(i) - 'a';
            if(node.children[index]==null){
                return false;
            }
            node = node.children[index];
        }
        return  node.end;
    }

    private void addWord(String word) {
        TrieNode node = root;
        for(int i=0;i<word.length();i++){
            int index = word.charAt(i) - 'a';
            if(node.children[index]==null){
                node.children[index] = new TrieNode(word.charAt(i));
            }
            node = node.children[index];
        }
        node.end = true;
    }

    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean end;
        private char data;
        public TrieNode(char data){
            this.data = data;
        }
    }
}

