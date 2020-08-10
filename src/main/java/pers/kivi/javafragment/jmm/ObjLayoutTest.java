package pers.kivi.javafragment.jmm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author wangqiwei
 * @date 2020/08/10 12:26 PM
 */
public class ObjLayoutTest {
    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());


        synchronized (obj) {
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }

        System.out.println("hash code:" + obj.hashCode() + ", Hex String: " + Integer.toHexString(obj.hashCode()));
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        int hash = 0b00010010_00111010_01000011_10011011;
        System.out.println(hash);
    }
}
