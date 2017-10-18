package cn.jondai.thread.chapter3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by jondai on 2017/10/18.
 * Test-3-2 等待多个并发事件完成
 * CountDownLatch类有3个基本元素：
    1.初始值决定CountDownLatch类需要等待的事件的数量。
    2.await() 方法, 被等待全部事件终结的线程调用。
    3.countDown() 方法，事件在结束执行后调用。
 */
public class CountDownLatchMain {

    public static void main(String[] args) {
        //创建一个Videoconference，等待参与者为10个
        Videoconference conference=new Videoconference(10);

        //创建 Thread 来运行这个 Videoconference 对象并开始运行
        Thread threadConference=new Thread(conference);
        threadConference.start();

        for (int i = 0; i < 10; i++){
            Participant participant = new Participant(conference, "Participant-" + i);
            Thread participantThread = new Thread(participant);
            participantThread.start();
        }
    }


    static class Videoconference implements Runnable{

        private final CountDownLatch controller;

        //实现类的构造函数，初始 CountDownLatch 属性。Videoconference 类接收将要等待的参与者的量为参数
        public Videoconference(int number) {
            this.controller = new CountDownLatch(number);
        }

        @Override
        public void run() {
            System.out.println("VideoConference：初始化" + controller.getCount() + "个参与者");

            try{
                //使用 await() 方法来等待全部的参与者。由于此法会抛出 InterruptedException 异常，所以要包含处理代码。
                controller.await();

                System.out.println("VideoConference:所有参与者全部到达!");
                System.out.println("VideoConference:开始执行~~");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //实现 arrive() 方法。每次有参与者到达都会调用此方法。它接收String类型的参数名为 name。
        public void arrive(String name){
            System.out.println(name + "已经到达");

            //计数器减1
            controller.countDown();

            System.out.println("VideoConference：还有"+ controller.getCount() + "个参与者等待完成！");
        }
    }


    static class Participant implements Runnable{

        private Videoconference conference;

        private String name;

        public Participant(Videoconference conference, String name) {
            this.conference = conference;
            this.name = name;
        }


        @Override
        public void run() {
            //让线程休眠一段时间
            long duration=(long)(Math.random()*10);

            try {
                TimeUnit.SECONDS.sleep(duration);

                conference.arrive(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
