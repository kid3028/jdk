package test.thread.join;

/**
 /**
 * 外部线程，例如main
 * 当外部线程调用t.join的时候，外部线程会获得线程对象t的锁，wait意味着拿到了该对象的锁，
 * 调用该对象的wait，直到该对象唤醒ma外部线程，比如子线程退出
 * 换句话说，外部线程线程调用t.join()时，必须要拿到线程t对象的锁， 如果拿不到的话，那么
 * 它是无法wait的，t.join(1000)并不是说外部线程等待1000millis，如果在外部线程等待之前，
 * 其他线程获得了t对象的锁，它等待的时间就不是1000millis了。
 * @Author kzh
 * @Date 2019/6/15 20:48
 */
public class JoinTest extends Thread{

    public static void main(String[] args) {
        Thread t = new JoinTest();
        t.start();
        try {
            // 当main线程中调用t.join(millis)时，main线程只等待millis毫秒，当达到时间时，无论子线程是否结束，main线程都不会再等待
            t.join(1000);
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end");

        // subThread.sleep(900);
//        subThread start
//        subThread sleep begin
//        subThread sleep end
//        subThread end
//        main thread end

        // subThread.sleep(2000）
//        subThread start
//        subThread sleep begin
//        main thread end  // main线程在等待1000秒后退出
//        subThread sleep end
//        subThread end
    }

    @Override
    public void run() {
        System.out.println("subThread start");
        System.out.println("subThread sleep begin");
        try {
            Thread.sleep(900);
            System.out.println("subThread sleep end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("subThread end");
    }
}
