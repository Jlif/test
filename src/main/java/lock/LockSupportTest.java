package lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author jiangchen
 * @date 2018年06月06日
 */
public class LockSupportTest {
    static Object u = new Object();
    static TestSuspendThread t1 = new TestSuspendThread("t1");
    static TestSuspendThread t2 = new TestSuspendThread("t2");

    public static class TestSuspendThread extends Thread {
        public TestSuspendThread(String name) {
            setName(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                //Thread.currentThread().suspend();
                LockSupport.park();
            }
        }
    }

    //在使用suspend时会发生死锁，使用LockSupport则不会发生死锁
    //因为System.out.println是一个同步的方法
    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
//        t1.resume();
//        t2.resume();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
