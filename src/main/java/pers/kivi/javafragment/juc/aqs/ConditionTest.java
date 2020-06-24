package pers.kivi.javafragment.juc.aqs;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wangqiwei
 * @date 2020/06/23 4:36 PM
 */
public class ConditionTest {
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    @Test
    public void test() {
        Thread thread = new Thread(() -> {
            reentrantReadWriteLock.readLock().lock();
            Condition condition = reentrantReadWriteLock.readLock().newCondition();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


    }
}
