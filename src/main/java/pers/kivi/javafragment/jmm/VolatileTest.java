package pers.kivi.javafragment.jmm;

/**
 * @author wangqiwei
 * @date 2020/05/25 6:46 PM
 */
public class VolatileTest {

    private boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        Thread threadA = new Thread(() -> {
            volatileTest.change();
            System.out.println("aaa");
        });
        Thread threadB = new Thread(() -> {
            volatileTest.foreach();
            System.out.println("bbb");
        });

        threadB.start();
        Thread.sleep(1000);
        threadA.start();
    }

    private void change() {
        flag = false;
    }

    private void foreach() {
        while (flag) {
            try {
                /**
                 *sleep方法内加了内存栅栏
                 */
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}
