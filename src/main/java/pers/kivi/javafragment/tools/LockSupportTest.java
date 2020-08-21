package pers.kivi.javafragment.tools;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport.park()方法返回的条件：
 * 1）LockSupport.unpark()
 * 2）thread.interrupt设置中断标志位，如果是已处于中断状态则直接返回
 * 3）没有原因的返回
 *
 * @author wangqiwei
 * @date 2020/08/21 1:32 PM
 */
public class LockSupportTest {
    private Object blocker = new Object();

    @Test
    public void testInterrupt2() throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    System.out.println("==start" + i++ + "==");
                    LockSupport.park(blocker);
                    System.out.println("==end" + i++ + "==");
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(5000);
        thread.interrupt();
        thread.join();
    }

    @Test
    public void testInterrupt() throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    System.out.println("==start" + i++ + "==");
                    LockSupport.park();

                    /**
                     * 1）如果注释-即不清除中断标记，之后的park方法检测到已中断时则直接返回，不会阻塞；
                     * 2）如果不注释-即清除中断标记，之后的park会正常阻塞
                     **/
                    System.out.println(Thread.interrupted());
                    System.out.println("==end" + i++ + "==");
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(5000);
        thread.interrupt();
        thread.join();
    }

    @Test
    public void testUnpark() throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    System.out.println("==start" + i++ + "==");
                    LockSupport.park();
                    System.out.println("==end" + i++ + "==");
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        LockSupport.unpark(thread);
        thread.join();
    }
}
