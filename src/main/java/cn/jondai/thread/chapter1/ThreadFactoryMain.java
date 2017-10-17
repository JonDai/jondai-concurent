package cn.jondai.thread.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * Created by jondai on 2017/10/17.
 * 线程工程能为我们做：线程的统一管理，线程的统计、验证线程的创建等等。 （创建完成之后可以配合线程组一起使用）
 */
public class ThreadFactoryMain {

    public static void main(String[] args) {

        Thread task = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        ThreadFactoryTest threadFactoryTest = new ThreadFactoryTest("myThreadFactory");

        Thread thread;
        for (int i=0; i<10; i++){
            thread = threadFactoryTest.newThread(task);

            thread.start();

        }

        System.out.printf("Factory stats:\n");
        System.out.printf("%s\n",threadFactoryTest.getStats());
    }
}
