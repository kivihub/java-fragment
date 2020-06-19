package juc.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangqiwei
 * @date 2020/06/16 12:54 PM
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock(true);
        reentrantLock.lock();
    }
}
