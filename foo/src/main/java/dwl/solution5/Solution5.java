package dwl.solution5;


/**
 * @author wenlong.ding
 * @date 2021/2/20 15:54
 */
public class Solution5 {
    //将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
//
//
//P   A   H   N
//A P L S I I G
//Y   I   R
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。


    public String convert(String s, int numRows) {
        if(s == null){
            return null;
        }
        if(s .length() == 0){
            return "";
        }
        if(numRows <= 1){
            return s;
        }
        char[] chars = s.toCharArray();
        int step = (numRows - 1)*2;
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < chars.length ; i += step){
            sb.append(chars[i]);
        }
        for(int j = 1 ; j < numRows - 1 ; j ++){
            for(int i = 0 ; i < chars.length + step ; i += step){
                if(i - j >= 0 && i - j < chars.length) {
                    sb.append(chars[i - j]);
                }
                if(i+j < chars.length) {
                    sb.append(chars[i + j]);
                }
            }
        }
        for(int i = 0 ; i+numRows-1 < chars.length ; i += step){
            sb.append(chars[i+numRows-1]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = new Solution5().convert("PAYPALISHIRING",3);
        System.out.println(s);
    }

}
