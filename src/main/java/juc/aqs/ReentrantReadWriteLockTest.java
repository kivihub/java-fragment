package juc.aqs;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wangqiwei
 * @date 2020/06/18 21:32
 */
public class ReentrantReadWriteLockTest {
    /**
     * 如果都是读锁，线程不会进入同步队列
     */
    @Test
    public void test_lock_ReadRead() throws InterruptedException {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);

        Thread read1 = new Thread(() -> {
            System.out.println("prepare to " + Thread.currentThread().getName());
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
        }, "read1");
        Thread read2 = new Thread(() -> {
            System.out.println("prepare to " + Thread.currentThread().getName());
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
        }, "read2");

        read1.start();
        read2.start();
        positionForDebugPoint();
    }

    /**
     * write线程获取成功无需进入同步队列，两个读锁线程获取失败进入同步队列
     * head -> read1 -> read2
     * or
     * head -> read2 -> read1
     *
     * @throws InterruptedException
     */
    @Test
    public void test_lock_WriteReadRead() throws InterruptedException {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
        Thread write = new Thread(() -> {
            System.out.println("prepare to " + Thread.currentThread().getName());
            reentrantReadWriteLock.writeLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
        }, "write");
        Thread read1 = new Thread(() -> {
            System.out.println("prepare to " + Thread.currentThread().getName());
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
        }, "read1");
        Thread read2 = new Thread(() -> {
            System.out.println("prepare to " + Thread.currentThread().getName());
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
        }, "read2");

        write.start();
        Thread.sleep(1000);
        read1.start();
        read2.start();
        positionForDebugPoint();
    }

    /**
     * read1获取成功，write,read2进入同步队列；
     * head -> write -> read2
     *
     * @throws InterruptedException
     */
    @Test
    public void test_lock_ReadWriteRead() throws InterruptedException {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

        Thread write = new Thread(() -> {
            System.out.println("prepare to " + Thread.currentThread().getName());
            reentrantReadWriteLock.writeLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
        }, "write");
        Thread read1 = new Thread(() -> {
            System.out.println("prepare to " + Thread.currentThread().getName());
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
        }, "read1");
        Thread read2 = new Thread(() -> {
            System.out.println("prepare to " + Thread.currentThread().getName());
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
        }, "read2");

        read1.start();
        Thread.sleep(1000);
        write.start();
        Thread.sleep(1000);
        read2.start();
        positionForDebugPoint();
    }

    @Test
    public void test_lock_ReadReentrant() throws InterruptedException {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

        Thread read1 = new Thread(() -> {
            System.out.println("prepare to " + Thread.currentThread().getName());
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            LockSupport.park();
        }, "read1");
        Thread read2 = new Thread(() -> {
            System.out.println("prepare to " + Thread.currentThread().getName());
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            LockSupport.park();
        }, "read2");
        read1.start();
        Thread.sleep(5000);
        read2.start();
        positionForDebugPoint();
    }
    @Test
    public void test_unlock_writeReadRead() throws InterruptedException {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

        Thread write = new Thread(() -> {
            System.out.println("prepare to " + Thread.currentThread().getName());
            reentrantReadWriteLock.writeLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantReadWriteLock.writeLock().unlock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            LockSupport.park();
        }, "write");
        Thread read1 = new Thread(() -> {
            System.out.println("prepare to " + Thread.currentThread().getName());
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            LockSupport.park();
        }, "read1");
        Thread read2 = new Thread(() -> {
            System.out.println("prepare to " + Thread.currentThread().getName());
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantReadWriteLock.readLock().lock();
            System.out.println("enter " + Thread.currentThread().getName());
            System.out.println("enter " + Thread.currentThread().getName());
            LockSupport.park();
        }, "read2");
        write.start();
        read1.start();
        read2.start();
        positionForDebugPoint();
    }

    private void positionForDebugPoint() throws InterruptedException {
        Thread.sleep(5000);
        LockSupport.park();
    }

}
