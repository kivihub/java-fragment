package pers.kivi.javafragment.loader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author wangqiwei
 * @date 2020/07/11 10:04 PM
 */
public class InitialTest {

    public static void main(String[] args) throws ClassNotFoundException{
        new MyClassLoader(new URL[]{}, false).loadClass("pers.kivi.javafragment.loader.InitialTest$InnerClass");
        System.out.println("Load Class: resolve = false");
        new MyClassLoader(new URL[]{}, true).loadClass("pers.kivi.javafragment.loader.InitialTest$InnerClass");
        System.out.println("Load Class: resolve = true");

        /**
         * 执行初始化逻辑，即调用<clinit>()
         */
        Class.forName("pers.kivi.javafragment.loader.InitialTest$InnerClass");
        System.out.println("Class.forName: with initialize");

    }

    public static class InnerClass {
        static {
            System.out.println("static block");
        }
    }

    public static class MyClassLoader extends URLClassLoader {
        boolean resolve;

        public MyClassLoader(URL[] urls, boolean resolve) {
            super(urls);
            this.resolve = resolve;
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            return super.loadClass(name, true);
        }
    }
}
