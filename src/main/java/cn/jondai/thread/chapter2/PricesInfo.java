package cn.jondai.thread.chapter2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by jondai on 2017/10/18.
 * 价格类
 */
public class PricesInfo {
    private double price1;
    private double price2;

    private ReentrantReadWriteLock lock;


    public PricesInfo() {
        price1 = 0;
        price2 = 0;

        lock = new ReentrantReadWriteLock();
    }

    public double getPrice1() {


        lock.readLock().lock();
        //处理价格
        double value = price1;


        System.out.println("线程"+Thread.currentThread().getName()+"的Price 1 当前价格为：" + price1);
        lock.readLock().unlock();

        return value;
    }

    public double getPrice2() {
        lock.readLock().lock();
        double value = price2;

        System.out.println("线程"+Thread.currentThread().getName()+"的Price 2 当前价格为：" + price1);
        lock.readLock().unlock();

        return value;
    }

    public void setPrice(double price1, double price2) {
        System.out.printf("开始写入.\n");
        lock.writeLock().lock();
        System.out.println(lock.isWriteLocked());
        System.out.printf("开始写入--加锁.\n");
        this.price1 += price1;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.price2 += price2;
        System.out.printf("写入完成.\n");
        lock.writeLock().unlock();
        System.out.printf("写入完成--解锁.\n");
    }

}
