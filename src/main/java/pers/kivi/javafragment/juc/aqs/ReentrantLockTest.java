package pers.kivi.javafragment.juc.aqs;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangqiwei
 * @date 2020/06/16 12:54 PM
 */
public class ReentrantLockTest {
    private ReentrantLock reentrantLock = new ReentrantLock(true);

    @Test
    public void testLock() {
        reentrantLock.lock();
        Thread thread = new Thread(() -> {
            reentrantLock.lock();
        });
        thread.start();
    }

    @Test
    public void testCondition() throws InterruptedException {
        Condition condition1 = reentrantLock.newCondition();
        Condition condition2 = reentrantLock.newCondition();
        Thread thread1 = new Thread(() -> {
            reentrantLock.lock();
            try {
                condition1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }

        });

        Thread thread2 = new Thread(() -> {
            reentrantLock.lock();
            try {
                condition2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        });

        thread1.start();
        thread2.start();
    }


}
