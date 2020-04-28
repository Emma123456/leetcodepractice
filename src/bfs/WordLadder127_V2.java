package bfs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 从两头开始变形
 * 时间复杂度O(n*26^(l/2))  l=字符串长度；n单词个数
 */
public class WordLadder127_V2 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<String>();
        for(String word : wordList){
            dict.add(word);
        }
        if(!dict.contains(endWord)) return 0;
        Set<String> q1 = new HashSet<String>();
        Set<String> q2 = new HashSet<String>();
        q1.add(beginWord);
        q2.add(endWord);
        int l = beginWord.length();

        int step = 0;
        while(!q1.isEmpty() && !q2.isEmpty()){
            if(q1.size()>q2.size()){
                Set<String> tmp = q1;
                q1 = q2;
                q2 = tmp;
            }
            step++;
            Set<String> q = new HashSet<String>();
            for(String word : q1){
                char[] chars = word.toCharArray();
                for(int j = 0;j<l;j++){
                    char ch = chars[j];
                    for(int k='a';k<='z';k++){
                        if(k==ch) continue;
                        chars[j] = (char)k;
                        String t = new String(chars);
                        if(q2.contains(t)){
                            return step+1;
                        }
                        if(!dict.contains(t)){
                            continue;
                        }
                        dict.remove(t);
                        q.add(t);
                    }
                    chars[j] = ch;
                }
            }
            q1 = q;
        }

        return 0;
    }
}
