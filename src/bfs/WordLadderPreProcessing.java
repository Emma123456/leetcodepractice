package bfs;

import javafx.util.Pair;

import java.util.*;

/****
 *
 输入是 beginWord 和 endWord。我们可以让他们分别代表图的 开始节点 startNode 和结束节点endNode。我们需要使用一些中间节点从开始节点走到结束节点。这些中间节点就是wordList中的值。每走一步的条件是：当前节点只能修改一个字母。
 我们会用一个无向无权重的图来实现，每个节点是一个单词。如果两个节点只差一个字母，那他们之间就有一条边。这个问题就可以归结为从开始节点到结束节点，找到一条最短路径。所以问题可以用BFS解决。
 这里其中一个重要的问题是如何找到一个节点的邻节点。对任意给定一个词，为了高效地找到其邻节点我们需要做一些预处理工作。预处理工作是把字母替换为一个非字母，例如*.例如 hot可以处理为 *ot,h*t,ho*。这样的预处理之后，我们会得到一个单词可以变形的一般状态。
 例如 dog有一个变形是 d*g,dig也有一个变形是d*g。这样可以很轻易地找到一个词的变形的词。
 预处理步骤能够让我们扎到wordList中任何单词的变形，可以比较轻松容易地找到单词的邻节点。
 方法1：
 1 对wordList做预处理工作，存在map中。key是所有单词的变形，value是符合这个变形的单词。
 2 将<beiginWord,1>放入队列中。1代表了当前节点所在的层次。我们需要返回endWord所在的层次，这也是从beginWord到endWord的最短路径长度。
 3 为了防止死循环，需要一个visited数组，记录已经处理过的节点。
 4 从队列头拿到一个元素currentWord。
 5 找到currentWord的所有变形，根据变形从map中找到邻节点。
 6 将这些邻节点加入到队列中。放入的时候每个单词word的层次是需要加1的：   <word,level+1>加入到队列。
 7 如果我们看到了endNode，返回它的层次。
 */
public class WordLadderPreProcessing {
    /**
     * 这里很精彩的地方时：先找到每个词的变形format，并且将format作为key。而我的思维中一直是想把词作为key，将邻节点列表作为value，放入map中。
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
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
        queue.offer(new Pair<>(beginWord,1));
        Map<String, Boolean> visited = new  HashMap<String,Boolean>();
        visited.put(beginWord,true);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                Pair<String,Integer> node = queue.poll();
                String word = node.getKey();
                int level = node.getValue();
                for(int j=0;j<wordLen;j++){
                    String format = word.substring(0,j)+"*"+word.substring(j+1,wordLen);
                    for(String nextWord : allComboDict.getOrDefault(format,new ArrayList<String>())){
                        if(nextWord.equals(endWord)) {
                            return level+1;
                        }
                        if(!visited.containsKey(nextWord)){
                            visited.put(nextWord,true);
                            queue.offer(new Pair<>(nextWord,level+1));
                        }

                    }
                }
            }
        }

        return 0;
    }
}
