package pers.kivi.javafragment.jmm;

import org.apache.commons.lang3.StringUtils;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author wangqiwei
 * @date 2020/08/10 12:26 PM
 */
public class ObjLayoutTest {
    public static void main(String[] args) throws InterruptedException {
        // 先睡眠5秒，等待偏向锁的延迟启动
        Thread.sleep(5002);

        printSplit("Layout when new Object");
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        synchronized (obj) {
            printSplit("Layout in synchronized block");
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }

        printSplit("Layout out synchronized block");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        printSplit("Layout after invoke method hashCode");
        System.out.println("hash code:" + obj.hashCode() + ", Hex String: " + Integer.toHexString(obj.hashCode()));
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        System.out.println(0b00010010_00111010_01000011_10011011);
    }

    private static void printSplit(String msg) {
        int leftLength = 20;
        System.out.println(StringUtils.repeat("=", 2 * leftLength + msg.length()));
        System.out.println(StringUtils.repeat("=", leftLength) + msg + StringUtils.repeat("=", leftLength));
        System.out.println(StringUtils.repeat("=", 2 * leftLength + msg.length()));
    }
}
