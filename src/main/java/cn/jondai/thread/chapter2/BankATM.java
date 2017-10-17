package cn.jondai.thread.chapter2;

/**
 * Created by jondai on 2017/10/17.
 */
public class BankATM implements Runnable{

    private Account account;

    public BankATM(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            account.subtractAmount(1000);
        }
    }
}
