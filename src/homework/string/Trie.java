package homework.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 一棵只包含a-z 26个小写字母的trie树
 */
public class Trie {
    TrieNode root = new TrieNode('/');
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
    }

    /**
     * 查找word是否存在
     * @param word
     * @return
     */
    public boolean find(String word){
        TrieNode p = root;
        for(int i=0;i<word.length();i++){
            int idx = word.charAt(i)-'a';
            if(p.children[idx]==null){
                return false;
            }
            p = p.children[idx];
        }
        return p.end;
    }

    /***
     * 返回和word前缀匹配的字符串
     * @param word
     * @return
     */
    public List<String> match(String word){
        TrieNode p = root;
        for(int i=0;i<word.length();i++){
            int idx = word.charAt(i)-'a';
            if(p.children[idx]==null){
                return null;
            }
            p = p.children[idx];
        }

        List<String> result = new ArrayList<String>();
        visitWord(p,result,word);
        return result;
    }

    private void visitWord(TrieNode p, List<String> result,String str) {
        if(p.end){
            result.add(str);
        }
        for(int i=0;i<p.children.length;i++){
            if(p.children[i]!=null){
                visitWord(p.children[i],result,str+p.children[i].data);
            }
        }
    }

    class TrieNode{
        private char data;
        private boolean end;
        private TrieNode[] children = new TrieNode[26];
        public TrieNode(char data){
            this.data = data;
        }

        public boolean isEnd(){
            return end;
        }
    }

    public static void main(String[] args){
        Trie dic = new Trie();
        dic.addWord("hello");
        dic.addWord("world");
        dic.addWord("hi");
        dic.addWord("he");
        dic.addWord("is");

        System.out.println(dic.find("is"));
        System.out.println(dic.find("her"));
        System.out.println(dic.match("hew"));
    }
}
