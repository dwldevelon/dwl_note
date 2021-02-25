package dwl.solution2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenlong.ding
 * @date 2021/2/20 11:36
 */
public class Soluction {

    class Result {
        int idx, l, r;

        Result(int idx, int l, int r) {
            this.idx = idx;
            this.l = l;
            this.r = r;
        }
    }

    class Solution {
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }
            char[] chars = s.toCharArray();
            List<Result> list = new ArrayList<>();
            for (int i = 0; i < chars.length; i++) {
                internal(i, 0, 0, chars, list);
                if (i < chars.length - 1 && chars[i] == chars[i + 1])
                    internal(i, 0, 1, chars, list);
            }
            Result result = list.stream().max((e1, e2) -> Integer.compare(e1.l + e1.r, e2.l + e2.r)).orElse(null);
            char[] r = new char[result.l + result.r + 1];
            System.arraycopy(chars, result.idx - result.l, r, 0, r.length);
            return new String(r);
        }

        void internal(int i, int l, int r, char[] chars, List<Result> list) {
            while (i - l >= 1 && i + r < chars.length - 1) {
                if (chars[i - ++l] != chars[i + ++r]) {
                    l--;
                    r--;
                    break;
                }
            }
            list.add(new Result(i, l, r));
        }
    }

    public static void main(String[] args) {
        Soluction soluction = new Soluction();
        soluction.test();
    }

    public void test() {
        String s = new Solution().longestPalindrome("cbbd");
        System.out.println(s);
    }

}
