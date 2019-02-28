package bfs;

import javafx.util.Pair;

import java.util.*;

/**
 *
 * 在单项BFS中，搜索需要的空间会随着层次的增加空间越来越多。想象你的访问一颗完全二叉树，空间会以2的指数增加。
 * 如果使用双向BFS，每个方向遍历的层次会减半，搜索空间会降低。
 */
public class WordLadderBidirectional {

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) {
                return 0;
            }
            Map<String,List<String>> allComboDict = new HashMap<String,List<String>>();
            int wordLen = beginWord.length();
            for(String word : wordList){
                for(int i=0;i<wordLen;i++){
                    String format = word.substring(0,i)+"*"+word.substring(i+1,wordLen);
                    List<String> list = allComboDict.getOrDefault(format,new ArrayList<String>());
                    list.add(word);
                    allComboDict.put(format,list);
                }
            }

            Queue<Pair<String,Integer>> queue = new LinkedList<Pair<String,Integer>>();
            Queue<Pair<String,Integer>> queueEnd = new LinkedList<Pair<String,Integer>>();
            queue.offer(new Pair<>(beginWord,1));
            queueEnd.offer(new Pair<>(endWord,1));
            Map<String, Integer> visited = new HashMap<String,Integer>();
            Map<String, Integer> visitedEnd = new  HashMap<String,Integer>();
            visited.put(beginWord,1);
            visitedEnd.put(endWord,1);
            while(!queue.isEmpty()  || !queueEnd.isEmpty()){
                int answer = visiteNode(queue,visited,visitedEnd,wordLen,allComboDict);
                if(answer>-1) return answer;
                answer = visiteNode(queueEnd,visitedEnd,visited,wordLen,allComboDict);
                if(answer>-1) return answer;
            }

            return 0;
        }

        private int visiteNode(Queue<Pair<String,Integer>> queue,Map<String, Integer> visited,Map<String, Integer> otherVisited,int wordLen,Map<String,List<String>> allComboDict ){
            int size = queue.size();
            for(int i=0;i<size;i++){
                Pair<String,Integer> node = queue.poll();
                String word = node.getKey();
                int level = node.getValue();
                for(int j=0;j<wordLen;j++){
                    String format = word.substring(0,j)+"*"+word.substring(j+1,wordLen);
                    for(String nextWord : allComboDict.getOrDefault(format,new ArrayList<String>())){
                        if(otherVisited.containsKey(nextWord)) {
                            return level+otherVisited.get(nextWord);
                        }
                        if(!visited.containsKey(nextWord)){
                            visited.put(nextWord,level+1);
                            queue.offer(new Pair<>(nextWord,level+1));
                        }

                    }
                }
            }
            return -1;
        }


}
