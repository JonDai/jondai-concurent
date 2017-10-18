package cn.jondai.thread.chapter2;

/**
 * Created by jondai on 2017/10/18.
 * 测试lock锁,多人使用一台打印机。
 *
 * 拓展：多人使用多台打印机
 */
public class LockMain {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++){
            threads[i] = new Thread(new Job(printQueue));
        }

        for (int i = 0; i < 10; i++){
            threads[i].start();
        }
    }
}


class Job implements Runnable{
    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.println(" Going to print a document" + Thread.currentThread().getName());

        printQueue.printJob(new Object());

        System.out.println("The document has been printed" + Thread.currentThread().getName());
    }
}
