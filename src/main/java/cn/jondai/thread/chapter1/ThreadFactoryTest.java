package cn.jondai.thread.chapter1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Created by jondai on 2017/10/17.
 * 测试对象：线程工厂
 *
 */
public class ThreadFactoryTest implements ThreadFactory{

    private int counter;

    private String name;

    private List<String> stats;

    public ThreadFactoryTest(String name) {
        this.name = name;
        counter = 0;

        stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r,  name + "-Thread-" +counter);
        stats.add(String.format("created thread %d with name %s on %s\n",thread.getId(),thread.getName(),new Date()));
        return thread;
    }

    public String getStats() {
        StringBuffer stringBuffer = new StringBuffer();

        for (String stat : stats) {
            stringBuffer.append(stat);
            stringBuffer.append("\n");
        }

        return stringBuffer.toString();
    }
}
