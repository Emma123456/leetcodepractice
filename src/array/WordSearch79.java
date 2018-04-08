package array;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 
 [["a","a"]]
"aaa"

 * @author Administrator
 *
 */
public class WordSearch79 {
	public boolean exist(char[][] board, String word) {
        Map<Character,Deque<int[]>> map = new HashMap<Character,Deque<int[]>>();
        for(int i=0;i<board.length;i++){
                for(int j=0;j<board[0].length;j++){
                    if(map.get(board[i][j])==null){
                        map.put(board[i][j],new LinkedList<int[]>());
                    }
                    map.get(board[i][j]).add(new int[]{i,j});
                }
            }
        char[] chars = word.toCharArray();
        int idx = 0;
        return find(map,chars,idx,-1,-1);
    }
    private boolean find(Map<Character,Deque<int[]>> map,char[] chars,int idx,int i,int j){
        if(idx>=chars.length){
            return true;
        }
        if(map.get(chars[idx])==null){
            return false;
        }
        Deque<int[]> list = map.get(chars[idx]);
        int size = list.size();
        for(int k=0;k<size;k++){
        	int[] pos = list.poll();
        	if(i==-1 &&  j==-1){
                if(find(map,chars,idx+1,pos[0],pos[1])){
                    return true;
                }
            }else if((pos[0]==i-1 && pos[1]==j) || (pos[0]==i+1 && pos[1]==j) || (pos[0]==i && pos[1]==j-1) || (pos[0]==i && pos[1]==j+1)){
            	//注意：题目中这里位置要求是相邻的元素(横轴、纵轴)
                if(find(map,chars,idx+1,pos[0],pos[1])){
                    return true;
                }
            }
            list.addLast(pos);
        }
        return false;
    }
    
    /**
     * 这里巧妙的地方是判断board[i][j]!=chars[idx]放在递归函数中，使得代码形式统一
     * @param board
     * @param word
     * @return
     */
    public boolean existV2(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(find(board,chars,i,j,0)) return true;
            }
        }
        return false;
    }
    private boolean find(char[][] board,char[] chars,int i,int j,int idx){
        if(idx>=chars.length){
            return true;
        }
        if(i<0 || j<0 || i==board.length || j == board[0].length){
            return false;
        }
        if(board[i][j]!=chars[idx]){
            return false;
        }
        board[i][j] = '*';
        boolean result = find(board,chars,i-1,j,idx+1) 
            || find(board,chars,i+1,j,idx+1) 
            || find(board,chars,i,j-1,idx+1) 
            || find(board,chars,i,j+1,idx+1) ;
        board[i][j] = chars[idx];
        return result;
    }
	public static void main(String[] args) {

	}

}
