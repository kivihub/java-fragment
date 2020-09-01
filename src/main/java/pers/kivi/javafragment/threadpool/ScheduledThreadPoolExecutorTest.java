package pers.kivi.javafragment.threadpool;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangqiwei
 * @date 2020/09/01 12:39 PM
 */
public class ScheduledThreadPoolExecutorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledThreadPoolExecutorTest.class);

    @Test
    public void testFixRate() throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(() -> {
            try {
                LOGGER.info("enter fix rate");
                Thread.sleep(2000);
                LOGGER.info("exit fix rate");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);

        Thread.sleep(10000);
        LOGGER.info("Main Thread over");
    }

    @Test
    public void testFixDelay() throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        ScheduledFuture<?> scheduledFuture = executor.scheduleWithFixedDelay(() -> {
            try {
                LOGGER.info("enter fix delay");
                Thread.sleep(4000);
                LOGGER.info("exit fix delay");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);

        Thread.sleep(100000);
        LOGGER.info("Main Thread over");
    }
}
