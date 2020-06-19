package juc.reenatrantlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wangqiwei
 * @date 2020/06/18 21:32
 */
public class ReadWriteLockTest {
    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread write = new Thread(() -> {
            reentrantReadWriteLock.writeLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            countDownLatch.countDown();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantReadWriteLock.writeLock().unlock();
        }, "write");
        Thread read1 = new Thread(() -> {
            System.out.println("prepare to read1");
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
        }, "read1");
        Thread read2 = new Thread(() -> {
            System.out.println("prepare to read2");
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
        }, "read2");

        write.start();
        countDownLatch.await();
        read1.start();
        read2.start();


    }
}
