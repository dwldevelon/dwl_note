package dwl.solution3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenlong.ding
 * @date 2021/2/20 14:43
 */
public class Solution {

    class Result{
        int l,r,c;
        Result(int l,int r,int c){
            this.l = l;
            this.r = r;
            this.c = c;
        }
    }

    public int findShortestSubArray(int[] nums) {
        Map<Integer,Result> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Result result = map.get(nums[i]);
            if(result == null){
                map.put(nums[i],new Result(i,i,1));
            }else {
                if(i > result.r){
                    result.r = i;
                }
                result.c ++;
            }
        }
        Comparator<Result> comparator = (e1,e2) -> Integer.compare(e2.c,e1.c);
        comparator = comparator.thenComparing((e1,e2)->Integer.compare(e1.r-e1.l,e2.r-e2.l));
        return map.values().stream().min(comparator).map(e->e.r-e.l + 1).orElse(0);
    }

    public static void main(String[] args) {
        int a = new Solution().findShortestSubArray(new int[]{1,2,2,3,1});
        System.out.println(a);
    }
}
