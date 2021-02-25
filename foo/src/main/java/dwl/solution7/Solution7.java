package dwl.solution7;

import java.util.*;

/**
 * @author wenlong.ding
 * @date 2021/2/22 9:19
 */
public class Solution7 {

    class Result {
        int k;
        int v;
        Result next;
    }

    /**
     * // 0 <= nums.length <= 3000
     * // -105 <= nums[i] <= 105
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> resultList = new ArrayList<>();
        for (int num : nums) {
            Integer v = map.get(num);
            map.put(num,v == null ? 1 : v+1);
        }
        Result head = null,curr = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Result r = new Result();
            r.k = entry.getKey();
            r.v = entry.getValue();
            if (head == null) {
                head = r;
            }
            if(curr == null){
                curr = r;
            }else {
                curr.next = r;
                curr = curr.next;
            }
        }
        Result t = new Result();
        t.next = head;
        while (t.next != null){
            t = t.next;
            int k = t.k;
            int v = t.v;
            int r = 0-k;
            Result tt = new Result();
            tt.next = v == 1 ? t.next : t;
            while (tt.next != null){
                tt = tt.next;
                int kk = tt.k;
                int vv = tt.v;
                int rr = r - kk;
                Result ttt = new Result();
                ttt.next = vv == 1 || (vv == 2 && k == kk) ? tt.next : tt;
                while (ttt.next != null){
                    ttt = ttt.next;
                    if(ttt.k == rr){
                        resultList.add(Arrays.asList(k,kk,rr));
                    }
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-2,0,0,2,2};
        List<List<Integer>> lists = new Solution7().threeSum(arr);
        System.out.println(lists);
    }


}
