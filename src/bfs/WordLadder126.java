package bfs;

import java.util.*;

public class WordLadder126 {
    /**
     * 先以词为节点，词与词之间只有一个字符不同的时候，两个词之间有连线。问题简化为从beginWord到endWord的最短路径。可以使用BFS访问。
     * 节点之间连线的查找方法有两种，一种是按字母替换；一种是将字母替换为*，查找变形相同的词。
     * 还有一个细节点：对于空值判断，可以节省时间；使用set查找数据，比list快。
     * 可以看到DOT和DOG有一个变形是相同的，所以这两个词之间有连线。
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> results = new ArrayList<List<String>>();
        if (endWord == null || endWord.equals("") || !wordList.contains(endWord)) return results;
        int len = beginWord.length();
        Set<String> wordSet = new HashSet<>();
        for(String w : wordList) wordSet.add(w);

        Queue<String> queue = new ArrayDeque<String>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        boolean exit = false;
        while(!queue.isEmpty()){
            int size = queue.size();
            List<String> tempList = new ArrayList<String>();
            for(int i=0;i<size;i++){
                String list = queue.poll();
                String[] words = list.split(",");
                String word = words[words.length-1];
                for(int j=0;j<len;j++){
                    char[] chars = word.toCharArray();
                    for(char ch = 'a';ch<='z';ch++){
                        chars[j] = ch;
                        String next = String.valueOf(chars);
                        if(!next.equals(word) && wordSet.contains(next) && !visited.contains(next)){
                            if(next.equals(endWord)){
                                results.add(Arrays.asList((list+","+next).split(",")));
                                exit =true;
                            }else{
                                queue.offer(list+","+next);
                                tempList.add(next);
                            }
                        }
                    }
                }

            }
            visited.addAll(tempList);
            if(exit) break;
        }
        return results;
    }
    public static void main(String[] args){
        List<List<String>> list = new WordLadder126().findLadders("hit","cog",Arrays.asList("hot","dot","dog","lot","log","cog"));
        System.out.println(list);
    }
}
