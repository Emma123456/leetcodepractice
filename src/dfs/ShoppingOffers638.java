package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingOffers638 {
	private List<Integer> price;
    private List<List<Integer>> special;
    private int minPrice;
    /**
     * 思路：先购买套餐，再单独购买。每次选择套餐i的时候先检查数量是否超过了。如果没有超过，可以决定购买该套餐，或者放弃购买该套下选择下一套餐。直到购买完成比较最小的价格。
     * @param price
     * @param special
     * @param needs
     * @return
     */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        this.price = price;
        this.special = special;
        minPrice = Integer.MAX_VALUE;
        dfs(0,needs,0);
        return minPrice;
    }
    /**
     * 这里使用了一个默认条件：offer价格比单买便宜。如果不满足该条件会出问题。
     * @param specialIdx
     * @param needs
     * @param cost
     */
    private void dfs(int specialIdx,List<Integer> needs,int cost){
        int itemSize = price.size();
        if(specialIdx==special.size()){
            //单独购买
           for(int i=0;i<itemSize;i++){
                cost += needs.get(i)*price.get(i);
            }
            minPrice = Math.min(minPrice,cost);
            return;
        }
        //检查个数
        boolean buyAble = true;
        for(int i=0;i<itemSize;i++){
            if(special.get(specialIdx).get(i)>needs.get(i)){
                buyAble = false;
                break;
            }
        }
        if(buyAble){
            List<Integer> list = new ArrayList<Integer>();
            for(int i=0;i<itemSize;i++){
                list.add(needs.get(i)-special.get(specialIdx).get(i));
            }
            //买
            dfs(specialIdx,list,cost+special.get(specialIdx).get(itemSize));
        }
        //不买
        dfs(specialIdx+1,needs,cost);
        
    }
    
    public static void main(String[] args) {
    	List<List<Integer>> special = new ArrayList<List<Integer>>();
    	special.add(Arrays.asList(3,0,5));
    	special.add(Arrays.asList(1,2,10));
		new ShoppingOffers638().shoppingOffers(Arrays.asList(2,5), special, Arrays.asList(3,2));
	}
}
