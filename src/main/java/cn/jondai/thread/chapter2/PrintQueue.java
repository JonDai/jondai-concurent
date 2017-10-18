package cn.jondai.thread.chapter2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jondai on 2017/10/18.
 * 打印队列
 */
public class PrintQueue {
    /**
     * 重入锁（ReentrantLock）是一种递归无阻塞的同步机制
     */
    private final Lock queueLock = new ReentrantLock();

    public void printJob(Object document){
        //获取锁的控制权
        queueLock.lock();

        try {
            //模拟打印
            Long duration=(long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+ ":PrintQueue: Printing a Job during "+(duration/1000)+" seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //打印完成、解锁
            queueLock.unlock();
        }

    }




}
