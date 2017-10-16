package cn.jondai.thread.chapter1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by jondai on 2017/10/16.
 * 测试项目： 使用本地线程变量
 *
 * 测试原因： 多线程环境下公用一个变量，若处理不当，会造成共享变量的值混乱。
 *
 * 解决方案： 目前可以使用ThreadLocal类，来管理每个线程中的属性，达到共享变量的数据隔离性。在使用完毕之后可以手动调用remove方法释放
 *           或者gc自动回收。
 *
 *
 */
public class ShareVarMain {

    //共享开始时间变量：线程不安全
    private Date unsafeStartDate;

    /**
     * 共享开始时间变量：线程安全
     * ThreadLocal对象通常被设计为类的私有静态类型(private static)字段，用来关联线程的某种状态。
     */
    private static ThreadLocal<Date> safeStateDate = ThreadLocal.withInitial(Date::new);

//    private static ThreadLocal<Date> safeStateDate =  new ThreadLocal<Date>(){
//        @Override
//        protected Date initialValue() {
//            return new Date();
//        }
//    };

    public static void main(String[] args) {
        ShareVarMain shareVarMain = new ShareVarMain();
        for(int i = 0; i < 10; i++){
//            shareVarMain.unsafeTask();
            shareVarMain.safeTask();

            //休眠2秒开始调用
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void unsafeTask(){

        Thread unsafeThread = new Thread(() -> {
            unsafeStartDate = new Date();
            int rint = (int) Math.rint(Math.random() * 10);
            System.out.println("线程开始: "+ Thread.currentThread().getId() +" 时间为：" + unsafeStartDate + "随机休眠时间：" + rint);

            //随机休眠几秒
            try {
                TimeUnit.SECONDS.sleep(rint);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程结束: "+Thread. currentThread().getId()+" 时间为："+unsafeStartDate);
        });

        unsafeThread.start();
    }

    private void safeTask(){
        Thread safeThread = new Thread(() -> {

            int rint = (int) Math.rint(Math.random() * 10);
            System.out.println("线程开始: " + Thread.currentThread().getId() + "时间为： "+ safeStateDate.get() + "随机休眠时间：" + rint);

            //随机休眠几秒
            try {
                TimeUnit.SECONDS.sleep(rint);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程结束: "+Thread. currentThread().getId()+" 时间为："+safeStateDate.get());
        });

        safeThread.start();
    }
}
