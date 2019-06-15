package test.thread.start;

/**
 * @Description
 * @Author kzh
 * @Date 2019/6/15 18:59
 */
public class StartTest {

    /**
     * 对一个线程多次调用start方法是非法的，一个线程一旦执行完毕是不会再重新启动，
     * 如果多次调用将会抛出IllegalThreadStateException异常
     * @param args
     */
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("test start thread more than one"));
        System.out.println("第一次调用start方法");
        thread.start();
        System.out.println("第二次调用start方法");
        thread.start();
//        第一次调用start方法
//        第二次调用start方法
//        Exception in thread "main" java.lang.IllegalThreadStateException
//        at java.lang.Thread.start(Thread.java:708)
//        at com.nqlz.hellorabbit.deadLetX.myConsumer.main(myConsumer.java:34)
//        test start thread more than one
    }
}
