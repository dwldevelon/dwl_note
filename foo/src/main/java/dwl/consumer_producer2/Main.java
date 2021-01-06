package dwl.consumer_producer2;

import com.sun.xml.internal.bind.v2.TODO;
import jdk.nashorn.internal.objects.NativeUint8Array;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author wenlong.ding
 * @date 2021/1/6 19:45
 */
public class Main {

    private static Integer TOTAL = 0;


    private static final Integer MAX = 10;
    private static final Integer MIN = 0;
    private static final Object LOCK = new Object();

    private static List<Runnable> list = new ArrayList<>();

    @SuppressWarnings("all")
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            list.add(() -> {
                for (int j = 0; j < 20; j++) {
                    Main.produce(1);
                }
            });
        }

        for (int i = 0; i < 20; i++) {
            list.add(() -> {
                for (int j = 0; j < 10; j++) {
                    Main.consume(1);
                }
            });
        }

        List<Thread> threadList = list.stream().map(e -> new Thread(e)).collect(Collectors.toList());
        threadList.forEach(e -> e.start());
        Thread.sleep(3000);
        System.out.println("end---------");
        while (true){
            synchronized (LOCK){
                System.out.println("-----");
                LOCK.notifyAll();
            }
            Thread.sleep(1000);
        }
//        threadList.forEach(e-> System.out.println(e.isInterrupted()));
//        threadList.forEach(e->e.stop());
    }

    public static void produce(int num) {
        synchronized (LOCK) {
            while (TOTAL >= MAX) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            TOTAL += num;
            System.out.println(String.format("生产苹果完成，num=%s, total=%s", num, TOTAL));
//            LOCK.notifyAll();
        }
    }

    public static void consume(int num) {
        synchronized (LOCK) {
            while (TOTAL <= MIN) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            TOTAL -= num;
            System.out.println(String.format("消费苹果完成， num=%s,total=%s", num, TOTAL));
//            LOCK.notifyAll();
        }
    }
}
