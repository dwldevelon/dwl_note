package dwl.solution10;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenlong.ding
 * @date 2021/2/26 9:44
 */
public class Solution10 {


    /**
     * // '.' 匹配任意单个字符
     * // '*' 匹配零个或多个前面的那一个元素
     */
    public boolean isMatch(String s, String p) {
        if(s == null || p == null){
            return false;
        }
        char[] pchars = p.toCharArray();
        if(pchars.length == 1){
            return p.equals(s) || p.equals(".");
        }
        if(pchars.length == 2 && '*' == pchars[1]){
            if(pchars[0] == '.'){
                return true;
            }
            char[] schars = s.toCharArray();
            for (char schar : schars) {
                if(schar != pchars[0]){
                    return false;
                }
            }
            return true;
        }
        char[] schars = s.toCharArray();

        Map<String,int[]> map = new HashMap<>();
        int x = 0;
        for(int i = 0 ; i < pchars.length ; i ++){
            String internalP = null;
            if(i < pchars.length - 1 && pchars[i+1] == '*' ){
                internalP = new String(new char[]{pchars[i],pchars[i+1]});
                i ++;
            }else {
                internalP = new String(new char[]{pchars[i]});
            }
            for(int j = x ; j < schars.length ; j ++){
                boolean result = isMatch(s.substring(x,j),internalP);
                if(result){
                    map.put(internalP,new int[]{x,j});
                    continue;
                }else {

                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String a = "a";
        System.out.println(a.substring(0,0));
    }


}
