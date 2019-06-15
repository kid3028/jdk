package test.thread.join;

/**
 * @Description
 * @Author kzh
 * @Date 2019/6/15 21:10
 */
public class JoinTest02 extends  Thread{
    public static void main(String[] args) {
       Thread t = new JoinTest02();
       Thread t1 = new SubThread(t);
       // 先启动，保证先持有线程t的锁
       t1.start();
       t.start();
       long start = System.currentTimeMillis();
        try {

            /**
             * main线程只等待1000ms，无论子线程是否结束，均不再等待，
             * 但是等待的前提是main线程要持有线程t的锁
             *
             * 在main方法中，通过Thread t1 = new SubThread(t); t1.start();
             * 开启了另一个子线程，这个子线程它在holdThreadLock()中，通过
             * synchronize(thread)来获取到线程对象t的锁，并在sleep(3000)
             * 后释放，这就意味意味着就是main方法中t.join(1000)，等待1000ms，
             * 但是由于子线程t1获得了子线程t的锁，main线程无法获取到线程t的锁，
             * 因此，他实际等待时间是3000+
             */
            t.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end, cost" + (System.currentTimeMillis() - start));
    }

    @Override
    public void run() {
        super.run();
        System.out.println("t thread start");
        System.out.println("t thread sleep begin");
        try {
            Thread.sleep(800);
            System.out.println("t thread sleep end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t thread end");
    }


    static class SubThread extends Thread {
        Thread thread;

        public SubThread() {}

        public SubThread(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            super.run();
            this.holdTheadLock(this.thread);
        }

        private void holdTheadLock(Thread thread) {
            synchronized (thread) {
                System.out.println("hold thread lock");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("release thread lock");
            }
        }
    }
}
