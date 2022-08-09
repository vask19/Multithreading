package deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {





    public static void main(String[] args) {
        Main main = new Main();

        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        Account account1 = new Account();
        Account account2 = new Account();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    account1.transfer(account1, account2,new Random().nextInt(100), lock1,lock2);

                }finally {
                    lock1.unlock();
                    lock2.unlock();
                }

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    account1.transfer(account2, account1,new Random().nextInt(100), lock2,lock1);

                }finally {
                    lock1.unlock();
                    lock2.unlock();
                }


            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Account.printBalance(account1,account2);

    }







    }


class Account{
    public int balance = 10000;




   public  void transfer(Account account1, Account account2,int amount,Lock lock1,Lock lock2){
        takeLocks(lock1,lock2);

        account1.balance -= amount;
        account2.balance += amount;

    }

    public static void printBalance(Account account1,Account account2){
        System.out.println(account1.balance);
        System.out.println(account2.balance);
        System.out.println(account1.balance + account2.balance);

    }



    public void takeLocks(Lock lock1,Lock lock2) {
        boolean firstLockTaken = false;
        boolean secondLockTaken = false;


        while (true) {
            try {
                firstLockTaken = lock1.tryLock();
                secondLockTaken = lock2.tryLock();
            } finally {
                if (firstLockTaken && secondLockTaken)
                    return;

                if (firstLockTaken)
                    lock1.unlock();
                if (secondLockTaken)
                    lock2.unlock();

            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    }
