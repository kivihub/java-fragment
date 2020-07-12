package pers.kivi.javafragment.clazz;

/**
 * @author wangqiwei
 * @date 2020/07/09 10:27 PM
 */
public class Student {
    private static String school = "希望小学";
    private String name;
    private int age;

    public void compute() {
        int a = 1;
        int b = 1;
        int c = a + b;
        System.out.println("a+b=" + c);
    }

    public static void main(String[] args) {
        new Student().compute();
    }
}
