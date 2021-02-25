package dwl.solution9;


import java.util.ArrayList;
import java.util.List;

/**
 * @author wenlong.ding
 * @date 2021/2/24 17:35
 */
public class Solution9 {

    static class Result{
        int unit;
        char m;
        Result(int unit, char m){
            this.unit = unit;
            this.m = m;
        }
    }
    private static final List<Result> LIST = new ArrayList<>();
    static {
        LIST.add(new Result(1,'I'));
        LIST.add(new Result(5,'V'));
        LIST.add(new Result(10,'X'));
        LIST.add(new Result(50,'L'));
        LIST.add(new Result(100,'C'));
        LIST.add(new Result(500,'D'));
        LIST.add(new Result(1000,'M'));
    }

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();

        for (Result result : LIST) {
            int m = num / result.unit;
            num = num % result.unit;
            for (int i = 0; i < m; i++) {
                sb.append(result.m);
            }
        }
        String str = sb.toString();
        str = str.replace("VIIII","IV");
        str = str.replace("XIIII","IX");
        str = str.replace("LXXXX","XL");
        str = str.replace("CXXXX","XC");
        str = str.replace("DCCCC","CD");
        str = str.replace("MCCCC","CM");
        return str;

    }



}
