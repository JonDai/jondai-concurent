package cn.jondai.thread.chapter2;

/**
 * Created by jondai on 2017/10/17.
 */
public class SynchronizedTestMain {

    public static void main(String[] args) {

        //初始化银行，存入1000
        Account account = new Account();
        account.setBalance(10000);

        //创建一个公司的线程，一直存起
        Thread companyThread = new Thread(new Company(account));
        //创建一个银行atm的线程，一直取钱
        Thread bankATMThread = new Thread(new BankATM(account));


        System.out.println("银行初始化时，账户上的钱：" + account.getBalance());

        System.out.println("开始执行...");
        companyThread.start();
        bankATMThread.start();

        //等待两个线程都执行完毕

        try {
            companyThread.join();
            bankATMThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("存取结束时，账户上的钱："+ account.getBalance());
    }

}
