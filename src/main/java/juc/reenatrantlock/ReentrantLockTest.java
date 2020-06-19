package juc.reenatrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock(true);
        reentrantLock.lock();
        Thread thread = new Thread(() ->{
            reentrantLock.lock();
        });
        thread.start();
        reentrantLock.unlock();
    }
}
