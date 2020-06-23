package pers.kivi.javafragment.jmm;

/**
 * @author wangqiwei
 * @date 2020/05/25 6:46 PM
 */
public class VolatileTest2 {

    private boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        VolatileTest2 volatileTest = new VolatileTest2();
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
        }
    }
}
