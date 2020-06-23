package juc.aqs;

import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * @author wangqiwei
 * @date 2020/06/22 9:28 PM
 */
public class SemaphoreTest {
    @Test
    public void test() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();

        Thread thread = new Thread(() -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "semaphore1");

        thread.start();
        Thread.sleep(1000);
    }
}
