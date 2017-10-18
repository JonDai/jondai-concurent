package cn.jondai.thread.chapter2;

/**
 * Created by jondai on 2017/10/18.
 * 测试生产者和消费者模式
 * 使用synchronized同步方法范围，以及wait和notifyAll
 */
public class ProducerAndConsumerMain {

    public static void main(String[] args) {

        EventStorage eventStorage = new EventStorage();

        Thread producerThread = new Thread(new Producer(eventStorage));

        Thread consumerThread = new Thread(new Consumer(eventStorage));

        producerThread.start();
        consumerThread.start();

    }
}



class Producer implements Runnable{
    private EventStorage eventStorage;

    public Producer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            eventStorage.set();
        }
    }
}


class Consumer implements Runnable{
    private EventStorage eventStorage;

    public Consumer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            eventStorage.get();
        }
    }
}
