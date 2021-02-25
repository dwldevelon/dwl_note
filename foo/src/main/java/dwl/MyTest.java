package dwl;

/**
 * @author wenlong.ding
 * @date 2021/1/6 19:27
 */
public class MyTest {

    private static final Object LOCK = new Object();

    @SuppressWarnings("all")
    public static void main(String[] args) throws InterruptedException {
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(findCircleNum(isConnected));
        int[][] isConnected2 = {{1,0,0},{0,1,0},{0,0,1}};
        System.out.println(findCircleNum(isConnected2));
    }

    public static int findCircleNum(int[][] isConnected) {
        if(isConnected.length == 0){
            return 0;
        }
        int result = isConnected.length;
        for(int i = 0 ; i < result ; i ++){
            for(int j = i + 1 ; j < result ; j ++){
                if(isConnected[i][j] == 1 && i != j){
                    result --;
                }
            }
        }
        return result;
    }

}
