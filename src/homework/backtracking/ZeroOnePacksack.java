package homework.backtracking;

public class ZeroOnePacksack {
    private int max = Integer.MIN_VALUE;
    /**
     * 决定第i个物品要不要放入背包
     * @param item
     * @param i
     * @param cw
     * @param maxWeight
     */
    public void f(int[] item,int i,int cw,int maxWeight){
        if(cw>maxWeight) return;
        if(i>=item.length){
            max = Math.max(max,cw);
            return;
        }
        max = Math.max(max,cw);
        f(item,i+1,cw,maxWeight);
        if(item[i]+cw<=maxWeight){
            f(item,i+1,cw+item[i],maxWeight);
        }


    }
}
