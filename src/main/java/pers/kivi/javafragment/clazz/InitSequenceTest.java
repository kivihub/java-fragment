package pers.kivi.javafragment.clazz;

/**
 * @author wangqiwei
 * @date 2020/07/07 9:53 PM
 */
public class InitSequenceTest {
    static {
        System.out.println("static block1");
    }

    {
        System.out.println("no-static block1");
    }

    public InitSequenceTest() {
       System.out.println("construct method");
    }

    {
        System.out.println("no-static block2");
    }

    static {
        System.out.println("static block2");
    }

    public static void main(String[] args) {
        new InitSequenceTest();
    }

}
