package pers.kivi.javafragment.loader;

import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author wangqiwei
 * @date 2020/07/11 10:04 PM
 */
public class HotLoadClassTest {
    private File path = new File("/Users/kivi/Documents");

    @Test
    public void testClassForName() throws Throwable {
        Class aClass = Class.forName("MyString", true, new MyClassLoader(new URL[]{path.toURL()}));
        aClass.getDeclaredMethod("echo").invoke(null);

        /** 替换MyString实现，可加载最近类 **/
        aClass = Class.forName("MyString", true, new MyClassLoader(new URL[]{path.toURL()}));
        aClass.getDeclaredMethod("echo").invoke(null);
    }

    @Test
    public void testClassLoader() throws Throwable {
        Class<?> aClass = new MyClassLoader(new URL[]{path.toURL()}).loadClass("MyString");
        aClass.getDeclaredMethod("echo").invoke(null);

        /** 替换MyString实现，可加载最近类 **/
        aClass = new MyClassLoader(new URL[]{path.toURL()}).loadClass("MyString");
        aClass.getDeclaredMethod("echo").invoke(null);
    }

    public static class MyClassLoader extends URLClassLoader {
        boolean resolve;

        public MyClassLoader(URL[] urls) {
            super(urls);
        }

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
