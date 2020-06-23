package pers.kivi.javafragment.type;

/**
 * @author wangqiwei
 * @date 2020/05/18 5:03 PM
 */
public class EnumTest {
    enum Status {
        NEW, RUNNING, DIE;
    }

    public static void main(String[] args) {
        System.out.println(Status.NEW.name());
    }
}
