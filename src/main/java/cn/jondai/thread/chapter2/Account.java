package cn.jondai.thread.chapter2;

import java.util.concurrent.TimeUnit;

/**
 * Created by jondai on 2017/10/17.
 * 模拟银行账户
 */
public class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 使用synchronized关键字 将该方法转化为临界区
     * @param amount
     */
//    public synchronized  void addBalance(double amount){
    public void addBalance(double amount){
//        double temp = balance;

        //模拟处理时间
        try {
//            TimeUnit.SECONDS.sleep(0.1);
            TimeUnit.MICROSECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        balance += amount;

        System.out.println("存入:" + amount + " 剩余："+ balance);

//        balance = temp;
    }

    /**
     * 使用temp转换一次，以免出异常时，数据被修改出错。
     * @param amount
     */
//    public synchronized  void subtractAmount(double amount){
    public void subtractAmount(double amount){
        double temp = balance;

        //模拟处理时间
        try {
            TimeUnit.MICROSECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        temp -= amount;

        System.out.println("取出:" + amount + " 剩余："+ balance);
        balance = temp;
    }
}
