package exception;

/**
 * 开发中禁止在finally中包含return语句
 */
public class FinallyTest {
    public static void main(String[] args) {
        System.out.println(method0());
        System.out.println(method1());
        System.out.println(method2());
        System.out.println(method3());
    }

    /**
     * 返回0
     *
     * @return
     */
    private static int method0() {
        int a = 0;
        return a++;
    }

    /**
     * 返回1
     *
     * @return
     */
    private static int method1() {
        int a = 0;
        return ++a;
    }

    /**
     * 返回2
     *
     * @return
     */
    private static int method2() {
        int a = 0;
        try {
            return ++a;
        } finally {
            return ++a;
        }
    }

    /**
     * 返回10
     *
     * @return
     */
    private static int method3() {
        int a = 0;
        try {
            return a++;
        } finally {
            return 10;
        }
    }


}
