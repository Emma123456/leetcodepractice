package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PyramidTransitionMatrix756 {
	public boolean pyramidTransition(String bottom, List<String> allowed) {
		//构建前缀map
        Map<String,List<Character>> map = new HashMap<String,List<Character>>();
        for(String str : allowed){
            String key = str.substring(0,2);
            if(map.get(key)==null){
                map.put(key,new ArrayList<Character>());
            }
            map.get(key).add(str.charAt(2));
        }
        
        //初始化pyramid底层
        List<List<Character>> pyramidList = new ArrayList<List<Character>>();
        List<Character> list = new ArrayList<Character>();
        for(int i=0;i<bottom.length();i++){
            list.add(bottom.charAt(i));
        }
        pyramidList.add(list);
        //构建其它层直到顶层
        return dfs(pyramidList,map,new ArrayList<Character>(),bottom.length() -1);
    }
    
    private boolean dfs(List<List<Character>> pyramidList ,Map<String,List<Character>> map,List<Character> list,int len){
    	//长度为len层构建完成
        if(list.size()==len){
            pyramidList.add(0,list);
            if(len>1){
            	//接着构建下一层
            	boolean r = dfs(pyramidList,map,new ArrayList<Character>(),len-1);
            	if(!r){
            		pyramidList.remove(0);
            		return false;
            	}else{
            		return true;
            	}
            }else{
            	return true;
            }
        }
        int index = list.size();
        String pre = String.valueOf(pyramidList.get(0).get(index))+String.valueOf(pyramidList.get(0).get(index+1));
        if(map.get(pre)==null){
            return false;
        }
        for(Character ch : map.get(pre)){
            list.add(ch);
            if(dfs(pyramidList,map,list,len)) return true;
            list.remove(list.size()-1);
        }
        return false;
    }
    public static void main(String[] args) {
		boolean r = new PyramidTransitionMatrix756().pyramidTransition("CBDDA", Arrays.asList("ACC","ACA","AAB","BCA","BCB","BAC","BAA","CAC","BDA","CAA","CCA","CCC","CCB","DAD","CCD","DAB","ACD","DCA","CAD","CBB","ABB","ABC","ABD","BDB","BBC","BBA","DDA","CDD","CBC","CBA","CDA","DBA","ABA"));
		System.out.println(r);
	}
}
