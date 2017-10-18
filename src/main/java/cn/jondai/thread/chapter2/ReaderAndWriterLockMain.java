package cn.jondai.thread.chapter2;

/**
 * Created by jondai on 2017/10/18.
 */
public class ReaderAndWriterLockMain {

    public static void main(String[] args) {
        PricesInfo pricesInfo = new PricesInfo();

        Reader[] readers = new Reader[5];
        Thread[] threadsReader=new Thread[5];

        for (int i=0; i<5; i++){
            readers[i]=new Reader(pricesInfo);
            threadsReader[i]=new Thread(readers[i]);
        }


        Writer writers = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writers);


        for (int i=0; i<5; i++){
            threadsReader[i].start();
        }

        threadWriter.start();
    }
}


//读线程
class Reader implements Runnable{

    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {

            pricesInfo.getPrice1();
            pricesInfo.getPrice2();

        }
    }
}

//写线程
class Writer implements Runnable{
    private PricesInfo pricesInfo;

    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }


    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
                    pricesInfo.setPrice(1.0, 1.0);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
