package cn.jondai.thread.chapter3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jondai on 2017/10/18.
 * TestCase-3-1 控制并发访问资源
 *  打印队列
 */
public class PrintQueue {

    private final Semaphore semaphore;

    //存储等待打印任务的和正在打印文档的printers
    private boolean[] freePrinters;

    //声明一个名为lockPrinters的Lock对象。将要使用这个对象来保护freePrinters array的访问。
    private Lock lockPrinters;

    //实现类的构造函数并初始能保护print quere的访问的semaphore对象的值。
    public PrintQueue() {
        this.semaphore = new Semaphore(3);

        //初始化所有打印队列都为空闲：true
        freePrinters = new boolean[3];
        for (int i = 0; i < 3; i++) {
            freePrinters[i] = true;
        }

        //初始化lock锁对象
        lockPrinters = new ReentrantLock();
    }

    public void printJob(Object document){
        //从信号量中获取一个许可
        try {
            semaphore.acquire();

            //获取安排打印的打印机号码
            int assignedPrinter = getPrinter();

            long duration=(long)(Math.random()*10);

            System.out.println(Thread.currentThread().getName() + "使用"+assignedPrinter+"号打印队列需要等待："+ duration +"秒");

            Thread.sleep(duration);

            //打印完成之后释放
            freePrinters[assignedPrinter] = true;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放信号量中的许可
            semaphore.release();
        }
    }


    /**
     * 获取当前空闲打印机的编号
     * @return
     */
    private int getPrinter(){
        int ret = -1;

        try {
            lockPrinters.lock();

            for (int i=0; i<freePrinters.length; i++) {
                    if (freePrinters[i]){
                        ret = i;
                        freePrinters[i] = false;
                        break;
                    }

            }
        }catch (Exception e){

        }finally {
            lockPrinters.unlock();
        }

        return ret;
    }

}
