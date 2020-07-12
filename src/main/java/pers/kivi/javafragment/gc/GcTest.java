package pers.kivi.javafragment.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangqiwei
 * @date 2020/07/10 9:30 PM
 */
public class GcTest {
    /**
     * -Xms256m -Xmx256m -XX:-UseAdaptiveSizePolicy
     * -Xms256m -Xmx256m -XX:+UseAdaptiveSizePolicy
     * -XX:+PrintFlagsFinal -XX:+PrintFlagsInitial
     */
    byte[] byteArr = new byte[5 * 1024];
    public static void main(String[] args) throws InterruptedException {
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new GcTest());
            Thread.sleep(10);
        }
    }
}
