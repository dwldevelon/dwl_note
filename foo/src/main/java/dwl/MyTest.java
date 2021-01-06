package dwl;

/**
 * @author wenlong.ding
 * @date 2021/1/6 19:27
 */
public class MyTest {

    private static final Object LOCK = new Object();

    @SuppressWarnings("all")
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            synchronized (LOCK){
                try {
                    System.out.println("---准备阻塞线程---" + Thread.currentThread().getName());
                    LOCK.wait();
                    System.out.println("---线程阻塞结束---" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        synchronized (LOCK) {
            LOCK.notify();
        }
        System.out.println("---main end---" );
    }
}
