package dwl.solution8;

/**
 * @author wenlong.ding
 * @date 2021/2/24 16:56
 */
public class Solution8 {

    private static final char I = 'I',V='V',X='X',L='L',C='C',D='D',M='M';

    /**
     * //I             1
     * //V             5
     * //X             10
     * //L             50
     * //C             100
     * //D             500
     * //M             1000
     *
     * // I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * // X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * // C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            char e = chars[i];
            switch (e) {
                case I:
                    if(i < chars.length - 1){
                        switch (chars[i + 1]){
                            case V:
                                result += 4;
                                i++;
                                continue;
                            case X:
                                result += 9;
                                i++;
                                continue;
                        }
                    }
                    result += 1;
                    break;
                case V:
                    result += 5;
                    break;
                case X:
                    if(i < chars.length - 1){
                        switch (chars[i + 1]){
                            case L:
                                result += 40;
                                i++;
                                continue;
                            case C:
                                result += 90;
                                i++;
                                continue;
                        }
                    }
                    result += 10;
                    break;
                case L:
                    result += 50;
                    break;
                case C:
                    if(i < chars.length - 1){
                        switch (chars[i + 1]){
                            case D:
                                result += 400;
                                i++;
                                continue;
                            case M:
                                result += 900;
                                i++;
                                continue;
                        }
                    }
                    result += 100;
                    break;
                case D:
                    result += 500;
                    break;
                case M:
                    result += 1000;
                    break;
            }
        }
        return result;
    }

}
