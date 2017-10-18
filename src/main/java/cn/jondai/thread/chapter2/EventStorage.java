package cn.jondai.thread.chapter2;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jondai on 2017/10/18.
 */
public class EventStorage {

    private int maxSize;

    private LinkedList<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }

    //生产者，库存满了就不能再存入
    public synchronized void set(){
        while (maxSize == storage.size()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Queue add to Collection
        storage.offer(new Date());
        System.out.println("存入后，storage数量: "+storage.size());
        notifyAll();
    }

    //消费者，库存没有时，不能再取出
    public synchronized void get(){
        while (storage.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        storage.poll();
        System.out.println("取出后，storage的数量：" + storage.size());
        notifyAll();
    }
}
