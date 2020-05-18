package template;

import java.util.*;

public class AAA {
    public static void main(String[] args) {
        String a = "hello";
        System.out.println(a.substring(a.length()));
        List<int[]> trees = new ArrayList<int[]>();
        Collections.sort(trees, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        int[] nums = new int[]{1,3,2};
        Arrays.stream(nums).sorted();
        for(int i : nums){
            System.out.println(i);
        }

        LinkedList<String> path = new LinkedList<>();
        path.add("aa");
        path.removeLast();
    }
}
