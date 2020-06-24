package pers.kivi.javafragment.juc.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author wangqiwei
 * @date 2020/06/23 9:37 AM
 */
public class ArrayBlockingQueueTest {
    private static ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) throws InterruptedException {
        blockingQueue.take();
        blockingQueue.add(new Object());
    }
}
