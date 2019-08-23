package book.dp;

import java.util.ArrayList;
import java.util.List;

public class Shopping {
    private int[] price = new int[]{198,201,345,200};
    private int w = 500;
    private int minPrice = Integer.MAX_VALUE;
    private List<Integer> selectItems;
    private void f (int i,int priceSum,boolean[] shoppingStatus){
        if(priceSum>w){
            if(priceSum<minPrice){
                minPrice = priceSum;
                selectItems = new ArrayList<Integer>();
                for(int j=0;j<shoppingStatus.length;j++){
                    if(shoppingStatus[j]){
                        selectItems.add(j);
                    }
                }
            }
            return;
        }
        if(i==price.length) return;
        shoppingStatus[i] = true;
        f(i+1,priceSum+price[i],shoppingStatus);//选择第i件商品
        shoppingStatus[i] = false;
        f(i+1,priceSum,shoppingStatus);//不选择第i件商品
    }

    public void decision(){
        boolean[] shoppingStatus = new boolean[price.length];
        f(0,0,shoppingStatus);
    }

    /**
     * 状态数组第一维是商品下标，第二维是商品总价。我们需要给商品总价一个最大值，例如3w
     */
    public void decisionDp(){
        int n = price.length;
        int maxw = 3*w;
        boolean[][] states = new boolean[n][maxw+1];
        states[0][0] = true;
        if(price[0]<maxw){
            states[0][price[0]] = true;
        }
        for(int i=1;i<n;i++){
            //不购买第i个
            for(int j=0;j<maxw+1;j++){
                if(states[i-1][j]){
                    states[i][j] = true;
                }
            }
            //购买第i个
            for(int j = 0;j<maxw+1;j++){
                if(states[i-1][j]==true && j+price[i]<maxw){
                    states[i][j+price[i]] = true;
                }
            }
        }
        int minPrice = -1;
        for(int j = w;j<maxw+1;j++){
            if(states[n-1][j]){
                minPrice = j;
                break;
            }
        }

        //说明有选择
        if(minPrice!=-1){
            System.out.println(minPrice);
            int j = minPrice;
            for(int i=n-1;i>=1;i--){
                if(j-price[i]>=0 && states[i-1][j-price[i]]){
                    System.out.println(price[i]);//购买这件商品
                }
            }
            if(j!=0){
                System.out.println(price[0]);
            }
        }

    }

    public static void main(String[] args){
        Shopping s = new Shopping();
        s.decision();
        System.out.println(s.minPrice);
        System.out.println(s.selectItems);
        s.decisionDp();
    }
}
