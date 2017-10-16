package cn.jondai.thread.chapter1;

/**
 * Created by jondai on 2017/10/16.
 * 声明一个类来处理非受检异常，以免线程再抛出异常时中断线程
 * 该类必须实现UncaughtExceptionHandler接口
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("捕获到一个非受检异常：");
        System.out.println("线程ID为：" + t.getId());
        System.out.println("异常名称：" + e.getClass().getName() + " 异常信息: " + e.getMessage());
        System.out.println();
        e.printStackTrace(System.out); System.out.printf("Thread status: %s\n",t.getState());
    }
}
