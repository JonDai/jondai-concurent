package cn.jondai.thread.chapter1;

/**
 * Created by jondai on 2017/10/16.
 * 测试项目： 在线程里处理不受控制的异常
 *
 * 问题原因：在线程中run方法不予许抛出异常，所以受检异常必须在线程中进行处理。
 *      对于非受检异常，若未进行处理，那么抛出异常时，线程就会停掉。
 *
 * 解决方案： 实现一个UncaughtExceptionHandler借口，再设置线程的异常处理方法。
 *
 * 捕获到一个非受检异常：
     线程ID为：10
     异常名称：java.lang.NumberFormatException 异常信息: For input string: "TTT"
     java.lang.NumberFormatException: For input string: "TTT"
     at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
     at java.lang.Integer.parseInt(Integer.java:580)
     at java.lang.Integer.parseInt(Integer.java:615)
     at cn.jondai.thread.chapter1.ExceptionHandlerMain.lambda$main$0(ExceptionHandlerMain.java:12)
     at java.lang.Thread.run(Thread.java:745)
     Thread status: RUNNABLE
 */

public class ExceptionHandlerMain {

    public static void main(String[] args) {

        Thread task = new Thread(() -> {
            //转换一个非数字、抛出一个非受检异常
            int numero=Integer.parseInt("TTT");
        });

        task.setUncaughtExceptionHandler(new ExceptionHandler());
        task.start();
    }

}


