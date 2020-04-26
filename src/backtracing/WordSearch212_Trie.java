package backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间复杂度O(sum(l)+4^max(l))
 */
public class WordSearch212_Trie {
    private char[][] board;
    private String word;
    private int m;
    private int n;
    private TrieNode root;
    private List<String> result;
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        m = board.length;
        if(m == 0) return new ArrayList<String>();
        n = board[0].length;
        result = new ArrayList<String>();
        buildTree(words);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                dfs(i,j,root);
            }
        }
        return result;
    }

    private void dfs(int i,int j,TrieNode node){
        if(node.word!=null){
            result.add(node.word);
            node.word = null;
        }
        if(i<0 || i>=m || j<0 || j>=n || board[i][j]=='#'){
            return;
        }
        char ch = board[i][j];
        if(node.children[ch-'a']==null) return;
        TrieNode n = node.children[ch-'a'];
        board[i][j] = '#';
        dfs(i-1,j,n);
        dfs(i+1,j,n);
        dfs(i,j-1,n);
        dfs(i,j+1,n);
        board[i][j] = ch;
    }

    private void buildTree(String[] words){
        root = new TrieNode('/');
        for(String word : words){
            addWord(word);
        }
    }

    public void addWord(String word){
        TrieNode p = root;
        for(int i=0;i<word.length();i++){
            int idx = word.charAt(i)-'a';
            if(p.children[idx]==null){
                p.children[idx] = new TrieNode(word.charAt(i));
            }
            p = p.children[idx];
        }
        p.end = true;
        p.word = word;
    }

    class TrieNode{
        private char data;
        private boolean end;
        private TrieNode[] children = new TrieNode[26];
        private String word;
        public TrieNode(char data){
            this.data = data;
        }

        public boolean isEnd(){
            return end;
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[2][2];
        board[0] = new char[]{'a','b'};
        board[1] = new char[]{'a','a'};
        String[] words = new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"};
        new WordSearch212_Trie().findWords(board,words);
    }
}
