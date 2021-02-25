package dwl.solution6;

/**
 * @author wenlong.ding
 * @date 2021/2/20 16:44
 */
public class Solution6 {

    public int myAtoi(String s) {
        if (s == null) {
            return 0;
        }
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        boolean positive = true;
        char first = s.charAt(0);
        if (first == '+') {
            s = s.substring(1);
        } else if (first == '-') {
            positive = false;
            s = s.substring(1);
        } else if (first < '0' || first > '9') {
            return 0;
        }
        if(s.length() == 0){
            return 0;
        }
        while (s.length() > 1 && s.charAt(0) == '0'){
            s = s.substring(1);
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                    s = s.substring(0, i);
                    break;
            }
        }
        if (s.length() > 11) {
            return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        Long result = Long.valueOf(s);
        if (result > Integer.MAX_VALUE) {
            return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        return positive ? result.intValue() : - result.intValue();
    }

    public static void main(String[] args) {
        int i = new Solution6().myAtoi("+-12");
        System.out.println(i);
        String a = "aa";
        String b = "bb";
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        String result = strs[0];
        for(int i = 1 ; i < strs.length ; i ++){
            if(result.length() == 0){
                return "";
            }
            int j = 0;
            while(j < result.length() && j < strs[i].length()){
                if(result.charAt(j) != strs[i].charAt(j)){
                    break;
                }
                j++;
            }
            result = result.substring(0,j);

        }
        return result;

    }

}
