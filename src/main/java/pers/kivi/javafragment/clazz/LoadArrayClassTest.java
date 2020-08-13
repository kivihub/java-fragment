package pers.kivi.javafragment.clazz;

import org.junit.Test;

/**
 * @author wangqiwei
 * @date 2020/08/12 6:28 PM
 */
public class LoadArrayClassTest {
    public LoadArrayClassTest() {
    }

    /**
     * Class.forName, ClassLoader.loadClass区别
     * 1）forName有缓存，即便classLoader卸载类也存在；
     * 2）forName执行初始化逻辑，loadClass不会；
     * 3）forName会解析数组类，loadClass不会；
     */
    @Test
    public void testLoadArray() throws ClassNotFoundException {
        String className = "[Ljava.lang.String;";
        Class<?> a = Class.forName(className);
        Class<?> b = Class.forName(className, true, Thread.currentThread().getContextClassLoader());
        System.out.println(a.getName());
        System.out.println(a.isArray());

        // throw ClassNotFoundException
        Class<?> c = LoadArrayClassTest.class.getClassLoader().loadClass(className);
    }

    @Test
    public void testInit() throws ClassNotFoundException {
        String className = "pers.kivi.javafragment.clazz.LoadArrayClassTest$Demo";
        Class<?> b = Class.forName(className, false, Thread.currentThread().getContextClassLoader());
    }

    public static class Demo {
        static {
            System.out.println("class static block");
        }
    }
}
