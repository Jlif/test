package lock;

/**
 * @author jiangchen
 * @date 2018年06月05日
 */

//一个线程进入不同的 synchronized 方法，是不会释放之前得到的锁的，所以输出还是顺序输出
public class Child extends Father implements Runnable {
    final static Child child = new Child();//为了保证锁唯一

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(child).start();
        }
    }

    public synchronized void doSomething() {
        System.out.println("1child.doSomething()");
        doAnotherThing(); // 调用自己类中其他的synchronized方法
    }

    private synchronized void doAnotherThing() {
        super.doSomething(); // 调用父类的synchronized方法
        System.out.println("3child.doAnotherThing()");
    }

    @Override
    public void run() {
        child.doSomething();
    }
}

class Father {
    public synchronized void doSomething() {
        System.out.println("2father.doSomething()");
    }
}
