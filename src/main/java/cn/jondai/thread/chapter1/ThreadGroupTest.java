package cn.jondai.thread.chapter1;

import cn.jondai.thread.util.ScannerUtil;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by jondai on 2017/10/17.
 * 测试项目： 线程组的使用
 * 线程组作用： 可以批量管理线程或线程组对象，有效地对线程或线程组对象进行组织。
 *
 *
 */
public class ThreadGroupTest extends ThreadGroup{


    public ThreadGroupTest(String name) {
        super(name);
    }


    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("测试线程组");

        Thread soutThread1 = new Thread(threadGroup, () -> {
            while (!Thread.currentThread().isInterrupted()){
                System.out.println("线程1名称：" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread soutThread2 = new Thread(threadGroup, () -> {
            while (!Thread.currentThread().isInterrupted()){
                System.out.println("线程2名称：" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        soutThread1.start();
        soutThread2.start();
        System.out.println("活动的线程数为：" + threadGroup.activeCount());
        System.out.println("线程组的名称为：" + threadGroup.getName());

        interruptedThreadGroup(threadGroup);

    }


    /**
     * 描述方法作用: 中断线程组
     * @author daipengwei
     * @date 2017/10/17 上午10:33
     * @version V1.0
     * modify history
     */
    public static void interruptedThreadGroup(ThreadGroup threadGroup){
        Thread scannerThread = new Thread(() -> {
            while (true){
                if ("kill".equalsIgnoreCase(ScannerUtil.readFromConsole()))
                    threadGroup.interrupt();
                    System.out.println("调用了interrupt()方法");
                    return;
            }
        });
    }


    /**
     * 描述方法作用: 线程组异常不被控制，必须继承threadGroup再复写该方法
     * @author daipengwei
     * @date 2017/10/17 下午1:21
     * @version V1.0
     * modify history
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("线程组抛出异常，已被捕获");
        super.uncaughtException(t, e);
    }

}
