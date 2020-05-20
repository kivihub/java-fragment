package jmh;

/**
 * @author wangqiwei
 * @date 2020/05/19 6:35 PM
 */
public class WhileForTest {
    public void forFunc() {
        for (;;) {
            System.out.println("for loop");
        }
    }

    public void whileFunc() {
        while (true) {
            System.out.println("while loop");
        }
    }
}
