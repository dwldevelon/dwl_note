package dwl.volatile01;

import java.util.concurrent.TimeUnit;

/**
 * @author wenlong.ding
 * @date 2021/4/13 17:44
 */
public class MyTest2 {

    public static void main(String[] args) {
        visibilityByVolatile();//验证volatile的可见性
    }

    /**
     * volatile可以保证可见性，及时通知其他线程，主物理内存的值已经被修改
     */
    public static void visibilityByVolatile() {
        MyData myData = new MyData();

        //第一个线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                //线程暂停3s
                TimeUnit.SECONDS.sleep(1);
                myData.addToSixty();
                System.out.println(Thread.currentThread().getName() + "\t update value:" + myData.num);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "thread1").start();
        //第二个线程是main线程
        while (myData.num == 0) {
            //如果myData的num一直为零，main线程一直在这里循环
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over, num value is " + myData.num);
    }
}

class MyData {
//        int num = 0;
    volatile int num = 0;

    public void addToSixty() {
        this.num = 60;
    }


}
