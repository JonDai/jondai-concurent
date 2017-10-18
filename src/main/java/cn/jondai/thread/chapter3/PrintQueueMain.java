package cn.jondai.thread.chapter3;

/**
 * Created by jondai on 2017/10/18.
 * TestCase-3-1 控制并发访问资源
 * 打印队列主类
 */
public class PrintQueueMain {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        Thread[] printThreads = new Thread[10];


        for(int i = 0; i < 10; i++){
            printThreads[i] = new Thread(new PrintQueueMain.Job(printQueue));
        }

        for(int i = 0; i < 10; i++){
            printThreads[i].start();
        }
    }


    /**
     * 静态类部类Job
     */
    static class Job  implements Runnable{
        private PrintQueue printQueue;

        public Job(PrintQueue printQueue) {
            this.printQueue = printQueue;
        }

        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName() + "准备开始打印工作...");

            //调用printJob方法打印
            printQueue.printJob(new Object());

            System.out.println(Thread.currentThread().getName() + "已经打印完成!");
        }
    }
}



