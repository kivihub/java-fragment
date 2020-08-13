package pers.kivi.javafragment.clazz;

/**
 * @author wangqiwei
 * @date 2020/07/07 10:46 PM
 */
public class LoadClassTest {
    public static void main(String[] args) throws ClassNotFoundException {
        // java.lang.ClassLoader.findLoadedClass方法会判断该类是否被（所有类加载器，不只是当前类加载器）加载过
        Class<?> str = ClassLoader.getSystemClassLoader().loadClass("java.lang.String");
        Class<?> aClass = Class.forName("pers.kivi.javafragment.clazz.LoadClassTest$Demo");
    }

    public static class Demo {
        static {
            System.out.println("demo static block");
        }
    }
}
