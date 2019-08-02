package book.match;

public class Trie {
    private TrieNode root = new TrieNode('/');
    public void insert(String text){

        char[] chars = text.toCharArray();
        TrieNode node = root;
        for(int i=0;i<chars.length;i++){
            int idx = chars[i] - 'a';
            if(node.childern[idx]==null){
                node.childern[idx] = new TrieNode(chars[i]);
            }
            node = node.childern[idx];
        }
        node.endWord = true;
    }



    public boolean find(String text){
        TrieNode node = root;
        char[] chars = text.toCharArray();
        for(int i=0;i<chars.length;i++){
            int idx = chars[i] - 'a';
            if(node.childern[idx]==null){
                return false;
            }
            node = node.childern[idx];
        }

        return node.endWord;
    }

    class TrieNode{
        private char data;
        private TrieNode[] childern;
        private boolean endWord;
        public TrieNode(char ch){
            this.data = ch;
        }
    }
}
