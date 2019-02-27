package bfs;

import java.util.*;

/**
 现在每个词之间建立图关系。然后使用BFS或者DFS遍历
 如果一个词改变其中一个字母能变成另外一个词典中的词，那他们之间就有连线。
 */
public class WordLadder127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        int step = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            step++;
            for(int k=0;k<size;k++){
                String word = queue.poll();
                if(word.equals(endWord)){
                    return step;
                }
                for(int i=0;i<word.length();i++){
                    char[] charArray=word.toCharArray();
                    for(int j=0;j<26;j++){
                        charArray[i] = (char)('a'+j);
                        String newWord = String.valueOf(charArray);
                        if(wordList.contains(newWord)){
                            queue.offer(newWord);
                            wordList.remove(newWord);
                        }
                    }
                }
            }

        }
        return 0;
    }
    public  static void main(String[] args){
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        int r = new WordLadder127().ladderLength("a","b", list);
        System.out.println(r);
    }
}
