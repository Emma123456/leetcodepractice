package template;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.List;
public class BFS {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<String>(wordList);
        if(!dict.contains(endWord)) return 0;

        Queue<String> queue = new ArrayDeque<String>();
        queue.offer(beginWord);
        int step = 0;
        int l = beginWord.length();

        while(!queue.isEmpty()){
            step++;
            for(int i = queue.size(); i>0;i--){
                String word = queue.poll();
                char[]  chars = word.toCharArray();
                for(int j = 0;j<l;j++){
                    char ch = chars[j];
                    for(int k = 'a';k<='z';k++){
                        if(k == ch) continue;
                        chars[j] = (char)k;
                        String text = new String(chars);
                        if(text.equals(endWord)) return step+1;
                        if(!dict.contains(text)) continue;
                        dict.remove(text);
                        queue.offer(text);
                    }
                    chars[j] = ch;
                }

            }
        }
        return 0;
    }
}
