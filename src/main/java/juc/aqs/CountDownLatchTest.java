package juc.aqs;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangqiwei
 * @date 2020/06/22 9:47 PM
 */
public class CountDownLatchTest {
    @Test
    public void test() {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        countDownLatch.countDown();
    }

}
