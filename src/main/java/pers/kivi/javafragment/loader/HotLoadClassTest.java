package pers.kivi.javafragment.loader;

import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author wangqiwei
 * @date 2020/07/11 10:04 PM
 */
public class HotLoadClassTest {
    private String pathPrefix = "src/main/java/pers/kivi/javafragment/loader/";
    private File version1Path = new File(pathPrefix + "version1/");
    private File version2Path = new File(pathPrefix + "version2/");

    @Test
    public void testClassForName() throws Throwable {
        System.out.println(version1Path.getAbsoluteFile());
        Class aClass = Class.forName("MyString", true, new MyClassLoader(new URL[]{version1Path.toURL()}));
        aClass.getDeclaredMethod("echo").invoke(null);

        /** 替换MyString实现，可加载最近类 **/
        aClass = Class.forName("MyString", true, new MyClassLoader(new URL[]{version2Path.toURL()}));
        aClass.getDeclaredMethod("echo").invoke(null);
    }

    @Test
    public void testClassLoader() throws Throwable {
        Class<?> aClass = new MyClassLoader(new URL[]{version1Path.toURL()}).loadClass("MyString");
        aClass.getDeclaredMethod("echo").invoke(null);

        /** 替换MyString实现，可加载最近类 **/
        aClass = new MyClassLoader(new URL[]{version2Path.toURL()}).loadClass("MyString");
        aClass.getDeclaredMethod("echo").invoke(null);
    }

    @Test
    public void testEquals() throws ClassNotFoundException, MalformedURLException {
        MyClassLoader myClassLoader = new MyClassLoader(new URL[]{version1Path.toURL()});
        Class<?> myString = Class.forName("MyString", true, myClassLoader);
        Class<?> myString1 = myClassLoader.loadClass("MyString");

        myClassLoader = new MyClassLoader(new URL[]{version2Path.toURL()});
        Class<?> myString2 = myClassLoader.loadClass("MyString");
        Class<?> myString3 = Class.forName("MyString", true, myClassLoader);

        assert myString == myString1;
        assert myString2 == myString3;
        assert myString1 != myString2;
        assert myString1 != myString3;
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
