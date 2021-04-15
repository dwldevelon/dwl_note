package dwl.volatile01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author wenlong.ding
 * @date 2021/4/12 15:23
 */
public class MyTest {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Data data = new Data();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.age=1;
            countDownLatch.countDown();
        }).start();

        new Thread(()->{
            while (data.age == 0){
            }
            System.out.println("---可见---");
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
        System.out.println("---end---");
    }
}

class Data {
    int age;
}
