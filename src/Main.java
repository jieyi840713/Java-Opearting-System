import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static Account account = new Account();

    private static class Account {
        private int balance = 0 ;
        private static Lock lock = new ReentrantLock();

        public int getBalance(){
            return this.balance;
        }

        //  public synchronized  deposit()
        //  synchronized java solve race condition
        public void deposit(){
            lock.lock();
            int newBalance = this.balance+1;
            try{
                Thread.sleep(3);
                this.balance = newBalance;
            }catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class AddMoneyTask implements Runnable{

        @Override
        public void run() {
//            synchronized (account){
//                account.deposit();
//            }
            account.deposit();
        }
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        // create 50 task;
        for(int i = 0 ; i< 50 ; i++){
            executorService.execute(new AddMoneyTask());
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){

        }

        System.out.println("The balance is "+ account.getBalance());
    }
}