package book.match;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ACMatchA {
    private ACNode root = new ACNode('/');
    public ACMatchA(List<String> wordList){
        for(String str : wordList){
            this.insert(str);
        }
    }

    /**
     * 构建trie树
     * @param str
     */
    private void insert(String str) {
        char[] text = str.toCharArray();
        ACNode p = root;
        for(int i=0;i<text.length;i++){
            int idx = text[i]-'a';
            if(p.children[idx]==null){
                p.children[idx] = new ACNode(text[i]);
            }
            p = p.children[idx];
        }
        p.isEnding = true;
        p.len = text.length;
    }

    /**
     * 构建失效指针
     * 一个层次遍历
     */
    public void buildFailurePointer(){
        root.fail = null;
        Queue<ACNode> queue = new LinkedList<ACNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            ACNode p = queue.poll();
            for(int i=0;i<p.children.length;i++){
                ACNode pc = p.children[i];
                if(pc==null) continue;
                if(p==root){
                    pc.fail = root;
                }else{
                    ACNode q = p.fail;
                    while(q!=null){
                        if(q.children[i]!=null){
                            pc.fail = q.children[i];
                            break;
                        }
                        q = q.fail;
                    }
                    if(q==null){
                        pc.fail = root;
                    }
                }

                queue.offer(pc);
            }
        }
    }

    public void match(char[] text) {
        ACNode p = root;
        for(int i=0;i<text.length;i++){
            int idx  = text[i] - 'a';
            while(p!=root && p.children[idx]==null){
                p= p.fail;
            }
            p = p.children[idx];
            if(p==null){
                p = root;
            }
            ACNode tmp = p;
            while(tmp!=root){
                if(tmp.isEnding){
                    int start = i-tmp.len+1;
                    System.out.println("长度:"+tmp.len+",起始位置："+start);
                }
                tmp = tmp.fail;
            }
        }
    }

    class ACNode{
        public char data;
        public int len;//当是字符串结尾的时候，匹配的字符串长度
        public boolean isEnding;//是否字符串结尾
        public ACNode fail;//失败指针
        public ACNode[] children = new ACNode[26];
        public ACNode(char ch){
            this.data = ch;
        }
    }


    public static void main(String[] args){
        ACMatchA ac = new ACMatchA(Arrays.asList("abcd","bcd","bc","c"));
        ac.buildFailurePointer();
        String text = "abcd";
        ac.match(text.toCharArray());


    }

}
